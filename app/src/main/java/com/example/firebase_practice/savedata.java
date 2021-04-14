package com.example.firebase_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class savedata extends AppCompatActivity {

    private EditText id, name, phone, address;
    private Button btnInsert;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedata);

        name = findViewById(R.id.etName);
        phone = findViewById(R.id.etPhoneNO);
        address = findViewById(R.id.etAddress);

        btnInsert = findViewById(R.id.btnInsert);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User Data");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid, uname, uphone, uaddress;

                uid = databaseReference.push().getKey();

                uname = name.getText().toString();
                uphone = phone.getText().toString();
                uaddress = address.getText().toString();

                if(uname.equals("")){
                    Toast.makeText(savedata.this, "Name is requied", Toast.LENGTH_SHORT).show();
                }else if(uphone.equals("")){
                    Toast.makeText(savedata.this, "Phone number is requied", Toast.LENGTH_SHORT).show();
                }else if(uaddress.equals("")){
                    Toast.makeText(savedata.this, "Address is requied", Toast.LENGTH_SHORT).show();
                }else{
                    userdata userData = new userdata(uid, uname, uphone, uaddress);
                    databaseReference.child(uid).setValue(userData);
                    Toast.makeText(savedata.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}