package com.example.appetech_smart_cafeteria;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ReservationForm extends AppCompatActivity {

    private Button buttonB;
    private Button buttonS;
    private TextView textViewTableNo;
    Button timebutton;
    int initialhour,initialminute,hour,minute;
    String tableNo, location;
    String time;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(ReservationForm.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        Intent intent = getIntent();
        if (intent != null) {
            String location = intent.getStringExtra("location");
        }

        timebutton = findViewById(R.id.timebutton);
        textViewTableNo = findViewById(R.id.textViewTableNo);
        buttonB = findViewById(R.id.back3);
        buttonS = findViewById(R.id.submit_1);

        tableNo = textViewTableNo.getText().toString();

        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationForm.this,ArkedList.class);
                startActivity(intent);
            }
        });

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
                    time = timebutton.getText().toString();
                    Table table = new Table(location);
                }
                else{
                    Toast.makeText(ReservationForm.this, "booking available from 8am to 10pm only", Toast.LENGTH_SHORT).show();
                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, initialhour, initialminute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}