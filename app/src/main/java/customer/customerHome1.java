package customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.MainActivity;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerHome1Binding;
import com.example.dbmsproject.databinding.FragmentCustomerHomeBinding;

public class customerHome1 extends AppCompatActivity {
    ActivityCustomerHome1Binding binding;
    public static String CPHONE;
    static String link= ApiLink.link;
    private static final String apiurl = link+"productdata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerHome1Binding.inflate(getLayoutInflater());
        Intent intent=getIntent();
        String phone=intent.getStringExtra("phno");
        String pass=intent.getStringExtra("pass");
        CPHONE=phone;
        Bundle bundle = new Bundle();
        bundle.putString("phno", phone);
        bundle.putString("pass", pass);
        CustomerHome customerhome = new CustomerHome();
        customerhome.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.chomu,customerhome).commit();
        setContentView(binding.getRoot());

        dbManager manager = new dbManager(apiurl,customerhome,getApplicationContext());
        manager.execute(apiurl);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(customerHome1.this,customerHome1.class));
    }
}