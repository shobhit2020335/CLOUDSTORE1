package customer;

import static customer.customerHome1.CPHONE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerHome1Binding;
import com.example.dbmsproject.databinding.ActivityDetailBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    public static String customerphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        customerphone=customerHome1.CPHONE;
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productID = intent.getStringExtra("productID");
        String productCategory = intent.getStringExtra("productCategory");
        String productPrice = intent.getStringExtra("productPrice");

        binding.textView7.setText(productID);
        binding.textView8.setText(productName);
        binding.textView9.setText(productCategory);
        binding.textView10.setText(productPrice);

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(DetailActivity.this, "Ordered Has Been Placed Successfully", Toast.LENGTH_SHORT).show();
                binding.textView11.setVisibility(View.VISIBLE);
                binding.button.setVisibility(View.VISIBLE);

            }
        });
//        if (binding.button.getVisibility() == View.VISIBLE) {
            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    RequestQueue queue = Volley.newRequestQueue(DetailActivity.this);
                    String link = ApiLink.link;
                    String url = link + "insertOrder.php";

                    Random rand = new Random();

                    // Generate random integer between 10000 and 99999
                    int randomNum = rand.nextInt(90000) + 10000;
                    String oid = String.valueOf(randomNum);
                    Log.d("OOID",oid);
                    Log.d("cccccppppphhhooonneee",customerphone);
                    String ocost = productPrice;
                    String ostatus = "ordered";
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = dateFormat.format(currentDate);
                    String odate = dateString;
                    String oquantity = "1";
                    String pid = productID;
                    String opaymode = binding.textView11.getText().toString();

                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the response message
                                    Log.d("RESPONSE",response);
                                    Toast.makeText(DetailActivity.this, "Order has been Placed Successfully", Toast.LENGTH_SHORT).show();
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
            });
//        }

        binding.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("Enter Product Quantity");
                Log.d("clicked1",":clicked");
                final EditText input = new EditText(DetailActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        cartModel model=new cartModel(productID,productPrice,productName,text);
                        Cart.itemlist.add(model);
                        Toast.makeText(DetailActivity.this, "Item added to cart successfully", Toast.LENGTH_SHORT).show();
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

    }

}