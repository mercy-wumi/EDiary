package com.example.e_diary;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email, username, pass;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email= findViewById(R.id.email);
        username= findViewById(R.id.username);
        pass= findViewById(R.id.mpassword);
        signup = findViewById(R.id.signup_button);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event_mail= email.getText().toString().trim();
                String event_user= username.getText().toString().trim();
                String event_pass= pass.getText().toString().trim();

                if (TextUtils.isEmpty(event_mail)){
                    Toast.makeText(Signup.this, "please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(event_user)){
                    Toast.makeText(Signup.this, "please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(event_pass)){
                    Toast.makeText(Signup.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (event_pass.length()<6){
                    Toast.makeText(Signup.this, "password too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(event_mail, event_pass)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),Event.class));
                                    Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);

                                } else {
                                    Toast.makeText(Signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                            }
                        });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }

//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            email.setVisibility(View.GONE);
//            username.setVisibility(View.GONE);
//            pass.setVisibility(View.GONE);
//
//        } else {
//
//            email.setVisibility(View.VISIBLE);
//            username.setVisibility(View.VISIBLE);
//            pass.setVisibility(View.GONE);
//
//        }
//    }

//    private void signOut() {
//        mAuth.signOut();
//        updateUI(null);
//    }

    public void Onclick(View view) {
        Intent intentThree= new Intent(Signup.this, Signin.class);
        startActivity(intentThree);
    }

}
