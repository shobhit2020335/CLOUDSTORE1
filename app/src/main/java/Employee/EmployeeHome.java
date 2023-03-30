package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityEmployeeHomeBinding;

import java.util.ArrayList;

public class EmployeeHome extends AppCompatActivity{
    ActivityEmployeeHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEmployeeHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        String nam1=intent.getStringExtra("name1");
        String phone1=intent.getStringExtra("phone1");
        String count1=intent.getStringExtra("count");

        binding.name1.setText("CUSTOMER NAME: "+nam1);
        binding.phone1.setText("PHONE NO: "+phone1);
        binding.count1.setText("TOTAL ORDERS: "+count1);
    }


}