package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Q_Sure extends AppCompatActivity {

    private Button buttonB;
    private Button buttonS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsure);

        buttonB=findViewById(R.id.back4);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Q_Sure.this, ReservationForm.class);
                startActivity(intent);
                finish();
            }
        });

        buttonS=findViewById(R.id.submit_2);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Q_Sure.this, success_book.class);
                startActivity(intent);
                finish();
            }
        });
    }
}