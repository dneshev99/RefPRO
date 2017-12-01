package com.elsys.refpro.refpromobile.login;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elsys.refpro.refpromobile.main.MainActivity;
import com.elsys.refpro.refpromobile.R;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        sign = (Button) findViewById(R.id.sign);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userString = username.getText().toString().trim();
                String passString = password.getText().toString().trim();

                if (userString.isEmpty() || passString.isEmpty()) {

                    vibrator.vibrate(300);
                }
                else if (userString.equals("bfu") && passString.equals("bfu")) {

                    Intent app = new Intent(getApplicationContext(), MainActivity.class);
                    app.putExtra("Username", userString);
                    startActivity(app);
                }
                else {

                    vibrator.vibrate(300);
                }

            }
        });
    }
}
