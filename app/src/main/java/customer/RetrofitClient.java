package customer;

import com.example.dbmsproject.ApiLink;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static String link= ApiLink.link;

    public  static final String URL_LOGIN=link;
    private static Retrofit retrofit=null;
    public static Retrofit getRetrofitInstance(){
        if (retrofit==null) {
            retrofit=new Retrofit.Builder()
                    .baseUrl(URL_LOGIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }
}
