package Employee;

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
import com.example.dbmsproject.databinding.ActivitySolveCustomerQueriesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import customer.Query;
import customer.customerHome1;
import customer.myQueriesAdapter;
import customer.myQueriesModel;

public class solveCustomerQueries extends AppCompatActivity {
    ActivitySolveCustomerQueriesBinding binding;
    ArrayList<solvecustomerqueryModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySolveCustomerQueriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RequestQueue queue1 = Volley.newRequestQueue(solveCustomerQueries.this);
        String link = ApiLink.link;
        String url = link + "solvecustomerqueries.php";

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
                                    String cphone = jsonObject.getString("CPhone");
                                    String qid = jsonObject.getString("QID");
                                    String qtext = jsonObject.getString("Qtext");
                                    String qstatus = jsonObject.getString("Qstatus");
                                    solvecustomerqueryModel model = new solvecustomerqueryModel(cphone, qid, qtext, qstatus);
                                    arrayList.add(model);

                                }
                                binding.cqueryRV.setLayoutManager(new LinearLayoutManager(solveCustomerQueries.this));
                                binding.cqueryRV.setAdapter(new solveCustomerQueriesAdapter(arrayList, getApplicationContext()));


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
}