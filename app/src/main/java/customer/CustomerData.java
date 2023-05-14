package customer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.dbmsproject.ApiLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CustomerData extends AsyncTask<String, Void, ArrayList<String>> {
    static String lin = ApiLink.link;
    TextView name;
    TextView mail;
    TextView dob;
    TextView address;


    public static final String link = lin + "customerdata.php";

    Context context;


    public CustomerData() {
    }

    public CustomerData(TextView name, TextView mail, TextView dob, TextView address, Context context) {
        this.name = name;
        this.mail = mail;
        this.dob = dob;
        this.address = address;
        this.context = context;
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {

        String phoneNumber = strings[0];
        String url = link;

        try {
            // Create a new HTTP client and request
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phno", phoneNumber)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            // Execute the request and retrieve the response body as a string
            Response response = client.newCall(request).execute();
            String json = response.body().string();

            // Parse the JSON and create a User object
            JSONArray array = new JSONArray(json);
            if (array.length() > 0) {
                JSONObject jsonObject = array.getJSONObject(0);
                String cname = jsonObject.getString("CName");
                String cmail = jsonObject.getString("CMail");
                String cAddress = jsonObject.getString("CAddress");
                String cdob = jsonObject.getString("CDOB");
                Log.d("established", "connectedsss3");
                Log.d("customerdataaaaa", cAddress);
                Log.d("customerdataaaaa", cname);
                Log.d("customerdataaaaa", cmail);
                Log.d("customerdataaaaa", cdob);
                ArrayList<String> data = new ArrayList<>();
                data.add(cname);
                data.add(cmail);
                data.add(cAddress);
                data.add(cdob);
                return data;

//
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        name.setText(s.get(0));
        mail.setText(s.get(1));
        address.setText(s.get(2));
        dob.setText(s.get(3));


    }
}
