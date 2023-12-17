package com.example.appetech_smart_cafeteria;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class StudentFloorPlan extends AppCompatActivity {
    private Handler updateTableHandler;
    private Runnable updateTables;
    private Button Customer_Table_1_Button;
    private Button Customer_Table_2_Button;
    private Button Customer_Table_3_Button;
    private Button Customer_Table_4_Button;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Calendar calendar;
    private Boolean tableAvailable1, tableAvailable2, tableAvailable3, tableAvailable4;
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

        Customer_Table_1_Button = findViewById(R.id.Customer_Table_1_Button);
        Customer_Table_2_Button = findViewById(R.id.Customer_Table_2_Button);
        Customer_Table_3_Button = findViewById(R.id.Customer_Table_3_Button);
        Customer_Table_4_Button = findViewById(R.id.Customer_Table_4_Button);

        tableAvailable1 = tableAvailable2 = tableAvailable3 = tableAvailable4 = true;

        updateTableHandler = new Handler(Looper.getMainLooper());

        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();

        calendar = Calendar.getInstance();
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        if (location.isEmpty()) {
            Toast.makeText(StudentFloorPlan.this, "Failed to choose location", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StudentFloorPlan.this, ArkedList.class));
            finish();
        }

        updateTableStatus(databaseReference, Customer_Table_1_Button, "Table 1", location);
        updateTableStatus(databaseReference, Customer_Table_2_Button, "Table 2", location);
        updateTableStatus(databaseReference, Customer_Table_3_Button, "Table 3", location);
        updateTableStatus(databaseReference, Customer_Table_4_Button, "Table 4", location);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null && user.getBooking() != null) {
                    Toast.makeText(StudentFloorPlan.this, "You have active booking, delete it before making another booking", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(StudentFloorPlan.this, ReservationDetails.class));
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updateTables = new Runnable() {
            @Override
            public void run() {
                updateTableStatus(databaseReference, Customer_Table_1_Button, "Table 1", location);
                updateTableStatus(databaseReference, Customer_Table_2_Button, "Table 2", location);
                updateTableStatus(databaseReference, Customer_Table_3_Button, "Table 3", location);
                updateTableStatus(databaseReference, Customer_Table_4_Button, "Table 4", location);
                Log.d("DEBUG", "Table Updated");
                updateTableHandler.postDelayed(this, 5);
            }
        };
        setupButton(Customer_Table_1_Button, "Table 1", location);
        setupButton(Customer_Table_2_Button, "Table 2", location);
        setupButton(Customer_Table_3_Button, "Table 3", location);
        setupButton(Customer_Table_4_Button, "Table 4", location);
        updateTableHandler.postDelayed(updateTables, 5);
    }
    private void setupButton(Button button, String tableNo, String location){
        button.setOnClickListener(new View.OnClickListener() {
            boolean tableAvailable = true;
            @Override
            public void onClick(View v) {
                switch(tableNo){
                    case "Table 1":
                        tableAvailable = tableAvailable1;
                        break;
                    case "Table 2":
                        tableAvailable = tableAvailable2;
                        break;
                    case "Table 3":
                        tableAvailable = tableAvailable3;
                        break;
                    case "Table 4":
                        tableAvailable = tableAvailable4;
                        break;
                }
                if(tableAvailable){
                    Intent intent = new Intent(StudentFloorPlan.this,ReservationForm.class);
                    intent.putExtra("tableNo", tableNo);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(StudentFloorPlan.this, "Table not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the periodic task callbacks when the activity is destroyed to avoid memory leaks
        updateTableHandler.removeCallbacks(updateTables);
    }
    private void getTableStatus(@NonNull SimpleCallback<Table> finishedCallback, DatabaseReference databaseReference, String tableNo, String location) {
        databaseReference.child("arked").child(location).child(tableNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Table table = snapshot.getValue(Table.class);
                    finishedCallback.callback(table);
                    Log.d("DEBUG", "Callback table object");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateTableStatus(DatabaseReference databaseReference ,Button button, String tableNo, String location){
        getTableStatus(new SimpleCallback<Table>() {
            @Override
            public void callback(Table data) {
                Log.d("DEBUG", "tableBooked = " + data.getTableBooked());
                Log.d("DEBUG", "tableOccupied = " + data.getTableOccupied());
                if(data.getTableBooked() != null && data.getTableOccupied() != null){
                    if(data.getTableOccupied() == 1 || data.getTableBooked()){
                        button.setBackgroundColor(Color.RED);
                        switch(tableNo){
                            case "Table 1":
                                tableAvailable1 = false;
                                break;
                            case "Table 2":
                                tableAvailable2 = false;
                                break;
                            case "Table 3":
                                tableAvailable3 = false;
                                break;
                            case "Table 4":
                                tableAvailable4 = false;
                                break;
                        }
                    }
                    else{
                        button.setBackgroundColor(Color.GREEN);
                        switch(tableNo){
                            case "Table 1":
                                tableAvailable1 = true;
                                break;
                            case "Table 2":
                                tableAvailable2 = true;
                                break;
                            case "Table 3":
                                tableAvailable3 = true;
                                break;
                            case "Table 4":
                                tableAvailable4 = true;
                                break;
                        }
                    }
                }
            }
        }, databaseReference, tableNo, location);
        Log.d("color2", "Table is " + (String) button.getTag());
    }

    public interface SimpleCallback<T> {
        void callback(T data);
    }
    public Boolean isButtonRed(Button button){
        ColorStateList backgroundTintList = button.getBackgroundTintList();
        int color = backgroundTintList.getColorForState(new int[] { android.R.attr.state_enabled}, 0);
        if(color == Color.GREEN){
            Log.d("ButtonColor", "The button is green");
        }
        else if(color == Color.RED){
            Log.d("ButtonColor", "The button is green");
        }
        else{
            Log.d("ButtonColor", "The button is" + color);
        }
        return false;
    }
}