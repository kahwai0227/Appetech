package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationDetails extends AppCompatActivity {

    private Button buttonB;
    private Button buttonD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        buttonB=findViewById(R.id.back1);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonD=findViewById(R.id.del1);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationDetails.this, Delete_R.class);
                startActivity(intent);
                finish();
            }
        });
    }
}