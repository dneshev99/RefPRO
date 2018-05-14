package com.elsys.refpro.refpromobile.http;

public interface RetrofitHookBack{
    void executeCallBack(Object ... objects);
    void executeErrorCallBack(Object ... objects);
}