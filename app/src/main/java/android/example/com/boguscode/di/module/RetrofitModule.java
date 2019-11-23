package android.example.com.boguscode.di.module;

import android.example.com.boguscode.utils.Constants;
import android.example.com.boguscode.api.retrofit.ApiNetworkService;
import android.example.com.boguscode.di.scope.ApplicationScope;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    ApiNetworkService getApiNetworkService(Retrofit retrofit) {
        return retrofit.create(ApiNetworkService.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.VIMEO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }



}
