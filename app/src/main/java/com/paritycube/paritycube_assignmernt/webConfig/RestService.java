package com.paritycube.paritycube_assignmernt.webConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infinx.saprfid.Web.ApiCall;
import com.infinx.saprfid.Web.WebConfig;
import com.paritycube.paritycube_assignmernt.constant.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private ApiCall apiService;

    public RestService() {
        // Define the interceptor, add authentication headers

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest;
                newRequest = chain.request().newBuilder()
                        .addHeader(Constant.INSTANCE.getHeaderKey(), Constant.INSTANCE.getHeaderValue()).build();
                return chain.proceed(newRequest);
            }
        };

        // Add the interceptor to OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setLenient().excludeFieldsWithModifiers().create();

        // Set the custom client when building adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebConfig.INSTANCE.getURL())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiCall.class);
    }


    public ApiCall getService() {
        return apiService;
    }
}
