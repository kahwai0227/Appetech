package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StaffLogin extends AppCompatActivity {

    private Button Staff_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        Staff_Login=findViewById(R.id.Staff_Login);
        Staff_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffLogin.this, StaffFloorplan.class);
                startActivity(intent);
            }
        });

        TextView clickableText = findViewById(R.id.Staff_New_User);
        clickableText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(StaffLogin.this, StaffRegistration.class);
                startActivity(intent);
            }
        });


    }
}