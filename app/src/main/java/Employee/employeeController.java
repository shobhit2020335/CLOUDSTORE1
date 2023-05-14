package Employee;

import com.example.dbmsproject.ApiLink;

import customer.UserServiceInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import seller.sellerController;

public class employeeController {
    static String link= ApiLink.link;
    public  static final String URL_LOGIN=link+"employeelogin.php/";

    private static employeeController clienobject;
    private static Retrofit retrofit;
    employeeController()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(URL_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized employeeController getInstance() {
        if (clienobject == null)
            clienobject = new employeeController();
        return clienobject;
    }
    UserServiceInterface getapi()
    {
        return retrofit.create(UserServiceInterface.class);
    }
}
