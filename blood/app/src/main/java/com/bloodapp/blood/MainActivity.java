package com.bloodapp.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buttonClicked(View v)
    {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

    public void btnClicked(View v)
    {
        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }



}


