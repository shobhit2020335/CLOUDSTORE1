package customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityQueryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import seller.addProduct;

public class Query extends AppCompatActivity {

    ActivityQueryBinding binding;
    ArrayList<myQueriesModel> modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQueryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RequestQueue queue1 = Volley.newRequestQueue(Query.this);
        String link = ApiLink.link;
        String url = link + "myqueries.php";

        StringRequest postRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.length()>0) {
                            Log.d("RESPONSE", response);


//                                Toast.makeText(CustomerProfile.this, "Phone number passed", Toast.LENGTH_SHORT).show();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String qid = jsonObject.getString("QID");
                                    String qtext = jsonObject.getString("Qtext");
                                    String qstatus = jsonObject.getString("Qstatus");
                                    myQueriesModel model = new myQueriesModel(qid, qtext, qstatus);
                                    modelArrayList.add(model);

                                }
                                binding.myqueriesRV.setLayoutManager(new LinearLayoutManager(Query.this));
                                binding.myqueriesRV.setAdapter(new myQueriesAdapter(modelArrayList, getApplicationContext()));



                            } catch (JSONException e) {
                                Log.d("error",e.toString());
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
                params.put("phno", customerHome1.CPHONE);
                return params;
            }
        };
        queue1.add(postRequest1);



        binding.submitquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qtext = binding.newquery.getText().toString();
                String qstatus = "Not answered";
                String eid = "1127032143";
                String cphone = customerHome1.CPHONE;
                Random rand = new Random();

                // Generate random integer between 10000 and 99999
                int randomNum = rand.nextInt(9000) + 1000;
                String qid = String.valueOf(randomNum);
                RequestQueue queue = Volley.newRequestQueue(Query.this);
                String url1 = ApiLink.link + "customerquery.php";

                StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response message
                                Log.d("RESPONSE", response);
                                Toast.makeText(Query.this, "Query Submitted Successfully", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Display the error message

                                Log.d("ERRORRESPONSE", error.toString());

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Set the POST parameters with the user input data
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("phno", cphone);
                        params.put("qid", qid);
                        params.put("eid", eid);
                        params.put("qtext", qtext);
                        params.put("qstatus", qstatus);

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
    }
}