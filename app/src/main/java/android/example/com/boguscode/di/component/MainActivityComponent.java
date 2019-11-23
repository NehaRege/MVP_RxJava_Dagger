package android.example.com.boguscode.di.component;

import android.example.com.boguscode.di.module.MainActivityContextModule;
import android.example.com.boguscode.di.scope.ActivityScope;
import android.example.com.boguscode.view.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MainActivityContextModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
}
