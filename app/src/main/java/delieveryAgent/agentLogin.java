package delieveryAgent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityAgentLoginBinding;

import java.util.HashMap;
import java.util.Map;

import Employee.employeeLogin;
import Employee.employeeMainpage;

public class agentLogin extends AppCompatActivity {

    ActivityAgentLoginBinding binding;
    public static String DID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAgentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkUser();
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(agentLogin.this);
                String link = ApiLink.link;
                String url = link + "agentlogin.php";
                String did = binding.inputphonelogin.getText().toString();
                String dpass = binding.inputpasswordlogin.getText().toString();

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response message
                                Log.d("RESPONSE", response);
                                if (response.equals("{\"message\":\"exist\"}")) {

                                    SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
//                                    Toast.makeText(employeeLogin.this, " Employee login successfully", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("did", binding.inputphonelogin.getText().toString());
                                    DID= binding.inputphonelogin.getText().toString();
                                    editor.putString("password", binding.inputpasswordlogin.getText().toString());
                                    editor.commit();
                                    editor.apply();
                                    Toast.makeText(agentLogin.this, "Delievery Agent login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(agentLogin.this, agentHome.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(agentLogin.this, "Delievery Agent Login Failed", Toast.LENGTH_SHORT).show();
                                }

//                                onBackPressed();
                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
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
                        params.put("did", did);
                        params.put("pass", dpass);
                        return params;
                    }
                };
                queue.add(postRequest);


            }
        });
    }

    void checkUser() {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("did")) {
            DID=sp.getString("did","");
            Intent intent = new Intent(getApplicationContext(), agentHome.class);
            intent.putExtra("did", sp.getString("did", ""));
            intent.putExtra("pass", sp.getString("password", ""));
            startActivity(intent);
        } else {
            Toast.makeText(this, "LOGIN AGAIN", Toast.LENGTH_SHORT).show();
        }
    }
}