package com.bloodapp.blood;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.UserWriteRecord;

public class AdminPage extends AppCompatActivity {

    FirebaseUser user;
    private FirebaseAuth auth;
    DatabaseReference database;
    FirebaseDatabase nDatabase;
    private FirebaseAuth.AuthStateListener authListener;
    private EditText mailId,logit,lattit,hospss, bAmount;
    private Button delete,datasave;
    private Spinner spinf;
    double latdd, loggg;
    String namessd,longitude,amont,type,latitude ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        mailId = findViewById(R.id.removemail);
        delete = findViewById(R.id.delUser);
        datasave = findViewById(R.id.savedata);
        logit = findViewById(R.id.longi);
        lattit = findViewById(R.id.lati);
        hospss = findViewById(R.id.hospname);
        spinf =findViewById(R.id.spinBld);
        bAmount = findViewById(R.id.bldAmount);


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(AdminPage.this, login.class));
                    finish();
                }
            }
        };

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String userd = mailId.getText().toString();
               String apple = FirebaseAuth.getInstance().getUid();
               mailId.setText(apple);
            }
        });

        datasave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namessd = hospss.getText().toString().trim();
                longitude = logit.getText().toString().trim();
                latitude = lattit.getText().toString().trim();
                amont = bAmount.getText().toString().trim();
                type = spinf.getSelectedItem().toString().trim();

                latdd = Double.parseDouble(latitude);
                loggg = Double.parseDouble(longitude);

                nDatabase =FirebaseDatabase.getInstance();
                database = nDatabase.getReference("Location").child(namessd);
                database.child("Name").setValue(namessd);
                database.child("Latitude").setValue(latdd);
                database.child("Longitude").setValue(loggg);
                database.child(type).setValue(amont);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    public void SignClick(View v)
    {
        auth.signOut();
        startActivity(new Intent(AdminPage.this,login.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.signs_out) {
            auth.signOut();
            startActivity(new Intent(AdminPage.this, MainActivity.class));
        }

        if (id == R.id.tips) {
            Toast.makeText(AdminPage.this,"This Page is not developed yet",Toast.LENGTH_LONG).show();
        }

        if (id == R.id.mapdd) {
                 Intent mapsd = new Intent(this, map.class);
                startActivity(mapsd);
        }

        if (id == R.id.campdd) {
                Intent post = new Intent(this, camp.class);
                startActivity(post);

        }

        if (id == R.id.Chatdd) {

                Intent post = new Intent(this, ChatRoom.class);
                startActivity(post);
        }
        return super.onOptionsItemSelected(item);
    }

}
