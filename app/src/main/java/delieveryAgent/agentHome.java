package delieveryAgent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityAgentHomeBinding;
import com.example.dbmsproject.databinding.ActivityAgentLoginBinding;

public class agentHome extends AppCompatActivity {

    ActivityAgentHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAgentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.deliverOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(agentHome.this,agentUndeliveredOrders.class));
            }
        });
        binding.myDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(agentHome.this,myDeliveries.class));


            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(agentHome.this,agentHome.class));
    }
}