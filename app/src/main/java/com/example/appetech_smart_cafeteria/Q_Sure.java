package com.example.appetech_smart_cafeteria;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Q_Sure extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button buttonB;
    private Button buttonS;
    private TextView name, contact, email, arked, bookTable, bookTime;
    User user;
    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            startActivity(new Intent(Q_Sure.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsure);

        buttonB=findViewById(R.id.back4);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        arked = findViewById(R.id.arked);
        bookTable = findViewById(R.id.table);
        bookTime = findViewById(R.id.time);

        Intent intent = getIntent();

        if(intent != null){
            String table = intent.getParcelableExtra("table");
            String tableNo = intent.getStringExtra("tableNo");
            String time = intent.getStringExtra("time");
            String hp = intent.getStringExtra("hp");
            String location = intent.getStringExtra("location");

            contact.setText(hp);
            bookTable.setText(tableNo);
            arked.setText(location);
            bookTime.setText(time);
        }

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("Student").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    user = snapshot.getValue(User.class);
                    if(user != null){
                        name.setText(user.getUsername());
                        email.setText(user.getEmail());
                    }
                    else{
                        Toast.makeText(Q_Sure.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Q_Sure.this, "Error getting data", Toast.LENGTH_SHORT).show();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Q_Sure.this, ReservationForm.class);
                startActivity(intent);
                finish();
            }
        });

        buttonS=findViewById(R.id.submit_2);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Table table = new Table();

                table.addLocation(arked.getText().toString().trim());
                table.addBookingTime(bookTime.getText().toString().trim());
                table.addBooking(true);
                table.addBookingUserEmail(email.getText().toString().trim());
                table.addBookingUsername(name.getText().toString());
                table.addBookingUserContact(contact.getText().toString().trim());
                table.addTableNo(bookTable.getText().toString().trim());
                user.addBooking(table);

                databaseReference.child("Student").child(uid).setValue(user);
                databaseReference.child(arked.getText().toString().trim()).child(bookTable.getText().toString().trim()).setValue(table);

                Intent intent = new Intent(Q_Sure.this, success_book.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}