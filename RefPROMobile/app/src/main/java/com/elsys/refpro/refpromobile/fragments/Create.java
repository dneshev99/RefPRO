package com.elsys.refpro.refpromobile.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.database.LocalDatabase;
import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.http.CreateMatchService;
import com.elsys.refpro.refpromobile.http.dto.CreateMatchDto;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create extends Fragment implements View.OnClickListener{

    View createView;
    boolean canCreate = true;
    EditText competition, venue, players, subs, length, home, away, homeAbbr, awayAbbr, time, date;
    Button create;
    ProgressBar loading;

    LocalDatabase db;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        createView = inflater.inflate(R.layout.create_fragment, container, false);
        preferences = getActivity().getSharedPreferences("RefPRO" , 0);
        // region INITIALIZE
        competition = (EditText) createView.findViewById(R.id.competition);
        venue = (EditText) createView.findViewById(R.id.venue);
        players = (EditText) createView.findViewById(R.id.players);
        subs = (EditText) createView.findViewById(R.id.subs);
        length = (EditText) createView.findViewById(R.id.lenght);
        home = (EditText) createView.findViewById(R.id.home);
        away = (EditText) createView.findViewById(R.id.away);
        homeAbbr = (EditText) createView.findViewById(R.id.homeabbr);
        awayAbbr = (EditText) createView.findViewById(R.id.awayabbr);
        time = (EditText) createView.findViewById(R.id.time);
        date = (EditText) createView.findViewById(R.id.date);

        create = (Button) createView.findViewById(R.id.createMatch);
        create.setOnClickListener(this);

        loading = (ProgressBar) createView.findViewById(R.id.progressBar);

        //endregiond
        db = new LocalDatabase(this.getActivity());

        return createView;
    }

    @Override
    public void onClick(View v) {

        int subsInt, playersInt, lengthInt;

        CheckStringParameters(competition, 100);
        CheckStringParameters(venue, 50);
        CheckStringParameters(home, 30);
        CheckStringParameters(away, 30);
        CheckStringParameters(awayAbbr, 5);
        CheckStringParameters(homeAbbr, 5);

        if (time.getText().toString().isEmpty()) {

            time.setError("Wrong time format (HH:MM)");
            canCreate = false;
        }
        else {

            String check = time.getText().toString();

            char [] elements = check.toCharArray();

            if (Integer.parseInt(String.valueOf(elements[0])) > 2 || (Integer.parseInt(String.valueOf(elements[1])) > 3 && Integer.parseInt(String.valueOf(elements[0])) == 2)
                    || Integer.parseInt(String.valueOf(elements[3])) > 5 || elements.length != 5) {

                time.setError("Wrong time format (HH:MM)");
                canCreate = false;
            }
        }

        if (date.getText().toString().isEmpty() || date.getText().toString().length() != 10)   {

            date.setError("Wrong date format (YYYY-MM-DD)");
            canCreate = false;
        }
        else {

            final String DATE_FORMAT = "yyyy-MM-dd";
            String check = date.getText().toString();

            try {

                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(check);

            } catch (ParseException e) {

                date.setError("Wrong date format (YYYY-MM-DD)");
                canCreate = false;
            }
        }

        playersInt = CheckIntParameters(players, 8, 15);
        subsInt = CheckIntParameters(subs, 3, 12);
         lengthInt = CheckIntParameters(length, 30, 55);

        if (!canCreate) {

            canCreate = true;
        }
        else {

            final String token = preferences.getString("token","N/A");

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization",token)
                            .build();
                    Log.i("HERE:",newRequest.toString());
                    Log.i("HERE:",newRequest.header("Authorization"));
                    return chain.proceed(newRequest);
                }
            }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("http://10.0.2.2:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            CreateMatchService service = retrofit.create(CreateMatchService.class);

            loading.setVisibility(View.VISIBLE);
            create.setVisibility(View.INVISIBLE);

            final int finalPlayersInt = playersInt;
            final int finalSubsInt = subsInt;
            final int finalLenghtInt =  lengthInt;
            service.create(new CreateMatchDto(false, competition.getText().toString(), venue.getText().toString(),
                    date.getText().toString(), time.getText().toString(), home.getText().toString(), away.getText().toString(),
                    homeAbbr.getText().toString(), awayAbbr.getText().toString(),
                    finalPlayersInt, finalSubsInt, finalLenghtInt)).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();

                        String mongoID = null;
                        try {
                            mongoID = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(getActivity(), "Success:" + mongoID, Toast.LENGTH_LONG).show();

                        boolean insertData = db.addData(competition.getText().toString(), venue.getText().toString(),
                                home.getText().toString(), away.getText().toString(), homeAbbr.getText().toString()
                                , awayAbbr.getText().toString(), date.getText().toString(), time.getText().toString(),
                                finalPlayersInt, finalSubsInt, finalLenghtInt, mongoID);

                        if (insertData)
                            Toast.makeText(getActivity(), R.string.create_success,
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), R.string.error,
                                    Toast.LENGTH_LONG).show();

                        loading.setVisibility(View.INVISIBLE);
                        create.setVisibility(View.VISIBLE);

                        Menu menu = new Menu();
                        android.app.FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, menu).commit();

                    } else {
                        Toast.makeText(getActivity(), "Error1", Toast.LENGTH_LONG).show();
                        Log.i("HERE", String.valueOf(response.code()));

                        loading.setVisibility(View.INVISIBLE);
                        create.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                    loading.setVisibility(View.INVISIBLE);
                    create.setVisibility(View.VISIBLE);
                }
            });

        }
    }


    public void CheckStringParameters(EditText field, int length) {

        if (field.getText().toString().length() < 1 || field.getText().toString().length() > length) {

            field.setError(getResources().getString(R.string.create_error));
            canCreate = false;
        }
    }

    public int CheckIntParameters(EditText field, int min, int max) {

        String toInt = field.getText().toString().trim();
        int value = 0;

        if (toInt.length() != 0) {

            value = Integer.parseInt(toInt);

            if (value < min || value > max) {

                field.setError(getResources().getString(R.string.create_error));
                canCreate = false;
            }
        }
        else {
            field.setError(getResources().getString(R.string.create_error));
            canCreate = false;
        }

        return value;
    }
}
