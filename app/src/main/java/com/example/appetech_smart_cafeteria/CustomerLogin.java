package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerLogin extends AppCompatActivity {

    private Button Customer_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        Customer_Login=findViewById(R.id.Customer_Login);
        Customer_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLogin.this, StudentFloorPlan.class);
                startActivity(intent);
            }
        });

        TextView clickableText = findViewById(R.id.Customer_New_User);
        clickableText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CustomerLogin.this, CustomerRegistration.class);
                startActivity(intent);
            }
        });


    }
}