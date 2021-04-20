package com.example.firebase_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBook extends AppCompatActivity {
    DatabaseReference myRef;
    FirebaseDatabase database;
    TextInputEditText title, author, score, pages;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        initViews();
    }

    public void initViews(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        score = findViewById(R.id.score);
        pages = findViewById(R.id.pages);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
                startActivity(new Intent(AddBook.this, book_list.class));
            }
        });
    }

    public void addBook(){
        String utitle = title.getText().toString();
        String uauthor = author.getText().toString();
        String uscore = score.getText().toString();
        String upages = pages.getText().toString();
        Book book = new Book(utitle, uauthor, uscore, upages);
        myRef.child("books").child(book.getTitle()).setValue(book);
    }

}