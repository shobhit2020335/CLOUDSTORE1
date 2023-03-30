package customer;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserServiceInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<responseModel> verifyuser(
            @Field("phno")String phno,
            @Field("password") String password
    );
}
