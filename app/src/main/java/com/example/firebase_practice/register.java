package com.example.firebase_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {

    Button btnLogin, btnRegister;
    TextInputEditText name, email, password, confirm;
    FirebaseAuth fAuth;
    userdata user;
    String emailformat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);

        fAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String upassword = password.getText().toString();
                String ucon = confirm.getText().toString();
                if (uname.isEmpty()){
                    name.setError("Full name is requied");
                    return;
                }
                if (uemail.isEmpty()){
                    email.setError("Email is requied");
                    return;
                }
                if (upassword.isEmpty()){
                    password.setError("Password is requied");
                    return;
                }
                if (ucon.isEmpty()){
                    confirm.setError("Confirm the pasword");
                    return;
                }
//                if(!password.equals(confirm)){
//                    confirm.setError("Passwords do not Match!");
//                    return;
//                }


                fAuth.createUserWithEmailAndPassword(uemail, upassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
//                        Log.i("gogpo", "123 " + uemail + upassword);
                        user = new userdata(uname, uemail, upassword);
                        emailformat = user.getEmail().replace(".", "-");
                        myRef.child("realtimedata").child(emailformat).setValue(user);
                        Intent i = new Intent(getApplicationContext(), login.class);
                        startActivity(i);
                        Toast.makeText(register.this, "Tirkeldiniz", Toast.LENGTH_SHORT).show();
//                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(register.this, login.class);
                startActivity(in);
            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

    }
}