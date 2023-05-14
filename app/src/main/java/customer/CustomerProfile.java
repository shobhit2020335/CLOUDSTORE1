package customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerProfileBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Employee.olap1model;
import okhttp3.OkHttpClient;
import seller.addProduct;

public class CustomerProfile extends AppCompatActivity {

    ActivityCustomerProfileBinding binding;
    public static boolean isLoggedin=true;
    String newAddress;
    public  static  ArrayList<myOrdersModel> myOrdersModelArrayList=new ArrayList<>();
    public TextView name;
    public TextView mail;
    public TextView dob;
    public TextView address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCustomerProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        name = (TextView) findViewById(R.id.profileName);
        mail = (TextView) findViewById(R.id.profileMail);
        dob = (TextView) findViewById(R.id.dobProfile);
        address = (TextView) findViewById(R.id.addressProfile);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phno");
        String pass = intent.getStringExtra("pass");
        CustomerData customerData = new CustomerData(name, mail, dob, address, this);
        customerData.execute(phone);
        Log.d("phoneeeeee", phone);

        binding.phoneProfile.setText(phone);
        binding.passwordProfile.setText(pass);

        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked",":clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerProfile.this);
                builder.setTitle("Enter New Address");
                Log.d("clicked1",":clicked");
                final EditText input = new EditText(CustomerProfile.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
//                builder.show();


                Log.d("clicked2",":clicked");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
//                        newAddress = text;
                        Log.d("new address", text);
                        RequestQueue queue = Volley.newRequestQueue(CustomerProfile.this);
                        String link = ApiLink.link;
                        String url = link + "changeAddress.php";
                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the response message
                                        Log.d("RESPONSE", response);
                                        Toast.makeText(CustomerProfile.this, "Address Updated Successfully", Toast.LENGTH_SHORT).show();
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
                                params.put("phno", phone);
                                params.put("address", text);
                                return params;
                            }
                        };
                        queue.add(postRequest);



//                        Toast.makeText(CustomerProfile.this, "Address updated successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }

        });

        binding.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(CustomerProfile.this);
                String link = ApiLink.link;
                String url = link + "myOrders.php";

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("RESPONSE", response);

//                                Toast.makeText(CustomerProfile.this, "Phone number passed", Toast.LENGTH_SHORT).show();
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String oid = jsonObject.getString("OID");
                                        String oCost = jsonObject.getString("OCost");
                                        String oStatus = jsonObject.getString("OStatus");
                                        String oDate = jsonObject.getString("ODate");
                                        String oQuantity = jsonObject.getString("OQuantity");
                                        String pid = jsonObject.getString("PID");
                                        String pName = jsonObject.getString("PName");
                                        String oPaymode = jsonObject.getString("OPaymode");
                                        myOrdersModel model=new myOrdersModel(oid,oCost,oStatus,oDate,oQuantity,pid,pName,oPaymode);
                                        myOrdersModelArrayList.add(model);
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                Log.d("myorderslist",String.valueOf(myOrdersModelArrayList.size()));
                                Intent intent1=new Intent(CustomerProfile.this,CustomerOrders.class);
                                startActivity(intent1);
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
                        params.put("phno", phone);
                        return params;
                    }
                };
                queue.add(postRequest);


            }
        });


        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                String logoutlink=ApiLink.link+"logout.php";
                RequestQueue queue = Volley.newRequestQueue(CustomerProfile.this);

// Define the URL of the PHP script that handles logout
//                String url = "http://yourserver.com/logout.php";

// Create a new StringRequest to send a POST request to the URL
                StringRequest stringRequest = new StringRequest(Request.Method.POST, logoutlink,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the response from the server (if any)Log
                                isLoggedin=false;
                                Toast.makeText(CustomerProfile.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CustomerProfile.this,customerLogin.class));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error (if any)
                    }
                }) {
                    // Override getParams() to add any POST parameters (if needed)
                    @Override
                    protected Map<String,String> getParams() {
                        Map<String,String> params = new HashMap<String, String>();
                        // Add any POST parameters here (if needed)
                        return params;
                    }
                };

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerProfile.this,Cart.class));
            }
        });
    }
}