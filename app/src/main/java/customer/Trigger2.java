package customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityTrigger1Binding;
import com.example.dbmsproject.databinding.ActivityTrigger2Binding;
import com.example.dbmsproject.databinding.Trigger2dataBinding;

import java.util.ArrayList;

import Orders.OrderStatus;
import Product.ProductCount;

public class Trigger2 extends AppCompatActivity {
    ActivityTrigger2Binding binding;
    Trigger2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrigger2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<OrderStatus> object = (ArrayList<OrderStatus>) args.getSerializable("ARRAYLIST");
        binding.RV100.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Trigger2Adapter(getApplicationContext(),object);
        binding.RV100.setAdapter(adapter);
    }
}