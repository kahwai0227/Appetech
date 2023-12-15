package com.example.appetech_smart_cafeteria;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class RegistrationPage extends AppCompatActivity {

    private EditText editTextUsername, editTextID, editTextEmail, editTextPassword, editTextConfirmPassword;
    private TextView goToLogin;
    private Button buttonRegister;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference firebaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            startActivity(new Intent(RegistrationPage.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        Intent intent = getIntent();
        String role = intent.getStringExtra("role");

        if(role.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please choose your role again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegistrationPage.this, Customer_or_Staff.class));
            finish();
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextID = findViewById(R.id.editTextID);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        goToLogin = findViewById(R.id.goToLogin);

        // Initialize Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://appetech-smart-cafeteria-default-rtdb.asia-southeast1.firebasedatabase.app/");
        firebaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String username = editTextUsername.getText().toString().trim();
                String id = editTextID.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                // Validate input (you can add more validation as needed)
                if(username.isEmpty()){
                    editTextUsername.setError("Username cannot be empty");
                }
                else if(id.isEmpty()) {
                    editTextID.setError("ID cannot be empty");
                }
                else if(email.isEmpty()){
                    editTextEmail.setError("Email cannot be empty");
                }
                else if (password.isEmpty()) {
                    editTextPassword.setError("Password cannot be empty");
                }
                else if(confirmPassword.isEmpty()){
                    editTextConfirmPassword.setError("Confirm password cannot be empty");
                }

                // Create a User object
                else {
                    if(password.equals(confirmPassword)){
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    String userid = firebaseUser.getUid();
                                    User user = new User(username, id, role, email, password);
                                    firebaseReference.child("users").child(userid).setValue(user);
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegistrationPage.this, "Auth fail:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
                    }
                    else{
                        editTextConfirmPassword.setError("Wrong password");
                    }
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationPage.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateUI(User user) {
        if(user != null){
            startActivity(new Intent(RegistrationPage.this, LoginActivity.class));
            finish();
        }
        else{
            startActivity(new Intent(RegistrationPage.this, RegistrationPage.class));
            finish();
        }
    }

}



