package delieveryAgent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityMyDeliveriesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myDeliveries extends AppCompatActivity {

    ActivityMyDeliveriesBinding binding;
    ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMyDeliveriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RequestQueue queue1 = Volley.newRequestQueue(myDeliveries.this);
        String link = ApiLink.link;
        String url = link + "myDeliveries.php";

        StringRequest postRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.length() > 0) {
                            Log.d("RESPONSE", response);


//                                Toast.makeText(CustomerProfile.this, "Phone number passed", Toast.LENGTH_SHORT).show();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String oid = jsonObject.getString("OID");
                                    list.add(oid);

//                                    String qstatus = jsonObject.getString("Qstatus");


                                }
                                binding.mydeliveriesRV.setLayoutManager(new LinearLayoutManager(myDeliveries.this));
                                binding.mydeliveriesRV.setAdapter(new myDeliveriesAdapter(list, getApplicationContext()));


                            } catch (JSONException e) {
                                Log.d("error", e.toString());
                            }
                        }
//                        Log.d("myorderslist", String.valueOf(myOrdersModelArrayList.size()));
//                        Intent intent1 = new Intent(CustomerProfile.this, CustomerOrders.class);
//                        startActivity(intent1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("ERRORRESPONSE", error.toString());

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("did", agentLogin.DID);
                return params;
            }
        };
        queue1.add(postRequest1);

    }
}