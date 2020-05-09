package com.bloodapp.blood;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class health extends AppCompatActivity implements ListFragmentt.MedListListener {

    private FirebaseAuth authe;
    private FirebaseUser userd;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        authe= FirebaseAuth.getInstance();
        userd= FirebaseAuth.getInstance().getCurrentUser();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (userd == null)
                {
                    Intent intent = new Intent(health.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tips_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.back)
        {
            Intent intent = new Intent(this,home.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(long id) {
        MenuDetailFragment frag = new MenuDetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        frag.setMenuID(id);
        ft.replace(R.id.fragmentContainer,frag);
        ft.addToBackStack(null);
        ft.commit();
    }
}
