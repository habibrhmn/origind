package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecieverHome extends AppCompatActivity {

    FirebaseUser user;
    FirebaseDatabase datad;
    DatabaseReference mdatad;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private TextView pwdText, mailfic;
    private Button btmn;
    private ListView mListView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever_home);
        user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        btmn =findViewById(R.id.changepwd);
        pwdText = findViewById(R.id.mailRT);
        mListView = findViewById(R.id.bloodAvb);
        mailfic =findViewById(R.id.verfac);
        datad = FirebaseDatabase.getInstance();
        mdatad = datad.getReference("Location");
        mListView = findViewById(R.id.bloodAvb);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user==null)
                {
                    startActivity(new Intent(RecieverHome.this,MainActivity.class));
                }
            }
        };

        mdatad.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren())
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(RecieverHome.this, ReciverLogin.class));
                    finish();
                }
            }
        };

        btmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String password = pwdText.getText().toString().trim();

               if(TextUtils.isEmpty(password))
               {
                   Toast.makeText(RecieverHome.this,"Enter a Password",Toast.LENGTH_LONG).show();
               }
               user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (!task.isSuccessful())
                       {
                           Toast.makeText(RecieverHome.this,"Something went Worng, Please re-type the password",Toast.LENGTH_LONG).show();
                       }
                       else {
                           Toast.makeText(RecieverHome.this,"Password has been Updated, Try logging in using your new Password",Toast.LENGTH_LONG).show();
                       }
                   }
               });
            }
        });

        if (user.isEmailVerified())
        {
            mailfic.setText("Email Verified");

        }
        else
        {
            mailfic.setText("Email not verified (Click to Verify)");
            mailfic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RecieverHome.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(RecieverHome.this, "Something went wrong, Please Check your Internet Connection", Toast.LENGTH_SHORT).show();

                            }                        }
                    });
                }
            });
        }
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.signs_out) {
            auth.signOut();
            startActivity(new Intent(RecieverHome.this, ReciverLogin.class));
        }

        if (id == R.id.tips) {
            Toast.makeText(RecieverHome.this,"This Page is not developed yet",Toast.LENGTH_LONG).show();
        }

        if (id == R.id.mapdd) {
            if (user.isEmailVerified()) {
                Intent mapsd = new Intent(this, map.class);
                startActivity(mapsd);
            } else {
                Toast.makeText(this, "Please Verify Your Email First", Toast.LENGTH_LONG).show();
            }
        }

        if (id == R.id.campdd) {
            if (user.isEmailVerified()) {
                Intent post = new Intent(this, camp.class);
                startActivity(post);
            } else {
                Toast.makeText(this, "Please Verify Your Email First", Toast.LENGTH_LONG).show();
            }
        }

        if (id == R.id.Chatdd) {
            if (user.isEmailVerified()) {
                Intent post = new Intent(this, ChatRoom.class);
                startActivity(post);
            } else {
                Toast.makeText(this, "Please Verify Your Email First", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void bloodDonate(View v)
    {
        Intent intent = new Intent(this,Hospital.class);
        startActivity(intent);
    }

}