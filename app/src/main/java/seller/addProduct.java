package seller;

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
import com.example.dbmsproject.databinding.ActivityAddProductBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import customer.DetailActivity;

public class addProduct extends AppCompatActivity {

    ActivityAddProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String phno = intent.getStringExtra("phno");
//        Log.d("sellerphno233",phno);
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Log.d("sellerphno233",phno);
                RequestQueue queue = Volley.newRequestQueue(addProduct.this);
                String link = ApiLink.link;
                String url = link + "addProduct.php";

                Random rand = new Random();

                // Generate random integer between 10000 and 99999
                int randomNum = rand.nextInt(9000) + 1000;
                String pname = binding.pname.getText().toString();
                String pprice = binding.pprice.getText().toString();
                String pquantity = binding.pquantity.getText().toString();
                String pcategory = binding.pcategory.getText().toString();
                String pid = String.valueOf(randomNum);

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response message
                                Log.d("RESPONSE", response);
                                Toast.makeText(addProduct.this, "Product added Successfully", Toast.LENGTH_SHORT).show();
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
                        params.put("phno", phno);
                        params.put("pname", pname);
                        params.put("pid", pid);
                        params.put("pprice", pprice);
                        params.put("pquantity", pquantity);
                        params.put("pcategory", pcategory);
                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });

    }
}