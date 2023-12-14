package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StaffRegistration extends AppCompatActivity {

    private Button Staff_Registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_registration);

        Staff_Registration = findViewById(R.id.Staff_Registration);
        Staff_Registration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StaffRegistration.this, StaffLogin.class);
                startActivity(intent);
            }
        });
    }

    public void onBtnClick (View v)
    {
        TextView txtWelcome = findViewById(R.id.Staff_Welcome);
        EditText Staff_Name = findViewById(R.id.Staff_Name);
        EditText Staff_ID = findViewById(R.id.Staff_ID);
        EditText Staff_Phone = findViewById(R.id.Staff_Phone);
        EditText Staff_Email = findViewById(R.id.Staff_Email);
        EditText Staff_Username = findViewById(R.id.Staff_Username);
        EditText Staff_Password = findViewById(R.id.Staff_Password);

    }
}