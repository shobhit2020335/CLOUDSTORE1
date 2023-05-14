package com.example.dbmsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbmsproject.databinding.ActivityMainBinding;

import Employee.EmployeeHome;
import Employee.EmployeeQuery;
import Employee.employeeLogin;
import Employee.olap1;
import customer.customerLogin;
import customer.customerSignup;
import seller.sellerLogin;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
       binding.buttonc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, customerLogin.class);
               startActivity(intent);
           }
       });

       binding.buttons.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, sellerLogin.class);
               startActivity(intent);
           }
       });

       binding.buttone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this, employeeSelection.class);
               startActivity(intent);
//               olap1 ol=new olap1(MainActivity.this);
//               ol.execute();

           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
    }
}