package com.example.appetech_smart_cafeteria;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText editTextHp;
    Button timebutton;
    int initialhour,initialminute,hour,minute;
    String tableNo, location, time, hp;
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
            location = intent.getStringExtra("location");
            tableNo = intent.getStringExtra("tableNo");
        }

        timebutton = findViewById(R.id.timebutton);
        textViewTableNo = findViewById(R.id.textViewTableNo);
        buttonB = findViewById(R.id.back3);
        buttonS = findViewById(R.id.submit_1);
        editTextHp = findViewById(R.id.editTextHp);

        textViewTableNo.setText(tableNo);

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
                hp = editTextHp.getText().toString().trim();
                time = timebutton.getText().toString();
                if(time.equals("Select time")){
                    Toast.makeText(ReservationForm.this, "Please pick a time", Toast.LENGTH_SHORT).show();
                }
                else if(hp.isEmpty()){
                    Toast.makeText(ReservationForm.this, "Please enter your phone no", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(ReservationForm.this,Q_Sure.class);
                    intent.putExtra("location", location);
                    intent.putExtra("tableNo", tableNo);
                    intent.putExtra("time", time);
                    intent.putExtra("hp", hp);
                    startActivity(intent);
                }
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