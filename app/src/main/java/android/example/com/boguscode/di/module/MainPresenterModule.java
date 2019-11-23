package android.example.com.boguscode.di.module;

import android.example.com.boguscode.data.DataManager;
import android.example.com.boguscode.di.scope.ActivityScope;
import android.example.com.boguscode.presenter.MainPresenter;
import android.example.com.boguscode.presenter.MainPresenterImpl;
import android.example.com.boguscode.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    private MainView mainView;

    public MainPresenterModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    @ActivityScope
    public MainView providesMainView() {
        return mainView;
    }

    @Provides
    @ActivityScope
    public MainPresenter providesMainPresenter(MainView mainView, DataManager dataManager) {
        return new MainPresenterImpl(mainView, dataManager);
    }

}
