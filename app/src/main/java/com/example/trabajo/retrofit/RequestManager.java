package com.example.trabajo.retrofit;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private Retrofit.Builder retrofit;

    public RequestManager(String BASE_URL, int timeout) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient
                .getUnsafeOkHttpClient()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient);
    }

    public RequestManager(String BASE_URL) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient
                .getUnsafeOkHttpClient()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient);
    }

    public <T> T create(Class<T> service) {
        return retrofit.build().create(service);
    }

    public <T> T create(Class<T> service, String username, String password) {
        HttpLoggingInterceptor interceptorLogger = new HttpLoggingInterceptor();
        interceptorLogger.setLevel(HttpLoggingInterceptor.Level.BODY);
        String authToken = Credentials.basic(username, password);
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
        OkHttpClient.Builder httpClient = UnsafeOkHttpClient
                .getUnsafeOkHttpClient();
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);
            httpClient.addInterceptor(interceptorLogger);
            retrofit.client(httpClient.build());
        }
        return retrofit.build().create(service);
    }
}
