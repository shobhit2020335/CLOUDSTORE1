package seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
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
import com.example.dbmsproject.databinding.ActivityMyProductsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import delieveryAgent.agentLogin;
import delieveryAgent.myDeliveries;
import delieveryAgent.myDeliveriesAdapter;

public class myProducts extends AppCompatActivity {


    ArrayList<myProductsModel> list = new ArrayList<>();
    ActivityMyProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RequestQueue queue1 = Volley.newRequestQueue(myProducts.this);
        String link = ApiLink.link;
        String url = link + "myProducts.php";
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
                                    String pid = jsonObject.getString("PID");
                                    String pname = jsonObject.getString("PName");
                                    String pprice = jsonObject.getString("PPrice");
                                    String pquantity = jsonObject.getString("PQuantity");
                                    myProductsModel model = new myProductsModel(pid, pname, pprice, pquantity);
                                    list.add(model);

//                                    String qstatus = jsonObject.getString("Qstatus");


                                }
                                binding.myproductsRV.setLayoutManager(new LinearLayoutManager(myProducts.this));
                                binding.myproductsRV.setAdapter(new myProductsAdapter(list, getApplicationContext()));


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
                params.put("phno",sellerLogin.sphone );
                return params;
            }
        };
        queue1.add(postRequest1);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(myProducts.this,sellerHome.class));
    }
}