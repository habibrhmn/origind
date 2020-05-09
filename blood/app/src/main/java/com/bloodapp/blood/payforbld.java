package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class payforbld extends AppCompatActivity {

    ListView ltvw;
    FirebaseDatabase dbased;
    DatabaseReference dbrefd;
    ArrayList<String> arraylist;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payforbld);

        dbased = FirebaseDatabase.getInstance();
        dbrefd = dbased.getReference("Location");

        ltvw = findViewById(R.id.lldvwe);

        dbrefd.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.child("Name").getValue(String.class);
                String blood = dataSnapshot.child("A+").getValue(String.class);
                arraylist.add(value);
                arraylist.add(blood);
                arrayAdapter = new ArrayAdapter<>(payforbld.this, R.layout.list_layview, arraylist);
                ltvw.setAdapter(arrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = Objects.requireNonNull(dataSnapshot.child("Name").getValue(String.class));
                String blood = Objects.requireNonNull(dataSnapshot.child("A+").getValue(String.class));
                arraylist.add(value);
                arraylist.add(blood);
                arrayAdapter = new ArrayAdapter<String>(payforbld.this,R.layout.list_layview,arraylist);
                ltvw.setAdapter(arrayAdapter);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
