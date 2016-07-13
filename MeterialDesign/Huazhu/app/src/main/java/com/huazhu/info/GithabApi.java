//package com.huazhu.info;
//
//import com.huazhu.info.bean.Location;
//import com.huazhu.info.bean.WeatherBean;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Path;
//import retrofit2.http.Query;
//import rx.Observable;
//
///**
// * Created by uiprj on 7/6/16.
// */
//public interface GithabApi {
//
//    @GET("repos/{owner}/{repo}/contributors")
//    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo")
//    String repo);
//
//
//    @GET("getIpInfo.php")
//    Call<ResponseBody> getIpCall(@Query("ip") String ip);
//
//    @GET("geocoding")
//    Call<ResponseBody> getLocation(@Query("a") String cityName);
//
//
//    @GET("getIpInfo.php")
//    Observable<WeatherBean> getIpCallByRxJava(@Query("ip") String ip);
//
//    @GET("geocoding")
//    Observable<Location> getLocationByRxJava(@Query("a") String cityName);
//}
