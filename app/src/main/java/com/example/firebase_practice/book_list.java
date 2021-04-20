package com.example.firebase_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class book_list extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter bookAdapter;
    ArrayList<Book> bookList;
    FirebaseAuth fAuth;
    Book book;
    private RecyclerView.LayoutManager linearLayoutManager, gridLayoutManager;
//    private List<book_adapter> bookList;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        initViews();
        getBooks();

//        int uphoto = 0;
//        String utitle = "test";
//        String uauthor = "test";
//        String uscore = "test";
//        String upage = "test";
//        book = new Book(uphoto, utitle, uauthor, uscore, upage);

//        myRef.child(bookList.get(0).getTitle()).setValue(bookList.get(0));
//        myRef.child(bookList.get(1).getTitle()).setValue(bookList.get(1));
//        myRef.child(bookList.get(2).getTitle()).setValue(bookList.get(2));
    }

    public void initViews(){

        recyclerView = findViewById(R.id.recyclerView);
        bookList = new ArrayList<>();
        fAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        bookAdapter = new BookAdapter(this, bookList);

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(bookAdapter);
    }

    public void getBooks(){
        myRef.child("books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot books: snapshot.getChildren()) {
                        Book book = books.getValue(Book.class);
                        bookList.add(book);
                    }
                    bookAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        bookList.add(new Book(R.drawable.book11, "Something wicked this way comes", "By Ray Bradbury","4.9", "335 pages | 35 reviews"));
//        bookList.add(new Book(R.drawable.atomic, "Atomic habits", "By James Clear","4.7", "226 pages | 25 reviews"));
//        bookList.add(new Book(R.drawable.habits, "The 7 Habits of Highly Effective People", "By Stephen Covey","4.5", "384 pages | 10 reviews"));
//        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            startActivity(new Intent(getApplicationContext(), AddBook.class));
        }
        return super.onOptionsItemSelected(item);
    }
}