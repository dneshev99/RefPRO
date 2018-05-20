package com.elsys.refpro.refpromobile.http.handlers;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.adapters.MatchItemAdapter;
import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.dto.ErrorDTO;
import com.elsys.refpro.refpromobile.dto.MatchInfoDTO;
import com.elsys.refpro.refpromobile.dto.MatchUpdateDTO;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.http.RetrofitHookBack;
import com.elsys.refpro.refpromobile.services.MatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
@Singleton
public class MatchHandler {

    private Retrofit retrofit;
    private Context context;
    private static final String LOG_TAG = MatchHandler.class.getName();
    @Inject
    public MatchHandler(Retrofit retrofit, Context context){
        this.retrofit=retrofit;
        this.context=context;
    }

    public void updateMatchInfoById(final String id, MatchUpdateDTO dto){
        MatchService service = retrofit.create(MatchService.class);
        service.updateMatchInfo(dto).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){

                }else{
                    Toast.makeText(context, "Updating match info failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Updating match info failed",Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG,t.getMessage(),t);
            }
        });
    }

    public MatchInfoDTO getMatchInfoById(String id,final View homePlayersTerrain,final View awayPlayersTerrain)   {
        final MatchInfoDTO result;
        MatchService service = retrofit.create(MatchService.class);

        service.getMatchById(id).enqueue(new Callback<MatchInfoDTO>() {
            @Override
            public void onResponse(Call<MatchInfoDTO> call, Response<MatchInfoDTO> response) {
                if(response.isSuccessful()){
                   MatchInfoDTO result = response.body();
                   homePlayersTerrain.setTag(result.getHome());
                   awayPlayersTerrain.setTag(result.getAway());
                }else{
                    Log.e("MatchHandler","Response is not successful code: " +response.code());
                }
            }

            @Override
            public void onFailure(Call<MatchInfoDTO> call, Throwable t) {
                Log.e("MatchHandler",t.getMessage());
            }
        });
        return null;
    }


    public MatchInfoDTO getMatchInfoById(String id, final RetrofitHookBack hookBack)   {
        final MatchInfoDTO result;
        MatchService service = retrofit.create(MatchService.class);

        service.getMatchById(id).enqueue(new Callback<MatchInfoDTO>() {
            @Override
            public void onResponse(Call<MatchInfoDTO> call, Response<MatchInfoDTO> response) {
                if(response.isSuccessful()){
                    MatchInfoDTO result = response.body();
                    hookBack.executeCallBack(response);
                }else{
                    Log.e("MatchHandler","Response is not successful code: " +response.code());
                    hookBack.executeErrorCallBack();
                }
            }

            @Override
            public void onFailure(Call<MatchInfoDTO> call, Throwable t) {
                Log.e("MatchHandler",t.getMessage());
                hookBack.executeErrorCallBack();
            }
        });
        return null;
    }


}
