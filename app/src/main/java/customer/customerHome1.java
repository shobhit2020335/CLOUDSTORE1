package customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.dbmsproject.MainActivity;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerHome1Binding;
import com.example.dbmsproject.databinding.FragmentCustomerHomeBinding;

public class customerHome1 extends AppCompatActivity {
    ActivityCustomerHome1Binding binding;
    private static final String apiurl = "http://192.168.44.208/api/productdata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerHome1Binding.inflate(getLayoutInflater());
        CustomerHome customerhome = new CustomerHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.chomu,customerhome).commit();
        setContentView(binding.getRoot());

        dbManager manager = new dbManager(apiurl,customerhome,getApplicationContext());
        manager.execute(apiurl);
    }
}