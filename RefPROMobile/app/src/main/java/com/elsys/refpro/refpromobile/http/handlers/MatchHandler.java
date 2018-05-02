package com.elsys.refpro.refpromobile.http.handlers;


import android.content.Context;

import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.dto.MatchInfoDTO;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.services.MatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
@Singleton
public class MatchHandler {

    private Retrofit retrofit;
    private Context context;

    @Inject
    public MatchHandler(Retrofit retrofit, Context context){
        this.retrofit=retrofit;
        this.context=context;
    }
    private static final Logger log = LoggerFactory.getLogger(TeamsHandler.class.getName());
    public MatchInfoDTO getMatchInfoById(String id, final ArrayList<PlayerDTO> allPlayers){
        MatchInfoDTO result;
        MatchService service = retrofit.create(MatchService.class);

        service.getMatchById(id).enqueue(new Callback<MatchInfoDTO>() {
            @Override
            public void onResponse(Call<MatchInfoDTO> call, Response<MatchInfoDTO> response) {
                if(response.isSuccessful() && response.body()!=null) {
                    for (PlayerDTO playerDTO : response.body().getAwayPlayers()) {
                        allPlayers.add(playerDTO);
                    }

                    for (PlayerDTO playerDTO : response.body().getHomePlayers()) {
                        allPlayers.add(playerDTO);
                    }
                }else{
                    log.error("Response is not successful code: {}",response.code());
                }
            }

            @Override
            public void onFailure(Call<MatchInfoDTO> call, Throwable t) {
                log.error(t.getMessage());
            }
        });

        return  null;
    }


     class CallbackImpl<T> implements  Callback<T>{

        private T bodyResult;
        private final Logger log = LoggerFactory.getLogger(CallbackImpl.class.getName());
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if(response.isSuccessful()){
                bodyResult=response.body();
            }else{
                log.error("Response is not successful code: {}",response.code());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            log.error(t.getMessage());
        }

         public T getBodyResult() {
             return bodyResult;
         }
     }
}
