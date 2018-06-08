package com.elsys.refpro.refprowatch.events;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.elsys.refpro.refprowatch.enums.MatchEventTypes;
import com.elsys.refpro.refprowatch.http.EventService;
import com.elsys.refpro.refprowatch.http.StateService;
import com.elsys.refpro.refprowatch.http.dto.MatchEventDTO;
import com.elsys.refpro.refprowatch.http.dto.MatchStateDTO;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEvent {

    private ArrayList<MatchEventDTO> events = new ArrayList<>();
    private String id;
    Context context;
    SharedPreferences preferences;

    public CreateEvent(String id, Context context) {

        this.id = id;
        this.context = context;
        preferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    public CreateEvent() {
    }


    public ArrayList<MatchEventDTO> addEvent(String time, MatchEventTypes eventType, String team, String player, boolean escape) {

        MatchEventDTO newEvent;

        if (!escape) {

            newEvent = new MatchEventDTO(time, eventType, team);
        }
        else {

            newEvent = new MatchEventDTO(time, eventType, team);
        }

        events.add(newEvent);

        if (events.size() != 1)
            sendEvent(newEvent);

        return events;
    }

    private void sendEvent(MatchEventDTO newEvent) {

        final String token = preferences.getString("token","N/A");

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", token)
                        .build();
                android.util.Log.i("HERE:", newRequest.toString());
                android.util.Log.i("HERE:", newRequest.header("Authorization"));
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://api2.tues.dreamix.eu:80")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EventService service = retrofit.create(EventService.class);

        service.send(newEvent, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(context, "SUCCESS",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "FAILURE-EVENT",
                            Toast.LENGTH_SHORT).show();
                    android.util.Log.d("Error:", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(context, "FAILURE-EVENT",
                        Toast.LENGTH_SHORT).show();
                android.util.Log.d("Error:",t.getMessage());
            }
        });
    }
}
