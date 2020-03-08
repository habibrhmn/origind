package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class register extends AppCompatActivity {

        private EditText inputEmail, inputPassword,ages, username;
        public Button btnSignUp;
        private ProgressBar progressBar;
        Spinner spinners;
        EditText phone;
        Spinner country;
        private FirebaseAuth auth;
        DatabaseReference myRef;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            auth = FirebaseAuth.getInstance();

            btnSignUp =  findViewById(R.id.button3);
            ages =  findViewById(R.id.aged);
            username =  findViewById(R.id.name);
            inputEmail =  findViewById(R.id.mail);
            inputPassword = findViewById(R.id.pass);
            progressBar =  findViewById(R.id.progressBar);
            spinners =  findViewById(R.id.spinner);
            myRef = FirebaseDatabase.getInstance().getReference("unReg");
            phone = findViewById(R.id.numbers);
            country = findViewById(R.id.spin);
            country.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, com.ashwiniraj.phoneotp.CountryData.countryNames));



            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = inputEmail.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {

                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(register.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        addData();
                                        startActivity(new Intent(register.this, home.class));
                                        finish();


                                    }
                                }
                            });

                }
            });

        }

    private void addData()
    {
        String blood = spinners.getSelectedItem().toString();
        String dob = ages.getText().toString().trim();
        String namae = username.getText().toString().trim();
        String number = phone.getText().toString().trim();
        String verify = country.getSelectedItem().toString().trim();

        if (!TextUtils.isEmpty(namae)) {

            String id = myRef.push().getKey();
            Reciever reciever = new Reciever(id, namae, blood, dob, number, verify);

            myRef.child(id).setValue(reciever);

            Toast.makeText(this,"User Added",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Enter a Name",Toast.LENGTH_LONG).show();
        }

    }

        @Override
        protected void onResume() {
            super.onResume();
            progressBar.setVisibility(View.GONE);
        }

        public void clicks(View v)
        {
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
        }

}



