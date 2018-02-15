package com.elsys.refpro.refpromobile.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.http.HttpDetails;
import com.elsys.refpro.refpromobile.services.UserService;
import com.elsys.refpro.refpromobile.dto.UserDTO;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signButton;
    ProgressBar loading;
    private final String TOKEN_PREFIX = "Bearer";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        final Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String userJwtToken = preferences.getString("token", "N/A");
        if(!"N/A".equals(userJwtToken)) {
            userJwtToken=userJwtToken.replace(TOKEN_PREFIX,"");
            int indexOfLastDot = userJwtToken.lastIndexOf('.');
            String withoutSignature = userJwtToken.substring(0, indexOfLastDot+1);
            Jwt<Header,Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
            Claims tokenBody = untrusted.getBody();
            String userName = tokenBody.getSubject();
            boolean isTokenValid = tokenBody.getExpiration().after(new Date(System.currentTimeMillis()));
            Log.d("token", isTokenValid + "");
            Log.d("token", userName);
            if(isTokenValid) {
                Intent app = new Intent(getApplicationContext(), MainActivity.class);
                app.putExtra("Username", userName);
                startActivity(app);
            }
        }
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userString = username.getText().toString().trim();
                final String passString = password.getText().toString().trim();


                if (userString.isEmpty() || passString.isEmpty()) {

                    vibrate.vibrate(300);
                } else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(HttpDetails.getRetrofitUrl())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    UserService service = retrofit.create(UserService.class);

                    loading.setVisibility(View.VISIBLE);
                    signButton.setVisibility(View.INVISIBLE);

                    service.login(new UserDTO(userString,passString)).enqueue(new Callback<ResponseBody>() {

                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if (response.isSuccessful()) {

                                    SharedPreferences.Editor prefsEditor = preferences.edit();
                                    prefsEditor.putString("token", response.headers().get("authorization"));

                                    prefsEditor.putString("username", userString);
                                    prefsEditor.apply();

                                    Intent app = new Intent(getApplicationContext(), MainActivity.class);
                                    app.putExtra("Username", userString);
                                    app.putExtra("Password", passString);

                                    startActivity(app);
                                }
                                else {

                                    Toast.makeText(getBaseContext(), "Wrong username or password", Toast.LENGTH_LONG).show();
                                }

                                loading.setVisibility(View.INVISIBLE);
                                signButton.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(getBaseContext(), "Error!",
                                    Toast.LENGTH_LONG).show();
                            Log.i("Failure", t.getMessage() + " ");

                            loading.setVisibility(View.INVISIBLE);
                            signButton.setVisibility(View.VISIBLE);
                        }
                    });

                    vibrate.vibrate(300);
                }
            }
        });
    }

    private void initialize() {

        username = (EditText) findViewById(R.id.usernameField);
        password = (EditText) findViewById(R.id.passwordField);
        signButton = (Button) findViewById(R.id.signInButton);
        loading = (ProgressBar) findViewById(R.id.progressBar);

        preferences = getSharedPreferences("RefPRO" , 0);
    }
}

