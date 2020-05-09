package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    ListView listview;
    Button chagebtn,availdd;
    EditText tpdpwd;
    DatabaseReference myRef;
    List<Reciever> reciverLists;
    TextView verification;
    FirebaseUser apple;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tpdpwd =findViewById(R.id.passplz);
        availdd = findViewById(R.id.showAvail);
        chagebtn = findViewById(R.id.chagebtn);
        auth = FirebaseAuth.getInstance();
        listview = findViewById(R.id.listViewUser);
        myRef = FirebaseDatabase.getInstance().getReference("unReg");
        reciverLists = new ArrayList<>();
        verification = findViewById(R.id.verification);
        apple = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(home.this, login.class));
                    finish();
                }
            }
        };


        if (user.isEmailVerified()) {
            verification.setText("Email Verified");
        } else {
            verification.setText("Email Not Verified (Click to Verify)");
            verification.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(home.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }
            );
        }

        chagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailId = tpdpwd.getText().toString().trim();

                if (TextUtils.isEmpty(mailId))
                {
                    Toast.makeText(home.this,"Please enter a password",Toast.LENGTH_LONG).show();
                }
                user.updatePassword(mailId).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(home.this,"Something went Wrong Please re-enter the password",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(home.this,"Password Change Success, Please try logging in using new password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        availdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,payforbld.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.signs_out)
        {
         signOut();
        }

        if (id == R.id.tips)
        {
            Intent intent = new Intent(this,health.class);
            startActivity(intent);
        }

        if (id==R.id.mapdd)
        {
            if (apple.isEmailVerified()) {
                Intent mapsd = new Intent(this, map.class);
                startActivity(mapsd);
            }
            else
            {
                Toast.makeText(this,"Please Verify Your Email First",Toast.LENGTH_LONG).show();
            }
        }

        if (id == R.id.campdd)
        {
            if (apple.isEmailVerified())
            {
                Intent post = new Intent(this,camp.class);
                startActivity(post);
            }
            else
            {
                Toast.makeText(this,"Please Verify Your Email First",Toast.LENGTH_LONG).show();
            }
        }

        if (id==R.id.Chatdd)
        {
            if (apple.isEmailVerified())
            {
                Intent post = new Intent(this,ChatRoom.class);
                startActivity(post);
            }
            else
            {
                Toast.makeText(this,"Please Verify Your Email First",Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reciverLists.clear();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren())
                {
                    Reciever reciever = userSnapshot.getValue(Reciever.class);
                    reciverLists.add(reciever);
                }
                userlist adapter = new userlist(home.this,reciverLists);
                listview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    }



