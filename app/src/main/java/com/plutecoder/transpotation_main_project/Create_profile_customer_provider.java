package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Create_profile_customer_provider extends AppCompatActivity {

    Button btncustomer , btnprovider;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_customer_provider);
        btncustomer = findViewById(R.id.customer_button);
        btnprovider = findViewById(R.id.provider_button);



        btnprovider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Create_profile_customer_provider.this,Provider_add_vehicle.class);
//                startActivity(i);
            }
        });
    }




}
