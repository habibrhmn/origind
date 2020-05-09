package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReciverLogin extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressBar progressBar;
    public Button btnLogin;
    private FirebaseUser userdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciver_login);

        auth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.recMail);
        inputPassword = findViewById(R.id.recPass);
        btnLogin = findViewById(R.id.loginbbtn);
        userdd =FirebaseAuth.getInstance().getCurrentUser();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(ReciverLogin.this, home.class);
                    startActivity(intent);
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = inputEmail.getText().toString();
                String pass = inputPassword.getText().toString();
                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(ReciverLogin.this, "Enter Email and Password", Toast.LENGTH_LONG).show();
                } else {
                    auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(ReciverLogin.this, "Email or Password is Incorrect", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(ReciverLogin.this, home.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

    }


        public void clicked (View v)
        {
            Intent intent = new Intent(ReciverLogin.this, register.class);
            startActivity(intent);
        }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthStateListener);
    }

    public void resetpwddd(View view) {
        Intent intent = new Intent(ReciverLogin.this,ResetPassword.class);
        startActivity(intent);
    }
}


