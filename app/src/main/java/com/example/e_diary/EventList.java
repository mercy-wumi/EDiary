package com.example.e_diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {
    ArrayList<EventInput> event;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    private RecyclerViewAdapter mAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        mAuth = FirebaseAuth.getInstance();
        Toolbar mtoolbar=findViewById(R.id.eventListToolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

       RecyclerView recyclerView= findViewById(R.id.recycler);
       mAdapter = new RecyclerViewAdapter();
       recyclerView.setAdapter(mAdapter);
       LinearLayoutManager eventLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       recyclerView.setLayoutManager(eventLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.event_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert_menu:
                Intent intent= new Intent(this, Event.class);
                startActivity(intent);
                return true;
            case R.id.logout_menu:
                mAuth.getInstance().signOut();
                Intent logout_intent= new Intent(this, Signin.class);
                startActivity(logout_intent);
                Toast.makeText(this, "user logged out successfully", Toast.LENGTH_SHORT).show();
                return true;

//                        .addOnCompleteListener(new OnCompleteListener<Void>(){
//                    public void onComplete(@NonNull Task<Void> task){
//
//                    }
//
//                });
        }
        return super.onOptionsItemSelected(item);
    }

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
