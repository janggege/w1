package shixun.lj.bw.weak1.mvp.okhttp;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
  name:刘江
  data:2019
*/public class Okhttputils {
    private OkHttpClient client;
    private static volatile Okhttputils instance;
    private Handler handler = new Handler();

    //创建拦截器
    private Interceptor getInterpolator() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.i("++++", "拦截前");
                Response response = chain.proceed(request);
                Log.i("++++", "拦截后");
                return response;
            }
        };
        return interceptor;
    }

    //添加拦截器
    private Okhttputils() {
        File file = new File(Environment.getExternalStorageDirectory(), "cache1");
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(getInterpolator())
                .cache(new Cache(file, 10 * 1024))
                .build();
    }

    //创建单例
    public static Okhttputils getinstance() {
        if (instance == null) {
            synchronized (Okhttputils.class) {
                if (instance == null) {
                    instance = new Okhttputils();
                }
            }
        }
        return instance;
    }

    //封装doget
    public void doGet(String url, final Class cizz, final NetCallBack callback) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(string, cizz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.LoadSuccess(o);
                    }
                });

            }
        });
    }

    //封装post
    public void doPost(String url, final Class cizz, String name, String pwd, final NetCallBack callBack) {
        client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("phone", name)
                .add("pwd", pwd)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.LoadFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(string, cizz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.LoadSuccess(o);
                    }
                });

            }
        });


    }

    public interface NetCallBack {
        void LoadSuccess(Object obj);

        void LoadFail();
    }

}
