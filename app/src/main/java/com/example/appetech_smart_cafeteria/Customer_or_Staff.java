package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Customer_or_Staff extends AppCompatActivity {

    private Button Student;
    private Button Staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_or_staff);

        Staff=findViewById(R.id.Staff);
        Staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role = "Staff";
                Intent intent = new Intent(Customer_or_Staff.this, LoginActivity.class);
                intent.putExtra("role", role);
                startActivity(intent);
            }
        });

        Student=findViewById(R.id.Student);
        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role = "Student";
                Intent intent = new Intent(Customer_or_Staff.this, RegistrationPage.class);
                intent.putExtra("role", role);
                startActivity(intent);
            }
        });
    }
}