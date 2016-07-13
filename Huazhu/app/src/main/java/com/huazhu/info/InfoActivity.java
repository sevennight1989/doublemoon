package com.huazhu.info;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huazhu.R;
//import com.huazhu.callback.JsonCallback;
import com.huazhu.info.bean.Location;
import com.huazhu.info.bean.RequestInfo;
import com.huazhu.utils.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.BitmapCallback;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.request.BaseRequest;
//import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

//import okhttp3.Call;
//import okhttp3.Request;
//import okhttp3.Response;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import rx.Observer;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//import rx.subscriptions.CompositeSubscription;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGetInfoBt;
    private TextView mInfoTv;
    private ImageView mImageView;

    private static String requestUrl = "https://api.github.com/";
    private static String ipUrl = "http://ip.taobao.com/service/";
    private static String locationUrl = "http://gc.ditu.aliyun" +
    ".com/geocoding";

    private static String downloadUrl = "http://gdown.baidu.com/data/wisegame/2eeee3831c9dbc42/QQ_374.apk";
    private static String downloadUrl2 = "http://server.jeasonlzy.com/OkHttpUtils/download";

    private static String imgUrl = "http://pic14.nipic.com/20110523/5239495_223340179147_2.jpg";


    private static String url = "http://10.75.52.143/login";
    private static String postUrl = url + "/huazhu.php";
    private static String upLoadUrl = url + "/upload.php";

    private static final String TAG = "PengLog";
    private static String upFile = "/temp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        x.view().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
            }
        });
        initView();
    }

    private void initView() {
        mGetInfoBt = (Button) findViewById(R.id.getInfo);
        mInfoTv = (TextView) findViewById(R.id.infoTv);
        mGetInfoBt.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.img);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getInfo:
                xGet();
                loadImage();
                xDownload();
//                doGetInfo();
//                doPost();
//                doUpload();
                break;

            default:
                break;
        }
    }

    File file = new File(Environment.getExternalStorageDirectory().getPath
    () + upFile);

    private void xGet() {
        RequestParams params = new RequestParams(locationUrl);
        params.addQueryStringParameter("a", "上海");
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>
        () {
            @Override
            public void onSuccess(String result) {
                LogUtils.logD("onSuccess: " + result);
                mInfoTv.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void loadImage() {
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).setCircular(true)
        .setUseMemCache(true).build();
        x.image().bind(mImageView, imgUrl, options);
    }

    private void xDownload() {
        OkHttpUtils.get(downloadUrl2)
        .tag(this)
        .execute(new DownloadFileCallBack(Environment.getExternalStorageDirectory() + "/temp",
        "xxx" + ".apk") {
        });


        RequestParams params = new RequestParams(downloadUrl2);
        params.setSaveFilePath(Environment.getExternalStorageDirectory().getPath
        () + upFile);
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                LogUtils.logD("onSuccess");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.logD("onError: " + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.logD("onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.logD("onFinished");
            }

            @Override
            public void onWaiting() {
                LogUtils.logD("onWaiting");
            }

            @Override
            public void onStarted() {
                LogUtils.logD("onStarted");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                LogUtils.logD("total: " + total + "   current: " + current);
            }
        });
    }


//    private void doPost() {
//        OkHttpUtils.post(postUrl)
//        .tag(this)//
//        .params("username", "zhansan")
//        .params("password", "xd23")
//        .execute(new StringCallback() {
//            @Override
//            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
//                Log.d(TAG, "s: " + s);
//            }
//        });
//    }

//    private void doUpload() {
//        if (file.exists()) {
//            Log.d(TAG,"exist");
//            OkHttpUtils.post(upLoadUrl)//
//            .tag(this)//
//            .params("uploadedfile", file)
//            .execute(new ProgressUpCallBack<>(this, RequestInfo.class));
//        }
//        uploadFileBy(upLoadUrl,Environment.getExternalStorageDirectory().getPath
//        () + upFile);
//    }
//    private int lastWriten = 0;
//
//    public void uploadFileBy(String uploadUrl, String uploadFilePath) {
//        try {
//            AsyncHttpClient client = new AsyncHttpClient();
//
//            RequestParams params = new RequestParams();
//            params.put("uploadedfile", new File(uploadFilePath));
//
//            client.post(this, uploadUrl, params, new AsyncHttpResponseHandler() {
//
//                @Override
//                protected Message obtainMessage(int arg0, Object arg1) {
//                    return super.obtainMessage(arg0, arg1);
//                }
//
//                @Override
//                public void onProgress(int bytesWritten, int totalSize) {
//                    if (bytesWritten * 100 / totalSize > (lastWriten * 100 / totalSize)) {
//                        Log.d(TAG, " ****  " + bytesWritten * 100 / totalSize);
//                    }
//                }
//
//                @Override
//                public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//                }
//
//                @Override
//                public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//                    lastWriten = 0;
//                    Log.d(TAG, "onSuccess");
//
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


//    private class ProgressUpCallBack<T> extends JsonCallback<T> {
//
//        public ProgressUpCallBack(Activity activity, Class<T> clazz) {
//            super(clazz);
//        }
//
//        @Override
//        public void onBefore(BaseRequest request) {
//            super.onBefore(request);
//            Log.d(TAG, "start");
//        }
//
//        @Override
//        public void onResponse(boolean isFromCache, T s, Request request, Response response) {
////            handleResponse(isFromCache, s, request, response);
//            Log.d(TAG, "end");
//        }
//
//        @Override
//        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
//            super.onError(isFromCache, call, response, e);
////            handleError(isFromCache, call, response);
////            btnFormUpload.setText("上传出错");
//            Log.d(TAG, "error");
//        }
//
//        @Override
//        public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//            System.out.println("upProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);
//
//            String downloadLength = Formatter.formatFileSize(getApplicationContext(), currentSize);
//            String totalLength = Formatter.formatFileSize(getApplicationContext(), totalSize);
//            Log.d(TAG, downloadLength + "/" + totalLength);
//            String netSpeed = Formatter.formatFileSize(getApplicationContext(), networkSpeed);
//        }
//    }
//
//    private CompositeSubscription mSubscriptions = new CompositeSubscription();


//    private void doGetInfo() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(locationUrl).build();
//        GithabApi repo = retrofit.create(GithabApi.class);
//        Call<ResponseBody> call = repo.contributorsBySimpleGetCall("square","retrofit");
//        Call<ResponseBody> call2 = repo.getIpCall("180.166.53.20");
//            Call<ResponseBody> call3 = repo.getLocation("苏州");
//
//        call3.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//                    if(response.body()!=null){
//                        mInfoTv.setText(response.body().string());
//                    }
//                    else{
//                        Snackbar.make(mInfoTv, "get null", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Snackbar.make(mInfoTv, "error", Snackbar.LENGTH_SHORT)
//                .setAction("Action", null).show();
//            }
//        });

//        Retrofit retrofit = new Retrofit.Builder()
//        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(locationUrl)
//        .build();
//        GithabApi repo = retrofit.create(GithabApi.class);
//        mSubscriptions.add(repo.getLocationByRxJava("苏州").subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Location>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onNext(Location location) {
//                mInfoTv.setText(location.getLon()+"");
//            }
//        }));

//        OkHttpUtils.get(locationUrl + "geocoding?a=苏州")
//        .tag(this)
//        .cacheKey("cacheKey")
//        .cacheMode(CacheMode.DEFAULT)
//        .execute(new StringCallback() {
//            @Override
//            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
//                Gson gson = new Gson();
//                Location location = gson.fromJson(s, Location.class);
//                mInfoTv.setText(location.getLon() + " " + location.getLat());
//            }
//        });
//
//        Picasso.with(this).load(imgUrl).into(mImageView);
//
//
//

    //    }
//
    private class DownloadFileCallBack extends FileCallback {

        public DownloadFileCallBack(String destFileDir, String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void onBefore(BaseRequest request) {
            LogUtils.logD("onBefore");
        }

        @Override
        public void onResponse(boolean isFromCache, File file, Request request, Response response) {
            LogUtils.logD("onResponse" + response.message());
        }

        @Override
        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            LogUtils.logD("downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress +
            "  " + networkSpeed);
        }

        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
            LogUtils.logD("onError");
            super.onError(isFromCache, call, response, e);

        }
    }


}
