package com.example.firebase_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnlog, btnVerify, btnInsert;
    TextView txtVerify, name, email, password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        btnlog = findViewById(R.id.btnLogout);
        btnVerify = findViewById(R.id.btnVerify);
        btnInsert = findViewById(R.id.btnInsert);
//        txtVerify = findViewById(R.id.txtVerify);
//        name = findViewById(R.id.txtValue);
//        email = findViewById(R.id.txtEmail);
//        password = findViewById(R.id.txtPassword);
//        Intent intent = getIntent();
//
//        String gName = intent.getStringExtra("Name");
//        String gEmail = intent.getStringExtra("Email");
//        String gPass = intent.getStringExtra("Password");
//
//        name.setText(gName);
//        email.setText(gEmail);
//        password.setText(gPass);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent save = new Intent(MainActivity.this, savedata.class);
                startActivity(save);
            }
        });

        if(!auth.getCurrentUser().isEmailVerified()){
            btnVerify.setVisibility(View.VISIBLE);
            txtVerify.setVisibility(View.VISIBLE);
        }

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Verification sent" , Toast.LENGTH_SHORT).show();
                        btnVerify.setVisibility(View.GONE);
                        txtVerify.setVisibility(View.GONE);
                    }
                });
            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.reset){
            startActivity(new Intent(getApplicationContext(), reset.class));
        }
        return super.onOptionsItemSelected(item);
    }
}