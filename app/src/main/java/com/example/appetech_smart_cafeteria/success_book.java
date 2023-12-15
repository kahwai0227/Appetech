package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class success_book extends AppCompatActivity {

    private Button buttonB;
    private TextView textViewTableNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_book);

        Intent intent = getIntent();
        String tableNo = intent.getStringExtra("tableNo");
        textViewTableNo = findViewById(R.id.textViewTableNo);
        textViewTableNo.setText(tableNo);

        buttonB=findViewById(R.id.back5);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(success_book.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}