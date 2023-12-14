package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerFloorplan extends AppCompatActivity {

    private Button Customer_Table_1_Button;
    private Button Customer_Table_2_Button;
    private Button Customer_Table_3_Button;
    private Button Customer_Table_4_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_floorplan);

        Customer_Table_1_Button=findViewById(R.id.Customer_Table_1_Button);
        Customer_Table_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFloorplan.this,ReservationForm.class);
                startActivity(intent);
            }
        });

        Customer_Table_2_Button=findViewById(R.id.Customer_Table_2_Button);
        Customer_Table_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFloorplan.this,ReservationForm.class);
                startActivity(intent);
            }
        });

        Customer_Table_3_Button=findViewById(R.id.Customer_Table_3_Button);
        Customer_Table_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFloorplan.this,ReservationForm.class );
                startActivity(intent);
            }
        });

        Customer_Table_4_Button=findViewById(R.id.Customer_Table_4_Button);
        Customer_Table_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFloorplan.this,ReservationForm.class );
                startActivity(intent);
            }
        });
    }
}