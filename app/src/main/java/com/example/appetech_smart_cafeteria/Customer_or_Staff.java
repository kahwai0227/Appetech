package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Customer_or_Staff extends AppCompatActivity {

    private Button Customer;
    private Button Staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_or_staff);

        Staff=findViewById(R.id.Staff);
        Staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_or_Staff.this, StaffLogin.class);
                startActivity(intent);
            }
        });

        Customer=findViewById(R.id.Customer);
        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_or_Staff.this, CustomerLogin.class);
                startActivity(intent);
            }
        });
    }
}