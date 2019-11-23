package android.example.com.boguscode.di.module;

import android.content.Context;
import android.example.com.boguscode.di.qualifier.ActivityContext;
import android.example.com.boguscode.di.scope.ActivityScope;
import android.example.com.boguscode.view.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {

    private MainActivity mainActivity;
    private Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }


    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
