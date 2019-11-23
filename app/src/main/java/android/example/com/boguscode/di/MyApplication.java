package android.example.com.boguscode.di;

import android.app.Activity;
import android.app.Application;
import android.example.com.boguscode.di.component.ApplicationComponent;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        applicationComponent = DaggerApplicationComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
//        applicationComponent.injectApplication(this);
    }

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
