package com.example.dbmsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbmsproject.databinding.ActivityEmployeeSelectionBinding;

import Employee.employeeLogin;
import delieveryAgent.agentLogin;

public class employeeSelection extends AppCompatActivity {

    ActivityEmployeeSelectionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEmployeeSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.esmployeesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(employeeSelection.this, employeeLogin.class));
            }
        });

        binding.agentsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(employeeSelection.this, agentLogin.class));
            }
        });
    }
}