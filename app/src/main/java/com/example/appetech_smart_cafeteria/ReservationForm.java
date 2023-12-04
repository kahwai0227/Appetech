package com.example.appetech_smart_cafeteria;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ReservationForm extends AppCompatActivity {

    private Button buttonB;
    private Button buttonS;
    Button timebutton;
    int initialhour,initialminute,hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        timebutton = findViewById(R.id.timebutton);

        buttonB = findViewById(R.id.back3);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationForm.this,ArkedList.class);
                startActivity(intent);
            }
        });

        buttonS = findViewById(R.id.submit_1);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationForm.this,Q_Sure.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(selectedHour>= 8 && selectedHour <= 22){
                    hour = selectedHour;
                    minute = selectedMinute;
                    timebutton.setText(String.format(Locale.getDefault(),"%02d:%02d",hour, minute));
                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, initialhour, initialminute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}