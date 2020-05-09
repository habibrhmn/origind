package com.bloodapp.blood;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hospital extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseUser user;
    private EditText txt1, txt2;
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    FirebaseDatabase nDatabase;
    DatabaseReference newdd;

    private Button btnnn;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        btnnn = findViewById(R.id.saveitman);
        user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        txt1 = findViewById(R.id.Namek);
        txt2 = findViewById(R.id.location);
        mDatePicker = findViewById(R.id.calender);
        mTimePicker = findViewById(R.id.timers);


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user==null)
                {
                    Intent intent = new Intent(Hospital.this,ReciverLogin.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        btnnn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String xt1 = txt1.getText().toString();
                String xt2 = txt2.getText().toString().trim();
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                int hour = mTimePicker.getHour();
                int min = mTimePicker.getMinute();

                nDatabase = FirebaseDatabase.getInstance();
                newdd = nDatabase.getReference("AppointmentTime").child(user.getUid());
                newdd.child("Name").setValue(xt1);
                newdd.child("Address").setValue(xt2);
                newdd.child("Month").setValue(year,month);
                newdd.child("Date").setValue(day);
                newdd.child("Time").setValue(hour,min).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Hospital.this,"Appointment has been made",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
