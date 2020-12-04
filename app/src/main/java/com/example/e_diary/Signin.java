//package com.example.e_diary;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//public class Signin extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signin);
//    }
//    public void Onclick(View view) {
//        Intent intentFour= new Intent(Signin.this, Signup.class);
//        startActivity(intentFour);
//    }
//
//    public void eventlist(View view) {
//        Intent intentevent= new Intent(Signin.this, EventList.class);
//        startActivity(intentevent);
//    }
//}

package com.example.e_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mail, pass;
    Button signin;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Toolbar mtoolbar=findViewById(R.id.signin_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mail= findViewById(R.id.username_txt);
        pass= findViewById(R.id.pass_txt);
        signin = findViewById(R.id.signin_btn);

        mAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event_mail= mail.getText().toString().trim();
                String event_pass= pass.getText().toString().trim();


                if (TextUtils.isEmpty(event_mail)){
                    Toast.makeText(Signin.this, "please enter mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(event_pass)){
                    Toast.makeText(Signin.this, "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (event_pass.length()<6){
//                    Toast.makeText(Signup.this, "password too short", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                mAuth.signInWithEmailAndPassword(event_mail, event_pass)
                        .addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "signInWithEmail:success");
                                    startActivity(new Intent(getApplicationContext(), EventList.class));
                                    Toast.makeText(Signin.this, "login Successful", Toast.LENGTH_SHORT).show();
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Signin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
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
        //updateUI(currentUser);
    }

//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            username.setVisibility(View.GONE);
//            pass.setVisibility(View.GONE);
//
//        } else {
//
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
        Intent intentThree= new Intent(Signin.this, Signup.class);
        startActivity(intentThree);
    }

}
