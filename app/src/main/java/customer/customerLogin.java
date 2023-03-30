package customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class customerLogin extends AppCompatActivity {

    ActivityCustomerLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkUser();
        binding.signupbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerLogin.this,customerSignup.class);
                startActivity(intent);
            }
        });
    }

    public void onLogin(View view){
        String phno=binding.inputphonelogin.getText().toString();
        String password=binding.inputpasswordlogin.getText().toString();
       Call<responseModel> call=controller
               .getInstance()
               .getapi()
               .verifyuser(phno,password);
       call.enqueue(new Callback<responseModel>() {
           @Override
           public void onResponse(Call<responseModel> call, Response<responseModel> response) {
               responseModel obj=response.body();
               String output=obj.getMessage();
               if (output.equals("failed")){
                   Toast.makeText(customerLogin.this, "idk what happens", Toast.LENGTH_SHORT).show();
               }
               if(output.equals("exist")){
                   SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
                   Toast.makeText(customerLogin.this,"login successfully", Toast.LENGTH_SHORT).show();
                   SharedPreferences. Editor editor=sp.edit();
                   editor.putString( "phno", binding.inputphonelogin.getText().toString());
                   editor.putString( "password", binding.inputpasswordlogin.getText().toString());
                   editor.commit();
                   editor.apply();
                   startActivity(new Intent(getApplicationContext(),customerHome1.class));
                   finish();
               }
           }

           @Override
           public void onFailure(Call<responseModel> call, Throwable t) {
               Toast.makeText(customerLogin.this, "something went wrong"+t.toString(), Toast.LENGTH_SHORT).show();
           }
       });
    }
    void checkUser(){
        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp. contains ("phno"))
            startActivity (new Intent(getApplicationContext(), customerHome1.class));
        else {
            Toast.makeText(this, "LOGIN AGAIN", Toast.LENGTH_SHORT).show();
        }
    }
}