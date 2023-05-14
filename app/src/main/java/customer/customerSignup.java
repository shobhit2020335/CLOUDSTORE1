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
        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.inputnamesignup.getText().toString();
                String phno=binding.inputphonesignup.getText().toString();
                String pass=binding.inputpasswordsignup.getText().toString();
                Intent intent=new Intent(customerSignup.this,CustomerSignup2.class);
                intent.putExtra("name",name);
                intent.putExtra("phno",phno);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
        binding.loginbtnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerSignup.this,customerLogin.class);
                startActivity(intent);
            }
        });
    }
}