package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    public Button signOut;
    ListView listview;
    DatabaseReference myRef;
    List<Reciever> reciverLists;
    TextView verification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        listview = findViewById(R.id.listViewUser);
        myRef = FirebaseDatabase.getInstance().getReference("unReg");
        reciverLists = new ArrayList<>();
        verification = findViewById(R.id.verification);

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

        signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


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

    public  void btnsClicked(View v)
    {
        Intent apple = new Intent(home.this,health.class);
        startActivity(apple);
    }

    public  void clickd(View v)
    {
        Intent mango = new Intent(this,imageupload.class);
        startActivity(mango);
    }

    public  void clickdd(View v)
    {
        Intent mapd = new Intent(this,map.class);
        startActivity(mapd);
    }

    public  void clickdnd(View v)
    {
        Intent post = new Intent(this,camp.class);
        startActivity(post);
    }


    public  void  gotToChat(View v)
    {
        Intent chats = new Intent(this,ChatRoom.class);
        startActivity(chats);
    }

    }



