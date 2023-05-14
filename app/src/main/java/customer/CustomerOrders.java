package customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityCustomerOrdersBinding;

public class CustomerOrders extends AppCompatActivity {

    ActivityCustomerOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCustomerOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String l=String.valueOf(CustomerProfile.myOrdersModelArrayList.size());
        Log.d("arraylistsize",l);
        binding.orderRV.setLayoutManager(new LinearLayoutManager(this));
        binding.orderRV.setAdapter(new myOrdersAdapter(CustomerProfile.myOrdersModelArrayList,getApplicationContext()));

    }
}