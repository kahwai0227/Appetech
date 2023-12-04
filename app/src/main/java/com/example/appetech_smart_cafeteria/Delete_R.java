package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_R extends AppCompatActivity {

    private Button buttonC;
    private Button buttonY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_r);

        buttonC=findViewById(R.id.ccl);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Delete_R.this, ReservationDetails.class);
                startActivity(intent);
                finish();
            }
        });

        buttonY=findViewById(R.id.yes);
        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Delete_R.this,Delete_R_success.class);
                startActivity(intent);
                finish();
            }
        });
    }
}