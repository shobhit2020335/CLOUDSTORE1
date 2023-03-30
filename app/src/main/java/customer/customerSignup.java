package customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerSignupBinding;

public class customerSignup extends AppCompatActivity {

    ActivityCustomerSignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginbtnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerSignup.this,customerLogin.class);
                startActivity(intent);
            }
        });
    }
}