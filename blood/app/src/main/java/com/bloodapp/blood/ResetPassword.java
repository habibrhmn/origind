package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPassword extends AppCompatActivity {

    private EditText editText;
    private Button buttonA, buttonB;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editText = findViewById(R.id.mailText);
        buttonA = findViewById(R.id.btnReset);
        buttonB = findViewById(R.id.mainPage);
        auth = FirebaseAuth.getInstance();

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPassword.this,MainActivity.class));
                finish();
            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText.getText().toString().trim();
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(ResetPassword.this,"Please Provide a Email",Toast.LENGTH_LONG).show();
                    return;
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(ResetPassword.this,"Something went Wrong, Please re-enter your Email Address",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else
                        {
                            Toast.makeText(ResetPassword.this,"Verification Email is  Sent",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }
        });
    }
}
