package com.example.e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }
    public void Onclick(View view) {
        Intent intentFour= new Intent(signin.this, signup.class);
        startActivity(intentFour);
    }

    public void eventlist(View view) {
        Intent intentevent= new Intent(signin.this, EventList.class);
        startActivity(intentevent);
    }
}

