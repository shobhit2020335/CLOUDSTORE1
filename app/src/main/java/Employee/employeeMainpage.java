package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityEmployeeMainpageBinding;

import customer.Trigger1Async;
import customer.Trigger2Async;

public class employeeMainpage extends AppCompatActivity {

    ActivityEmployeeMainpageBinding binding;
    private static final String apiurl2 = ApiLink.link +"Trigger2.php";
    private static final String apiurl1 = ApiLink.link +"Trigger1.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeMainpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                olap1 ol = new olap1(employeeMainpage.this);
                ol.execute();
                startActivity(new Intent(employeeMainpage.this, EmployeeQuery.class));
            }
        });

        binding.customerqueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(employeeMainpage.this,solveCustomerQueries.class));
            }
        });

       binding.deliverdordertrigger.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Trigger2Async trigger2Async = new Trigger2Async(apiurl2,employeeMainpage.this);
               trigger2Async.execute();
           }
       });


       binding.lowquantityproducttrigger.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Trigger1Async triggerManager = new Trigger1Async(apiurl1,employeeMainpage.this);
                triggerManager.execute();

           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(employeeMainpage.this,employeeMainpage.class));
    }
}