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
import com.example.dbmsproject.databinding.ActivityCartBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import delieveryAgent.agentLogin;
import delieveryAgent.myDeliveries;
import delieveryAgent.myDeliveriesAdapter;

public class Cart extends AppCompatActivity {

    public static ArrayList<cartModel> itemlist = new ArrayList<>();
    ActivityCartBinding binding;
    public static String customerphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("cart", String.valueOf(itemlist.size()));
        customerphone=customerHome1.CPHONE;
        binding.cartRV.setLayoutManager(new LinearLayoutManager(Cart.this));
        binding.cartRV.setAdapter(new cartAdapter(itemlist, getApplicationContext()));
        int totalprice=0;
        if(itemlist.size()>0
        ){
            for (int i=0;i<itemlist.size();i++){
                String totalpprice=itemlist.get(i).getPprice();
                String totapquantity=itemlist.get(i).getPquantity();
                totalprice+= Integer.parseInt(totalpprice)*Integer.parseInt(totapquantity);

            }
        }
        binding.totalcost.setText("Total Cost: "+totalprice);


        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<itemlist.size();i++) {
                    RequestQueue queue = Volley.newRequestQueue(Cart.this);
                    String link = ApiLink.link;
                    String url = link + "insertOrder.php";

                    Random rand = new Random();

                    // Generate random integer between 10000 and 99999
                    int randomNum = rand.nextInt(90000) + 10000;
                    String oid = String.valueOf(randomNum);
                    Log.d("OOID",oid);
                    Log.d("cccccppppphhhooonneee",customerphone);
                    int tcost=Integer.parseInt(itemlist.get(i).pprice)*Integer.parseInt(itemlist.get(i).pquantity);
                    String ocost = String.valueOf(tcost);
                    String ostatus = "ordered";
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = dateFormat.format(currentDate);
                    String odate = dateString;
                    String oquantity = itemlist.get(i).getPquantity();
                    String pid = itemlist.get(i).getPid();
                    String opaymode = "COD";

                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the response message
                                    Log.d("RESPONSE",response);
                                    Log.d("checkout","checkout successfully");
                                    Toast.makeText(Cart.this, "Order has been Placed Successfully", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Display the error message

                                    Log.d("ERRORRESPONSE",error.toString());

                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            // Set the POST parameters with the user input data
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("phno",customerphone);
                            params.put("oid", oid);
                            params.put("ocost", ocost);
                            params.put("ostatus", ostatus);
                            params.put("odate", odate);
                            params.put("oquantity", oquantity);
                            params.put("pid", pid);
                            params.put("opaymode", opaymode);
                            return params;
                        }
                    };
                    queue.add(postRequest);
                }

            }
        });


    }


}