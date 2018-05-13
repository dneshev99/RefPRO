package com.elsys.refpro.refpromobile.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.database.LocalDatabase;
import com.elsys.refpro.refpromobile.models.Item;
import com.elsys.refpro.refpromobile.services.DeleteService;
import com.elsys.refpro.refpromobile.enums.DeviceType;
import com.elsys.refpro.refpromobile.http.HttpDetails;
import com.elsys.refpro.refpromobile.services.UserService;
import com.elsys.refpro.refpromobile.dto.UserDTO;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class MenuActivity extends Fragment {

    View menuView;
    int matchId;
    LocalDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences preferences;
        preferences = getActivity().getSharedPreferences("RefPRO" , 0);
        menuView = inflater.inflate(R.layout.activity_menu, container, false);

        final String jwtToken = preferences.getString("token", "N/A");
        final String fcmToken = FirebaseInstanceId.getInstance().getToken();
        updateCurrentUserFcmToken(jwtToken,fcmToken, DeviceType.MOBILE);

        //region INITIALIZE
        final SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
        LinearLayout layout = (LinearLayout) menuView.findViewById(R.id.menu_layout);

        //Get last saved match's ID
        matchId = mPrefs.getInt("ID", 0);

        //endregion
        db = new LocalDatabase(this.getActivity());

        Cursor data = db.getData();
        final ArrayList<Item> match_info = new ArrayList<Item>();

        while (data.moveToNext()) {

            final Item current_match = new Item(data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(2), data.getString(10));
        }

        return menuView;
    }

    private void updateCurrentUserFcmToken(final String jwtToken,final String fcmToken,final DeviceType deviceType){

        final SharedPreferences preferences;
        preferences = getActivity().getSharedPreferences("RefPRO" , 0);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", jwtToken)
                        .addHeader("DeviceType", deviceType.toString())
                        .build();

                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpDetails.getRetrofitUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        service.addFcmTokenForUser(fcmToken).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(HttpDetails.getRetrofitUrl())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    UserService service = retrofit.create(UserService.class);
                    service.getFcmTokenForUser().enqueue(new Callback<UserDTO>() {
                        @Override
                        public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {

                            if(response.isSuccessful()) {
                                UserDTO userDto = response.body();
                                if(userDto.getTokens()==null || userDto.getTokens().get(DeviceType.WEAR)==null){
                                    return;
                                }
                                String WatchFCMToken = userDto.getTokens().get(DeviceType.WEAR);
                                Toast.makeText(getActivity(), WatchFCMToken,
                                        Toast.LENGTH_LONG).show();

                                SharedPreferences.Editor prefsEditor = preferences.edit();
                                prefsEditor.putString("WatchFCMToken", WatchFCMToken);
                                prefsEditor.apply();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserDTO> call, Throwable t) {

                            Toast.makeText(getActivity(), "Failed to get Watch FCM Token",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getActivity(), R.string.fcmToken_error,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
