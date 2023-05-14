package delieveryAgent;

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
import com.example.dbmsproject.databinding.ActivityAgentUndeliveredOrdersBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Employee.solveCustomerQueries;
import Employee.solveCustomerQueriesAdapter;
import Employee.solvecustomerqueryModel;

public class agentUndeliveredOrders extends AppCompatActivity {

    ActivityAgentUndeliveredOrdersBinding binding;
    ArrayList<undeliveredOrdersModel> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAgentUndeliveredOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RequestQueue queue1 = Volley.newRequestQueue(agentUndeliveredOrders.this);
        String link = ApiLink.link;
        String url = link + "undeliveredOrders.php";

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
                                    String ocost = jsonObject.getString("OCost");
                                    String ostatus = jsonObject.getString("OStatus");
                                    String opaymode = jsonObject.getString("OPaymode");
                                    String cname = jsonObject.getString("CName");
                                    String cphomne = jsonObject.getString("CPhone");
                                    String caddress = jsonObject.getString("CAddress");
//                                    String qstatus = jsonObject.getString("Qstatus");
                                    undeliveredOrdersModel model = new undeliveredOrdersModel(oid, ocost, ostatus,opaymode, cname,cphomne,caddress);
                                    arrayList.add(model);

                                }
                                binding.undeliiveredOrdersRV.setLayoutManager(new LinearLayoutManager(agentUndeliveredOrders.this));
                                binding.undeliiveredOrdersRV.setAdapter(new undeliveredOrdersAdapter(arrayList, getApplicationContext()));


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
//                params.put("phno", customerHome1.CPHONE);
                return params;
            }
        };
        queue1.add(postRequest1);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(agentUndeliveredOrders.this,agentHome.class));
    }
}