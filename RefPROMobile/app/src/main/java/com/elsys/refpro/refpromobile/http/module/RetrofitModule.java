package com.elsys.refpro.refpromobile.http.module;


import android.support.annotation.NonNull;

import com.elsys.refpro.refpromobile.http.HttpDetails;
import com.elsys.refpro.refpromobile.http.RetrofitJwtInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.elsys.refpro.refpromobile.http.HttpDetails.CONNECT_TIMEOUT_MS;
import static com.elsys.refpro.refpromobile.http.HttpDetails.READ_TIMEOUT_MS;
import static com.elsys.refpro.refpromobile.http.HttpDetails.WRITE_TIMEOUT_MS;

@Module
public class RetrofitModule {



    //we create modules, because we have no control over okHttpClient constructor for example, but we need an instance
    @Provides
    @Singleton
    OkHttpClient okHttpClient(RetrofitJwtInterceptor interceptor) {

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
               // .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT_MS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_MS, TimeUnit.SECONDS)
                .build();
    }
    @Provides
    @Singleton
    Retrofit provideRetrofitClient(@NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(HttpDetails.getRetrofitUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




}
