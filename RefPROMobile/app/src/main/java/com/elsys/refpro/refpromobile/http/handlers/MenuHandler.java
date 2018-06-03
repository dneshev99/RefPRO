package com.elsys.refpro.refpromobile.http.handlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.UserDTO;
import com.elsys.refpro.refpromobile.enums.DeviceType;
import com.elsys.refpro.refpromobile.services.UserService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class MenuHandler {

    private Retrofit retrofit;
    private Context context;
    private SharedPreferences preferences;
    private static final String LOG_TAG = MenuHandler.class.getName();

    @Inject
    public MenuHandler(Retrofit retrofit, Context context, SharedPreferences preferences){
        this.retrofit=retrofit;
        this.context=context;
        this.preferences=preferences;
    }

    public void addTokenForUser(final String jwtToken, final String fcmToken, final DeviceType deviceType) {



        UserService service = retrofit.create(UserService.class);
        service.addFcmTokenForUser(DeviceType.MOBILE.toString(),fcmToken).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {

                    getWatchToken();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(context, R.string.fcmToken_error,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getWatchToken () {

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
                    Toast.makeText(context, WatchFCMToken,
                            Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor prefsEditor = preferences.edit();
                    prefsEditor.putString("WatchFCMToken", WatchFCMToken);
                    prefsEditor.apply();
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

                Toast.makeText(context, "Failed to get Watch FCM Token",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
