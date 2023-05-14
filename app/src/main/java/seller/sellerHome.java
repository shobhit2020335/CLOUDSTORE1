package seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivitySellerHomeBinding;

import customer.customerHome1;

public class sellerHome extends AppCompatActivity {

    ActivitySellerHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySellerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        String phno=intent.getStringExtra("phno");

//        Log.d("sellerphno",phno);
        binding.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(sellerHome.this,addProduct.class);
                intent1.putExtra("phno",phno);
                startActivity(intent1);
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(sellerHome.this,myProducts.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(sellerHome.this,sellerHome.class));
    }
}