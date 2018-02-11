package com.elsys.refpro.refpromobile.http;

import android.text.TextUtils;
import android.util.Log;

import com.elsys.refpro.refpromobile.http.dto.UserDto;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by i.ivanov on 15-Dec-17.
 */

public class TokenDrivenRFItServiceGenerator implements CustomRetroFitLogin  {

    public  final String BASE_URL ;
    private  final  OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private  final Retrofit.Builder builder ;

    public  <T> T createService(Class<T> serviceClass) {
        Log.d("MyApp","here123 "+this.jwtToken);
        return createService(serviceClass, jwtToken);
    }

    private String jwtToken;

    public  <T> T createService( Class<T> serviceClass, final String authToken) {
        Retrofit retrofit=null;
        if (!TextUtils.isEmpty(authToken) ) {
            JWTAuthInterceptor interceptor =
                    new JWTAuthInterceptor(authToken);
            Log.d("MyApp","here "+authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                Log.d("MyApp","here44 ");
            }

        }
        Log.d("MyApp","here2 "+authToken);

        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }


    //Async problems
     public synchronized void  requestToken(Class<LoginService> loginServiceClass,String userName,String password){
         Retrofit retrofit= builder.build();
         LoginService loginService = retrofit.create(loginServiceClass);
         Callback<Void> a = new Callback<Void>() {
             @Override
             public void onResponse(Call<Void> call, Response<Void> response) {
                 if (response.isSuccessful()) {
                     String token = response.headers().get("Authorization");
                     if (!StringUtils.isEmpty(token)) {
                         Log.d("MyApp", "tokneasdasd " + token);
                         jwtToken = token;
                     }
                 }
                 Log.d("MyApp", "tokne" + jwtToken);
             }

             @Override
             public void onFailure(Call<Void> call, Throwable t) {
                 Log.d("MyApp", "" + t.getMessage());
             }
         };

         Response<Void> resp;
         try {
             resp = loginService.login(new UserDto(userName, password)).execute();
             this.jwtToken = resp.headers().get("Authorization");
             Log.d("MyApp", "set" + jwtToken);
         } catch (IOException e) {
             e.printStackTrace();
         }

     }

    private TokenDrivenRFItServiceGenerator(Builder builder){
         //= "http://10.19.9.30:8081"
        this.BASE_URL = builder.baseUrl;
       this.builder =
        new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static class Builder{
        private String baseUrl;
        public static ConcurrentHashMap<String,TokenDrivenRFItServiceGenerator> builtInstances = new ConcurrentHashMap<>();

        public Builder withBaseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public TokenDrivenRFItServiceGenerator build(){
            //If an instance with the same URL already exists retrieve it and return it
            if(!StringUtils.isEmpty(this.baseUrl)){
                if(!builtInstances.containsKey(this.baseUrl)) {
                    builtInstances.put(this.baseUrl, new TokenDrivenRFItServiceGenerator(this));
                }
                TokenDrivenRFItServiceGenerator res = builtInstances.get(this.baseUrl);
                if(res!=null)
                    return res;

            }


            return new TokenDrivenRFItServiceGenerator(this);
        }
    }

}
