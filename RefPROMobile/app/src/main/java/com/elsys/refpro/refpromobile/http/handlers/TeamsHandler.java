package com.elsys.refpro.refpromobile.http.handlers;


import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.elsys.refpro.refpromobile.dto.TeamDTO;
import com.elsys.refpro.refpromobile.services.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class TeamsHandler {

    Retrofit retrofit ;

    //When we can create singletons we`ll have to use @Inject on a constructor
    @Inject
    public TeamsHandler(Retrofit retrofit){
        this.retrofit=retrofit;
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
                    if(eachTeamDto!=null && eachTeamDto.getName()!=null)
                        teamNames.add(eachTeamDto.getName());
                }
                Log.d("HttpCall",teamNames+"");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, teamNames);
                home.setAdapter(adapter);
                away.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TeamDTO>> call, Throwable t) {
                Log.e("HttpCall","Error",t);
            }
        });

        return teams;
    }


}
