package Employee;

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
import com.example.dbmsproject.databinding.ActivityEmployeeLoginBinding;

import java.util.HashMap;
import java.util.Map;

import customer.responseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seller.addProduct;
import seller.sellerController;
import seller.sellerHome;
import seller.sellerLogin;

public class employeeLogin extends AppCompatActivity {

    ActivityEmployeeLoginBinding binding;
    public static String EID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkUser();

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(employeeLogin.this);
                String link = ApiLink.link;
                String url = link + "employeelogin.php";
                String eid = binding.inputphonelogin.getText().toString();
                String epass = binding.inputpasswordlogin.getText().toString();
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
                                    editor.putString("eid", binding.inputphonelogin.getText().toString());
                                    EID= binding.inputphonelogin.getText().toString();
                                    editor.putString("password", binding.inputpasswordlogin.getText().toString());
                                    editor.commit();
                                    editor.apply();
                                    Toast.makeText(employeeLogin.this, "Employee login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(employeeLogin.this, employeeMainpage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(employeeLogin.this, "Employee Login Failed", Toast.LENGTH_SHORT).show();
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
                        params.put("eid", eid);
                        params.put("pass", epass);
                        return params;
                    }
                };
                queue.add(postRequest);


            }
        });

    }

    public void onLogin() {
        String eid = binding.inputphonelogin.getText().toString();
        String password = binding.inputpasswordlogin.getText().toString();
        Call<responseModel> call = employeeController
                .getInstance()
                .getapi()
                .verifyuser(eid, password);
        call.enqueue(new Callback<responseModel>() {
            @Override
            public void onResponse(Call<responseModel> call, Response<responseModel> response) {
                responseModel obj = response.body();
                String output = obj.getMessage();
                if (output.equals("failed")) {
                    Toast.makeText(employeeLogin.this, "EMPLOYEE LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
                if (output.equals("exist")) {
                    SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                    Toast.makeText(employeeLogin.this, " Employee login successfully", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("eid", binding.inputphonelogin.getText().toString());
                    editor.putString("password", binding.inputpasswordlogin.getText().toString());
                    editor.commit();
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), EmployeeHome.class);
                    intent.putExtra("eid", binding.inputphonelogin.getText().toString());
                    intent.putExtra("pass", binding.inputpasswordlogin.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<responseModel> call, Throwable t) {
                Toast.makeText(employeeLogin.this, "something went wrong" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void checkUser() {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("eid")) {
            EID=sp.getString("eid","");
            Intent intent = new Intent(getApplicationContext(), employeeMainpage.class);
            intent.putExtra("eid", sp.getString("eid", ""));
            intent.putExtra("pass", sp.getString("password", ""));
            startActivity(intent);
        } else {
            Toast.makeText(this, "LOGIN AGAIN", Toast.LENGTH_SHORT).show();
        }
    }
}