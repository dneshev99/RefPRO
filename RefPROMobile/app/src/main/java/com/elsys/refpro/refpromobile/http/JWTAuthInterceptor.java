package com.elsys.refpro.refpromobile.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by i.ivanov on 15-Dec-17.
 */

public class JWTAuthInterceptor implements Interceptor {

    private String authToken;

    public JWTAuthInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
