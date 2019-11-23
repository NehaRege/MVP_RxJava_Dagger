package android.example.com.boguscode.di.component;

import android.content.Context;
import android.example.com.boguscode.api.retrofit.ApiNetworkService;
import android.example.com.boguscode.di.MyApplication;
import android.example.com.boguscode.di.module.ContextModule;
import android.example.com.boguscode.di.module.RetrofitModule;
import android.example.com.boguscode.di.qualifier.ApplicationContext;
import android.example.com.boguscode.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    ApiNetworkService getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);

}
