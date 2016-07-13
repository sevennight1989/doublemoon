package lk.meterialdesign.net;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.io.File;
import java.util.List;

import lk.meterialdesign.R;
import lk.meterialdesign.ioc.ContentView;
import lk.meterialdesign.ioc.ViewInjectUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@ContentView(value = R.layout.activity_net)
public class NetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.init(this);

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("").addConverterFactory
//        (GsonConverterFactory.create()).build();
//        IUserBiz userBiz = retrofit.create(IUserBiz.class);
//        Call<List<User>> call = userBiz.addUsers(new User());
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//
//            }
//        });
//
//        File file = new File(Environment.getExternalStorageDirectory(),"");
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"),file);
//        MultipartBody.Part photo = MultipartBody.Part.createFormData("","",requestBody);


    }


}
