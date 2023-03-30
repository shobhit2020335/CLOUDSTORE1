package customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.dbmsproject.databinding.ActivityTrigger1Binding;

import java.util.ArrayList;

import Product.ProductCount;

public class Trigger1 extends AppCompatActivity {
    ActivityTrigger1Binding binding;
    Trigger1Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrigger1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<ProductCount> object = (ArrayList<ProductCount>) args.getSerializable("ARRAYLIST");
        binding.RV99.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Trigger1Adapter(getApplicationContext(),object);
        binding.RV99.setAdapter(adapter);

    }

    public void updateData(ArrayList<ProductCount> productCountArrayList) {
        if(productCountArrayList.size()>0) {
            adapter.setData(productCountArrayList);
        }
    }
}