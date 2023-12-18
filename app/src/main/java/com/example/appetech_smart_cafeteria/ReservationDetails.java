package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ReservationDetails extends AppCompatActivity {

    private Button buttonB;
    private Button buttonD;

    Button buttonC;

    String location2, tableNo;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private TextView name, contact, email, arked, bookTable, bookTime;
    private String location;


    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(ReservationDetails.this, LoginActivity.class));
            finish();
        }
        else{
            firebaseUser = user;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        arked = findViewById(R.id.arked);
        bookTable = findViewById(R.id.table);
        bookTime = findViewById(R.id.time);

        databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    if(user != null && user.getBooking() != null){
                        Table table = user.getBooking();
                        name.setText(table.getBookingUsername());
                        contact.setText(table.getBookingUserContact());
                        email.setText(table.getBookingUserEmail());
                        arked.setText(table.getLocation());
                        bookTable.setText(table.getTableNo());
                        bookTime.setText(table.getBookingTime());
                        location = table.getLocation();
                    }
                    else{
                        Toast.makeText(ReservationDetails.this, "No current booking", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReservationDetails.this, MainActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonB=findViewById(R.id.back1);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationDetails.this, MainActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
                finish();
            }
        });

        buttonD=findViewById(R.id.del1);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationDetails.this, Delete_R.class);
                startActivity(intent);
                finish();
            }
        });

        buttonC = findViewById(R.id.checkin);
        buttonC.setOnClickListener(v->
        {
            scanCode();
        });

    }

    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(false);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions>barLauncher = registerForActivityResult(new ScanContract(), result->
    {
        if (result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ReservationDetails.this);
            builder.setTitle("Table booked");
            tableNo = result.getContents();

            databaseReference.child("arked").child(location2).child(tableNo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        Table table = snapshot.getValue(Table.class);
                        table.removeBooking();
                        databaseReference.child("arked").child(location2).child(tableNo).setValue(table);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}