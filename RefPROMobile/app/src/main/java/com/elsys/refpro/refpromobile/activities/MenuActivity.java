package com.elsys.refpro.refpromobile.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.adapters.MatchItemAdapter;
import com.elsys.refpro.refpromobile.application.DIApplication;
import com.elsys.refpro.refpromobile.database.LocalDatabase;
import com.elsys.refpro.refpromobile.http.handlers.LoginHandler;
import com.elsys.refpro.refpromobile.http.handlers.MatchHandler;
import com.elsys.refpro.refpromobile.http.handlers.MenuHandler;
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

import javax.inject.Inject;

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

    @Inject
    MatchHandler matchHandler;

    @Inject
    SharedPreferences preferences;

    @Inject
    MenuHandler menuHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((DIApplication) this.getActivity().getApplicationContext()).getApplicationComponent().inject(this);

        menuView = inflater.inflate(R.layout.activity_menu, container, false);

        final String jwtToken = preferences.getString("token", "N/A");
        final String fcmToken = FirebaseInstanceId.getInstance().getToken();
        updateCurrentUserFcmToken(jwtToken,fcmToken, DeviceType.MOBILE);

        //region INITIALIZE
        final SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
        RecyclerView layout = (RecyclerView) menuView.findViewById(R.id.menu_layout);

        //Get last saved match's ID
        matchId = mPrefs.getInt("ID", 0);

        //endregion
        db = new LocalDatabase(this.getActivity());

        Cursor data = db.getData();
        final ArrayList<Item> match_info = new ArrayList<Item>();
        MatchItemAdapter mAdapter = new MatchItemAdapter(match_info, this.getActivity().getApplicationContext(),this.getActivity().getFragmentManager());
        mAdapter.setMatchHandler(matchHandler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        layout.setLayoutManager(mLayoutManager);
        layout.setAdapter(mAdapter);
        while (data.moveToNext()) {

            final Item current_match = new Item(data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(2), data.getString(10));
            current_match.setDbId(Integer.parseInt(data.getString(0)));
            match_info.add(current_match);
        }

        mAdapter.notifyDataSetChanged();

        return menuView;
    }

    private void updateCurrentUserFcmToken(final String jwtToken,final String fcmToken,final DeviceType deviceType){
        menuHandler.addTokenForUser(jwtToken, fcmToken, deviceType);
    }
}
