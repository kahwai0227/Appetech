package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ArkedList extends AppCompatActivity {

    private Button buttonM;
    private Button buttonC;
    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(ArkedList.this, LoginActivity.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arked_list);

        buttonM = findViewById(R.id.meranti);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = buttonM.getText().toString();
                Intent intent = new Intent(ArkedList.this,StudentFloorPlan.class);
                intent.putExtra("location", location);
                startActivity(intent);
                finish();
            }
        });

        buttonC = findViewById(R.id.cenggal);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = buttonC.getText().toString();
                Intent intent = new Intent(ArkedList.this,StudentFloorPlan.class);
                intent.putExtra("location", location);
                startActivity(intent);
                finish();
            }
        });
    }
}