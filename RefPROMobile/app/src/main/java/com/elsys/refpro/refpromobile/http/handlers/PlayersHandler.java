package com.elsys.refpro.refpromobile.http.handlers;


import android.content.Context;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.adapters.PlayersAdapter;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.dto.TeamDTO;
import com.elsys.refpro.refpromobile.services.PlayersService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import okhttp3.ResponseBody;
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

    public void setHomePlayersForDrawer(String teamName ,final PlayerHeadAdapter adapter){
        PlayersService service = retrofit.create(PlayersService.class);
        service.getPlayersByTeam(teamName).enqueue(new Callback<List<PlayerDTO>>() {
            @Override
            public void onResponse(Call<List<PlayerDTO>> call, Response<List<PlayerDTO>> response) {
                if(response.isSuccessful()){
                    for(PlayerDTO playerDTO: response.body()){
                        adapter.add(playerDTO);
                        saveIconToFileStorage(playerDTO.getPlayerId());
                    }

                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<PlayerDTO>> call, Throwable t) {
                Log.e("HttpCall","Error",t);
            }
        });
    }

    public void saveIconToFileStorage(@NotNull final String playerId){
        PlayersService service = retrofit.create(PlayersService.class);
        service.getPlayerIcon(playerId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    String filename = playerId;
                    File file =null;
                    FileOutputStream outStreamWriter=null;
                    try {

                        file = new File(context.getFilesDir(), filename);
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        outStreamWriter = new FileOutputStream(file);
                        outStreamWriter.write(response.body().bytes());

                    } catch (Exception e) {
                        e.printStackTrace();

                    }finally {
                        try {
                            outStreamWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
