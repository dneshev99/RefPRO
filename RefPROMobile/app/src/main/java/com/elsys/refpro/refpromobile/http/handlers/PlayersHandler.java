package com.elsys.refpro.refpromobile.http.handlers;


import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.dto.TeamDTO;
import com.elsys.refpro.refpromobile.services.PlayersService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class PlayersHandler {

    private Retrofit retrofit;
    private Context context;

    @Inject
    public PlayersHandler(Retrofit retrofit, Context context){
        this.retrofit=retrofit;
        this.context=context;
    }

    public void setHomePlayersForDrawer(String teamName ,final ListView playersDrawer){
        PlayersService service = retrofit.create(PlayersService.class);
        service.getPlayersByTeam(teamName).enqueue(new Callback<List<PlayerDTO>>() {
            @Override
            public void onResponse(Call<List<PlayerDTO>> call, Response<List<PlayerDTO>> response) {
                List<String>playerName=new ArrayList<String>();
                for(PlayerDTO eachPlayerDto:response.body()){
                    if(eachPlayerDto!=null && eachPlayerDto.getShirtName()!=null)
                        playerName.add(eachPlayerDto.getShirtName());
                }
                Log.d("HttpCall","playerName="+playerName+"");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, playerName);
                playersDrawer.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PlayerDTO>> call, Throwable t) {
                Log.e("HttpCall","Error",t);
            }
        });
    }
}
