package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerRegistration extends AppCompatActivity {

    private Button Customer_Registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        Customer_Registration = findViewById(R.id.Customer_Registration);
        Customer_Registration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CustomerRegistration.this, CustomerLogin.class);
                startActivity(intent);
            }
        });
    }

    public void onBtnClick (View v)
    {
        TextView txtWelcome = findViewById(R.id.Customer_Welcome);
        EditText Staff_Name = findViewById(R.id.Customer_Name);
        EditText Staff_ID = findViewById(R.id.Customer_ID);
        EditText Staff_Phone = findViewById(R.id.Customer_Phone);
        EditText Staff_Email = findViewById(R.id.Customer_Email);
        EditText Staff_Username = findViewById(R.id.Customer_Username);
        EditText Staff_Password = findViewById(R.id.Customer_Password);
    }

}