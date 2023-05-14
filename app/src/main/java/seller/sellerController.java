package seller;


import com.example.dbmsproject.ApiLink;

import customer.UserServiceInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sellerController {
    static String link= ApiLink.link;
    public  static final String URL_LOGIN=link+"sellerlogin.php/";

    private static sellerController clienobject;
    private static Retrofit retrofit;
    sellerController()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(URL_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized sellerController getInstance() {
        if (clienobject == null)
            clienobject = new sellerController();
        return clienobject;
    }
    UserServiceInterface getapi()
    {
        return retrofit.create(UserServiceInterface.class);
    }
}
