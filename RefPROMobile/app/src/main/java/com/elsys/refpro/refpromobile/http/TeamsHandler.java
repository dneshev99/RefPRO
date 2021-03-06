package com.elsys.refpro.refpromobile.http;


import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.elsys.refpro.refpromobile.dto.TeamDTO;
import com.elsys.refpro.refpromobile.services.TeamService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsHandler {

    private final String token ;
    OkHttpClient client;
    Retrofit retrofit ;
    public TeamsHandler(final String token){
        this.token=token;
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization",token)
                        .build();
                Log.d("Na sasho tokena",token);
                return chain.proceed(newRequest);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HttpDetails.getRetrofitUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private static final Logger log = Logger.getLogger(TeamsHandler.class.getName());






    public List<TeamDTO> getAllTeams(final AutoCompleteTextView home,final AutoCompleteTextView away,final Context context){
        TeamService service = retrofit.create(TeamService.class);
        final List<TeamDTO> teams=new ArrayList<>();
        service.getAllTeams().enqueue(new Callback<List<TeamDTO>>() {
            @Override
            public void onResponse(Call<List<TeamDTO>> call, Response<List<TeamDTO>> response) {
                Log.d("HttpCall",response.body()+"");

                teams.addAll(response.body());

                List<String>teamNames=new ArrayList<String>();
                for(TeamDTO eachTeamDto:teams){
                    teamNames.add(eachTeamDto.getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, teamNames);
                home.setAdapter(adapter);
                away.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TeamDTO>> call, Throwable t) {
                Log.e("HttpCall","Error",t);
            }
        });

        return teams;
    }


}
