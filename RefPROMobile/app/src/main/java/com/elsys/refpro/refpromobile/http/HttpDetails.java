package com.elsys.refpro.refpromobile.http;

/**
 * Created by user on 4.2.2018 г..
 */

public class HttpDetails {

    public static final String httpAddress="10.19.9.30";
    public static final String httpPort="8082";
    public static final String protocolType="http";

    public static String getRetrofitUrl(){
        return protocolType+"://"+httpAddress+":"+httpPort;
    }
}
