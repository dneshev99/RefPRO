package com.elsys.refpro.refpromobile.http.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.activities.MainActivity;
import com.elsys.refpro.refpromobile.dto.UserDTO;
import com.elsys.refpro.refpromobile.http.RetrofitHookBack;
import com.elsys.refpro.refpromobile.services.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class LoginHandler {

    private Retrofit retrofit;
    private Context context;
    private SharedPreferences preferences;
    private static final String LOG_TAG = LoginHandler.class.getName();

    @Inject
    public LoginHandler(Retrofit retrofit, Context context, SharedPreferences preferences){
        this.retrofit=retrofit;
        this.context=context;
        this.preferences=preferences;
    }

    public void login(final String username, final String password, final RetrofitHookBack hookBack) {

        UserService service = retrofit.create(UserService.class);

        service.login(new UserDTO(username, password)).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    SharedPreferences.Editor prefsEditor = preferences.edit();
                    prefsEditor.putString("token", response.headers().get("authorization"));

                    prefsEditor.putString("username", username);
                    prefsEditor.apply();

                    hookBack.executeCallBack();
                }
                else {

                    Toast.makeText(context, "Wrong username or password", Toast.LENGTH_LONG).show();

                    hookBack.executeErrorCallBack();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(context, "Error!",
                        Toast.LENGTH_LONG).show();
                Log.e(LOG_TAG, t.getMessage() + " ");

                hookBack.executeErrorCallBack();
            }
        });
    }
}
