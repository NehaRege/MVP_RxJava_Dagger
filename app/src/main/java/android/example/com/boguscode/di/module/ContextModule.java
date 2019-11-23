package android.example.com.boguscode.di.module;

import android.content.Context;
import android.example.com.boguscode.di.qualifier.ApplicationContext;
import android.example.com.boguscode.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
