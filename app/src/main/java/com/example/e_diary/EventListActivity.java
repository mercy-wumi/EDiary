package com.example.e_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class EventListActivity extends AppCompatActivity {
    ArrayList<Event> event;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    private RecyclerViewAdapter mAdapter;
    private FirebaseAuth mAuth;

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        BottomNavigationView bottomNav = findViewById(R.id.btmNav);
        bottomNav.setSelectedItemId(R.id.event_menu);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case  R.id.event_menu:
                        return true;
                    case  R.id.insert_menu:
                        startActivity(new Intent(getApplicationContext(), EditEventActvity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.logout_menu:
                        startActivity(new Intent(getApplicationContext(), Signin.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        Toolbar mtoolbar=findViewById(R.id.eventListToolbar);
        setSupportActionBar(mtoolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

       RecyclerView recyclerView= findViewById(R.id.recycler);
       mAdapter = new RecyclerViewAdapter();
       recyclerView.setAdapter(mAdapter);
       LinearLayoutManager eventLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       recyclerView.setLayoutManager(eventLayoutManager);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater= getMenuInflater();
//        inflater.inflate(R.menu.event_list_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.insert_menu:
//                Intent intent= new Intent(this, EditEventActvity.class);
//                startActivity(intent);
//                return true;
//            case R.id.logout_menu:
//                mAuth.getInstance().signOut();
//                Intent logout_intent= new Intent(this, Signin.class);
//                startActivity(logout_intent);
//                Toast.makeText(this, "user logged out successfully", Toast.LENGTH_SHORT).show();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseUtil.detachListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUtil.attachListener();
    }


    //    if(mAuth.currentUser == null){
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        finish()
//    }else{
//        Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
//    }
}
