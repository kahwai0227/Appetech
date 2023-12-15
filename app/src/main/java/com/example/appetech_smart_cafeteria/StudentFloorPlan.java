package com.example.appetech_smart_cafeteria;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class StudentFloorPlan extends AppCompatActivity {

    private Button Customer_Table_1_Button;
    private Button Customer_Table_2_Button;
    private Button Customer_Table_3_Button;
    private Button Customer_Table_4_Button;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Calendar calendar;

    Boolean tableBooked1, tableBooked2, tableBooked3, tableBooked4, haveBooking;
    Boolean tableOccupied1, tableOccupied2, tableOccupied3, tableOccupied4;

    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(StudentFloorPlan.this, LoginActivity.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_floorplan);

        calendar = Calendar.getInstance();

        Customer_Table_1_Button=findViewById(R.id.Customer_Table_1_Button);
        Customer_Table_2_Button=findViewById(R.id.Customer_Table_2_Button);
        Customer_Table_3_Button=findViewById(R.id.Customer_Table_3_Button);
        Customer_Table_4_Button=findViewById(R.id.Customer_Table_4_Button);

        tableBooked1 = tableBooked2 = tableBooked3 = tableBooked4 = haveBooking = false;
        tableOccupied1 = tableOccupied2 = tableOccupied3 = tableOccupied4 = false;

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        if(location.isEmpty()){
            Toast.makeText(StudentFloorPlan.this, "Failed to choose location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StudentFloorPlan.this, ArkedList.class));
            finish();
        }

        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null && user.getBooking() != null){
                    haveBooking = true;
                    Toast.makeText(StudentFloorPlan.this, "You have active booking, delete it before making another booking", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(StudentFloorPlan.this, ReservationDetails.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("device").child(location).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot objectSnapshot : snapshot.getChildren()) {
                        Integer tableOccupied = objectSnapshot.child("tableOccupied").getValue(Integer.class);
                        String tableNo = objectSnapshot.getKey();
                        if (tableOccupied != null && tableOccupied == 1) {
                            switch (tableNo) {
                                case "Table 1":
                                    tableOccupied1 = true;
                                    Customer_Table_1_Button.setBackgroundColor(Color.RED);
                                    break;
                                case "Table 2":
                                    tableOccupied2 = true;
                                    Customer_Table_2_Button.setBackgroundColor(Color.RED);
                                    break;
                                case "Table 3":
                                    tableOccupied3 = true;
                                    Customer_Table_3_Button.setBackgroundColor(Color.RED);
                                    break;
                                case "Table 4":
                                    tableOccupied4 = true;
                                    Customer_Table_4_Button.setBackgroundColor(Color.RED);
                                    break;
                                default:
                                    Toast.makeText(StudentFloorPlan.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                        else {
                            switch (tableNo) {
                                case "Table 1":
                                    tableOccupied1 = false;
                                    Customer_Table_1_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                case "Table 2":
                                    tableOccupied2 = false;
                                    Customer_Table_2_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                case "Table 3":
                                    tableOccupied3 = false;
                                    Customer_Table_3_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                case "Table 4":
                                    tableOccupied4 = false;
                                    Customer_Table_4_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                default:
                                    Toast.makeText(StudentFloorPlan.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("arked").child(location).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot objectSnapshot : snapshot.getChildren()) {
                        Boolean tableBooked = objectSnapshot.child("tableBooked").getValue(Boolean.class);
                        String tableNo = objectSnapshot.child("tableNo").getValue(String.class);
                        if (tableBooked != null && tableBooked) {
                            switch (tableNo) {
                                case "Table 1":
                                    tableBooked1 = true;
                                    Customer_Table_1_Button.setBackgroundColor(Color.RED);
                                    break;
                                case "Table 2":
                                    tableBooked2 = true;
                                    Customer_Table_2_Button.setBackgroundColor(Color.RED);
                                    break;
                                case "Table 3":
                                    tableBooked3 = true;
                                    Customer_Table_3_Button.setBackgroundColor(Color.RED);
                                    break;
                                case "Table 4":
                                    tableBooked4 = true;
                                    Customer_Table_4_Button.setBackgroundColor(Color.RED);
                                    break;
                                default:
                                    Toast.makeText(StudentFloorPlan.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            Log.d(TAG, calendar.getTime().toString());
                        } else {
                            switch (tableNo) {
                                case "Table 1":
                                    tableBooked1 = false;
                                    Customer_Table_1_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                case "Table 2":
                                    tableBooked2 = false;
                                    Customer_Table_2_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                case "Table 3":
                                    tableBooked3 = false;
                                    Customer_Table_3_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                case "Table 4":
                                    tableBooked4 = false;
                                    Customer_Table_4_Button.setBackgroundColor(Color.GREEN);
                                    break;
                                default:
                                    Toast.makeText(StudentFloorPlan.this, "Data does not exist", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Customer_Table_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNo = Customer_Table_1_Button.getText().toString().trim();
                if(tableBooked1 || tableOccupied1){
                    Toast.makeText(StudentFloorPlan.this, "Booking not available", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(StudentFloorPlan.this,ReservationForm.class);
                    intent.putExtra("tableNo", tableNo);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Customer_Table_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNo = Customer_Table_2_Button.getText().toString().trim();
                if(tableBooked2 || tableOccupied2){
                    Toast.makeText(StudentFloorPlan.this, "Booking not available", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(StudentFloorPlan.this,ReservationForm.class);
                    intent.putExtra("tableNo", tableNo);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Customer_Table_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNo = Customer_Table_3_Button.getText().toString().trim();
                if(tableBooked3 || tableOccupied3){
                    Toast.makeText(StudentFloorPlan.this, "Booking not available", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(StudentFloorPlan.this,ReservationForm.class);
                    intent.putExtra("tableNo", tableNo);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Customer_Table_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNo = Customer_Table_4_Button.getText().toString().trim();
                if(tableBooked4 || tableOccupied4){
                    Toast.makeText(StudentFloorPlan.this, "Booking not available", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(StudentFloorPlan.this,ReservationForm.class);
                    intent.putExtra("tableNo", tableNo);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}