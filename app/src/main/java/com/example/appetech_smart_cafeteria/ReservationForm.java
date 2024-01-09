package com.example.appetech_smart_cafeteria;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReservationForm extends AppCompatActivity {

    private Button buttonB;
    private Button buttonS;
    private TextView textViewTableNo;
    private EditText editTextHp;
    TextView timetext, datetext;
    int initialhour,initialminute,hour,minute;
    String tableNo, location, time, hp, date;
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

        timetext = findViewById(R.id.timetext);
        textViewTableNo = findViewById(R.id.textViewTableNo);
        buttonB = findViewById(R.id.back3);
        buttonS = findViewById(R.id.submit_1);
        editTextHp = findViewById(R.id.editTextHp);
        datetext = findViewById(R.id.datetext);

        textViewTableNo.setText(tableNo);

        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();

        // Format the time using SimpleDateFormat
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedTime = timeformat.format(currentTime);
        String formattedDate = dateFormat.format(currentTime);

        // Set the formatted time to the TextView
        timetext.setText(formattedTime);
        datetext.setText(formattedDate);

        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationForm.this,ArkedList.class);
                startActivity(intent);
                finish();
            }
        });

        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hp = editTextHp.getText().toString().trim();
                time = timetext.getText().toString();
                date = datetext.getText().toString();

                Intent intent = new Intent(ReservationForm.this, Q_Sure.class);
                intent.putExtra("location", location);
                intent.putExtra("tableNo", tableNo);
                intent.putExtra("time", time);
                intent.putExtra("hp", hp);
                intent.putExtra("date", date);
                startActivity(intent);
                finish();
            }
        });
    }
}