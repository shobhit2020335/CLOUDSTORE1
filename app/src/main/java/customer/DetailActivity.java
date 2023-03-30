package customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerHome1Binding;
import com.example.dbmsproject.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
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
                Toast.makeText(DetailActivity.this, "Ordered Has Been Placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(binding.getRoot());
    }
}