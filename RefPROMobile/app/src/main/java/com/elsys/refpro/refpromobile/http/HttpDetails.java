package com.elsys.refpro.refpromobile.http;


public class HttpDetails {

    public static final String httpAddress="api2.tues.dreamix.eu";
    public static final String httpPort="80";
    public static final String protocolType="http";
    public static final long CONNECT_TIMEOUT_MS=10*1000L;
    public static final long WRITE_TIMEOUT_MS=10*1000L;
    public static final long READ_TIMEOUT_MS=10*1000L;

    public static String getRetrofitUrl(){
        return protocolType+"://"+httpAddress+":"+httpPort;
    }
}
