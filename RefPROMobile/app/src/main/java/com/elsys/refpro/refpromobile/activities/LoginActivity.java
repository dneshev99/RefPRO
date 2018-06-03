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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.request.target.ViewTarget;
import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.application.DIApplication;
import com.elsys.refpro.refpromobile.http.HttpDetails;
import com.elsys.refpro.refpromobile.http.RetrofitHookBack;
import com.elsys.refpro.refpromobile.http.handlers.LoginHandler;
import com.elsys.refpro.refpromobile.services.UserService;
import com.elsys.refpro.refpromobile.dto.UserDTO;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Date;

import javax.inject.Inject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
    private static final String LOG_TAG = LoginActivity.class.getName();

    @Inject
    SharedPreferences preferences;

    @Inject
    LoginHandler loginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG,"Mobile token: "+ FirebaseInstanceId.getInstance().getToken());
        ViewTarget.setTagId(R.id.glide_tag);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((DIApplication) this.getApplicationContext()).getApplicationComponent().inject(this);

        initialize();

        skipLoginIfTokenExists();

        final Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userString = username.getText().toString().trim();
                final String passString = password.getText().toString().trim();


                if (userString.isEmpty() || passString.isEmpty()) {

                    vibrate.vibrate(300);
                } else {

                    loading.setVisibility(View.VISIBLE);
                    signButton.setVisibility(View.INVISIBLE);

                    loginHandler.login(userString, passString, new RetrofitHookBack() {
                        @Override
                        public void executeCallBack(Object... objects) {

                            Intent app = new Intent(getApplicationContext(), MainActivity.class);
                            app.putExtra("Username", userString);

                            startActivity(app);
                        }

                        @Override
                        public void executeErrorCallBack(Object... objects) {

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

    private void skipLoginIfTokenExists() {

        try{
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
        }catch (ExpiredJwtException e){
            Log.d(LOG_TAG,"Jwt token has expired");
        }

    }
}

