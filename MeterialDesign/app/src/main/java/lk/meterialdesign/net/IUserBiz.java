package lk.meterialdesign.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by uiprj on 6/30/16.
 */
public interface IUserBiz {

    @POST("add")
    Call<List<User>> addUsers(@Body User user);
}
