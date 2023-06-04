package com.example.proyectocliente.API;

import com.example.proyectocliente.base.Parameters;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class CallMethods {
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.22:8080/apidb/").build();
    private APIService service = retrofit.create(APIService.class);
    private static CallMethods callMethods;

    public static CallMethods getCallMethodsObject() {
        if (callMethods == null) {
            callMethods = new CallMethods();
        }
        return callMethods;
    }

    public String get(String url) {
        Call<ResponseBody> call = service.getCall(url);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String post(String url, RequestBody data) {
        Call<ResponseBody> call = service.postCall(url, data);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String put(String url, RequestBody data) {
        Call<ResponseBody> call = service.putCall(url, data);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String delete(String url) {
        Call<ResponseBody> call = service.deleteCall(url);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
