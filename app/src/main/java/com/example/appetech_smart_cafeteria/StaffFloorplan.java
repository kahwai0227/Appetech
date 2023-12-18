package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StaffFloorplan extends AppCompatActivity {

    private Button Staff_Table_1_Button;
    private Button Staff_Table_2_Button;
    private Button Staff_Table_3_Button;
    private Button Staff_Table_4_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_floorplan);

        Staff_Table_1_Button=findViewById(R.id.Staff_Table_1_Button);
        Staff_Table_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffFloorplan.this, StaffFloorplan.class);
                startActivity(intent);
            }
        });

        Staff_Table_2_Button=findViewById(R.id.Staff_Table_2_Button);
        Staff_Table_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffFloorplan.this, StaffFloorplan.class);
                startActivity(intent);
            }
        });

        Staff_Table_3_Button=findViewById(R.id.Staff_Table_3_Button);
        Staff_Table_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffFloorplan.this, StaffFloorplan.class);
                startActivity(intent);
            }
        });

        Staff_Table_4_Button=findViewById(R.id.Staff_Table_4_Button);
        Staff_Table_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffFloorplan.this, StaffFloorplan.class);
                startActivity(intent);
            }
        });


    }
}