package com.example.firebase_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    Button btnLogin, btnRegister;
    TextInputEditText name, email, password, confirm;
    FirebaseAuth fAuth;

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
                        Intent i = new Intent(getApplicationContext(), login.class);
//                        i.putExtra("Name", uname);
//                        i.putExtra("Email", uemail);
//                        i.putExtra("Password", upassword);
                        Toast.makeText(register.this, "Tirkeldiniz", Toast.LENGTH_SHORT).show();
                        startActivity(i);
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

    }
}