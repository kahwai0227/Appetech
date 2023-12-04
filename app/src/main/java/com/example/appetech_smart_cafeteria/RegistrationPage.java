package com.example.appetech_smart_cafeteria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.example.appetech_smart_cafeteria.User;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword;
    private TextView goToLogin;
    private Button buttonRegister;

    // Firebase
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        goToLogin = findViewById(R.id.goToLogin);

        // Initialize Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("users");

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validate input (you can add more validation as needed)
                if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(RegistrationPage.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                // Create a User object
                else {
                    User user = new User(username, email, password);

                    // Store user information in Firebase Database
                    databaseReference.child(username).setValue(user);

                    // Show a toast message indicating successful registration
                    Toast.makeText(RegistrationPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

