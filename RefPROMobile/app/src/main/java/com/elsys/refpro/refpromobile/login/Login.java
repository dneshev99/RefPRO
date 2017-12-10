package com.elsys.refpro.refpromobile.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.http.LoginService;
import com.elsys.refpro.refpromobile.http.dto.UserDto;
import com.elsys.refpro.refpromobile.main.MainActivity;
import com.elsys.refpro.refpromobile.R;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button sign;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        sign = (Button) findViewById(R.id.sign);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        preferences = getSharedPreferences("RefPRO" , 0);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userString = username.getText().toString().trim();
                final String passString = password.getText().toString().trim();

                if (userString.isEmpty() || passString.isEmpty()) {

                    vibrator.vibrate(300);
                }
                else  {

                     Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://localhost:8080")
                             .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginService service = retrofit.create(LoginService.class);
                    service.login(userString, passString).enqueue(new Callback<UserDto>() {
                        @Override
                        public void onResponse(Call<UserDto> call, Response<UserDto> response) {

                            if (response.isSuccessful()) {

                                SharedPreferences.Editor prefsEditor = preferences.edit();
                                prefsEditor.putString("token", response.headers().get("authorization"));
                                prefsEditor.commit();


                                Intent app = new Intent(getApplicationContext(), MainActivity.class);
                                app.putExtra("Username", userString);
                                app.putExtra("Password", passString);
                                startActivity(app);
                            }
                            else
                                Toast.makeText(getBaseContext(), "Wrong username or password!",
                                        Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<UserDto> call, Throwable t) {

                            Toast.makeText(getBaseContext(), "Error!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
//                    service.listRepos().enqueue(new Callback<List<UserDto>>() {
//                        @Override
//                        public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
//                            Toast.makeText(getApplicationContext(),response.body().get(0).toString(),Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<UserDto>> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
//
//                        }
//                    });
                }

                    /*Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://google.com")//.addConverterFactory(GsonConverterFactory.create())

                            .build();

                    LoginService service = retrofit.create(LoginService.class);
                    service.g().enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_LONG).show();
//
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
//                    service.listRepos().enqueue(new Callback<List<UserDto>>() {
//                        @Override
//                        public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
//                            Toast.makeText(getApplicationContext(),response.body().get(0).toString(),Toast.LENGTH_LONG).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<UserDto>> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
//
//                        }
//                    });*/

                    vibrator.vibrate(300);


            }
        });
    }
}
