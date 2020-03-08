package com.bloodapp.blood;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;

import android.os.Bundle;

public class health extends AppCompatActivity implements ListFragmentt.MedListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

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
