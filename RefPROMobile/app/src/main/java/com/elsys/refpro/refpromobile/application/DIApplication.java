package com.elsys.refpro.refpromobile.application;

import android.app.Application;

import com.elsys.refpro.refpromobile.http.DI.components.ApplicationComponent;
import com.elsys.refpro.refpromobile.http.DI.components.DaggerApplicationComponent;
import com.elsys.refpro.refpromobile.http.module.ApplicationModule;
import com.elsys.refpro.refpromobile.http.module.RetrofitModule;

public class DIApplication extends Application {
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationComponent= DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).retrofitModule(new RetrofitModule()).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
