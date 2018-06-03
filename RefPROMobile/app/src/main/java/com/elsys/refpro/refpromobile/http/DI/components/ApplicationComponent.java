package com.elsys.refpro.refpromobile.http.DI.components;



import android.content.Context;
import android.content.SharedPreferences;

import com.elsys.refpro.refpromobile.activities.CreateActivity;
import com.elsys.refpro.refpromobile.activities.LoginActivity;
import com.elsys.refpro.refpromobile.activities.MatchInfoActivity;
import com.elsys.refpro.refpromobile.activities.MenuActivity;
import com.elsys.refpro.refpromobile.http.handlers.LoginHandler;
import com.elsys.refpro.refpromobile.http.handlers.MenuHandler;
import com.elsys.refpro.refpromobile.http.handlers.TeamsHandler;
import com.elsys.refpro.refpromobile.http.handlers.PlayersHandler;
import com.elsys.refpro.refpromobile.http.module.ApplicationModule;
import com.elsys.refpro.refpromobile.http.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class,AndroidInjectionModule.class, RetrofitModule.class})

public interface ApplicationComponent {

    //From where it should be available
    void inject(CreateActivity app);
    void inject(MatchInfoActivity app);
    void inject (MenuActivity app);
    void inject (LoginActivity app);
    Retrofit provideRetrofitClient();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    Context contextInject();
    TeamsHandler provideTeamHandler();
    PlayersHandler playersHandler();
    LoginHandler loginHandler();
    MenuHandler menuHandler();
}
//Defines which beans from the Modules should be injected and available (just as the name states it an interface=contract what is visible)