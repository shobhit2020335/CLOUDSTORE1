package seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivitySellerLoginBinding;

import customer.controller;
import customer.customerHome1;
import customer.customerLogin;
import customer.responseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sellerLogin extends AppCompatActivity {

    ActivitySellerLoginBinding binding;
    public  static  String sphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkUser();
    }

    public void onLogin(View view) {
        String phno = binding.inputphonelogin.getText().toString();
        String password = binding.inputpasswordlogin.getText().toString();
        Call<responseModel> call = sellerController
                .getInstance()
                .getapi()
                .verifyuser(phno, password);
        call.enqueue(new Callback<responseModel>() {
            @Override
            public void onResponse(Call<responseModel> call, Response<responseModel> response) {
                responseModel obj = response.body();
                String output = obj.getMessage();
                if (output.equals("failed")) {
                    Toast.makeText(sellerLogin.this, "SELLER LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
                if (output.equals("exist")) {
                    SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                    Toast.makeText(sellerLogin.this, " seller login successfully", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("phno", binding.inputphonelogin.getText().toString());
                    sphone= binding.inputphonelogin.getText().toString();
                    editor.putString("password", binding.inputpasswordlogin.getText().toString());
                    editor.commit();
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), sellerHome.class);
                    intent.putExtra("phno", binding.inputphonelogin.getText().toString());
                    intent.putExtra("pass", binding.inputpasswordlogin.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<responseModel> call, Throwable t) {
                Toast.makeText(sellerLogin.this, "something went wrong" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void checkUser() {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("phno")) {

            sphone=sp.getString("phno","");
            Intent intent = new Intent(getApplicationContext(), sellerHome.class);
            intent.putExtra("phno", sp.getString("phno", ""));
            intent.putExtra("pass", sp.getString("password", ""));
            startActivity(intent);
        } else {
            Toast.makeText(this, "LOGIN AGAIN", Toast.LENGTH_SHORT).show();
        }
    }

}