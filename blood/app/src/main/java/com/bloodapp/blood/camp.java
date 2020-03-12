package com.bloodapp.blood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.DatabaseMetaData;

public class camp extends AppCompatActivity {

    private RecyclerView postlist;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        postlist = findViewById(R.id.listed);
        postlist.setHasFixedSize(true);
        postlist.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance().getReference().child("Post_Image");


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter <posting,ViewHolder> fbra = new FirebaseRecyclerAdapter<posting, ViewHolder>(
                posting.class,
                R.layout.block,
                ViewHolder.class,
                database

                ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, posting posting, int i) {

                viewHolder.setTitle(posting.getTitle());
                viewHolder.setDesc(posting.getDesc());
                viewHolder.setImage(getApplicationContext(),posting.getImage());
                viewHolder.setuserName(posting.getUserName());
            }
        };
        postlist.setAdapter(fbra);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public ViewHolder(View itemView)
        {
            super(itemView);
           view = itemView;
        }
        public void setTitle(String title)
        {
            TextView name = view.findViewById(R.id.textTitle);
            name.setText(title);
        }

        public void setDesc(String desc)
        {
            TextView dead = view.findViewById(R.id.textDesc);
            dead.setText(desc);
        }

        public void setImage(Context ctx, String image)
        {
            ImageView post = view.findViewById(R.id.postImage);
            Picasso.with(ctx).load(image).into(post);
        }

        public void  setuserName(String userName)
        {
            TextView postname = view.findViewById(R.id.textUsername);
            postname.setText(userName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();

        if (id == R.id.setting)
        {
            return true;
        }

        if (id==R.id.appicon)
        {
            Intent intent = new Intent(this,postit.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
