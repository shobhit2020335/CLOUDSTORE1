package customer;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.dbmsproject.databinding.ActivityCustomerSignup2Binding;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CustomerSignup2 extends AppCompatActivity {

    ActivityCustomerSignup2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerSignup2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phno = intent.getStringExtra("phno");
        String pass = intent.getStringExtra("pass");
        binding.signupbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.inputmailsignup.getText().toString();
                String address = binding.inputaddresssignup.getText().toString();
                String dob = binding.inputdobsignup.getText().toString();
                Calendar now = Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int inputyear=Integer.parseInt(dob.substring(0,4));
                String age=String.valueOf(year-inputyear);
                RequestQueue queue = Volley.newRequestQueue(CustomerSignup2.this);
                String link = ApiLink.link;
                String url = link + "signup.php";


                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response message
                                Log.d("RESPONSE", response);
                                Toast.makeText(CustomerSignup2.this, "SIGNUP SUCCESSFULLY. KINDLY LOGIN", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(CustomerSignup2.this, customerLogin.class);
                                startActivity(intent1);
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
                        params.put("name", name);
                        params.put("phno", phno);
                        params.put("mail", email);
                        params.put("address", address);
                        params.put("pass", pass);
                        params.put("dob", dob);
                        params.put("age", age);
//                        params.put("pid", pid);
//                        params.put("opaymode", opaymode);
                        return params;
                    }
                };
                queue.add(postRequest);

//        }


            }
        });

    }
}