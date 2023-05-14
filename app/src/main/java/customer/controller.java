package customer;

import com.example.dbmsproject.ApiLink;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class controller {
    static String link= ApiLink.link;
    public  static final String URL_LOGIN=link;

    private static controller clienobject;
    private static Retrofit retrofit;
    controller()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(URL_LOGIN)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }
    public static synchronized controller getInstance() {
        if (clienobject == null)
            clienobject = new controller();
        return clienobject;
    }
    UserServiceInterface getapi()
    {
        return retrofit.create(UserServiceInterface.class);
    }
}
