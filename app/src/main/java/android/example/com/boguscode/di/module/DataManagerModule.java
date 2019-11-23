package android.example.com.boguscode.di.module;

import android.example.com.boguscode.api.retrofit.ApiNetworkService;
import android.example.com.boguscode.data.DataManager;
import android.example.com.boguscode.data.DataManagerImpl;
import android.example.com.boguscode.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DataManagerModule {

    @Provides
    @ActivityScope
    public DataManager providesDataManager(ApiNetworkService apiNetworkService) {
        return new DataManagerImpl(apiNetworkService);
    }
}
