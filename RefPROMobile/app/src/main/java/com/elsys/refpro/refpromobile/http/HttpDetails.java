package com.elsys.refpro.refpromobile.http;


public class HttpDetails {

    public static final String httpAddress="10.19.9.30";
    public static final String httpPort="8082";
    public static final String protocolType="http";
    public static final long CONNECT_TIMEOUT_MS=10*1000l;
    public static final long WRITE_TIMEOUT_MS=10*1000l;
    public static final long READ_TIMEOUT_MS=10*1000l;

    public static String getRetrofitUrl(){
        return protocolType+"://"+httpAddress+":"+httpPort;
    }
}
