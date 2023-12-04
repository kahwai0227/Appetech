package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ArkedList extends AppCompatActivity {

    private Button buttonM;
    private Button buttonC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arked_list);

        buttonM = findViewById(R.id.meranti);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArkedList.this,ReservationForm.class);
                startActivity(intent);
            }
        });

        buttonC = findViewById(R.id.cenggal);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArkedList.this,ReservationForm.class);
                startActivity(intent);
            }
        });
    }
}