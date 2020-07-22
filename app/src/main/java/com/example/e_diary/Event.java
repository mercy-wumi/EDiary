package com.example.e_diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Event extends AppCompatActivity {
    private Spinner spinner;
    Button imgUpload;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private static final int PICTURE_RESULT = 42;

    TextInputEditText eventDate;
    TextInputEditText eventTitle;
    TextInputEditText eventDetails;
    EventInput event_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        FirebaseUtil.openFbReference("EventInput");
        mFirebaseDatabase=  FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference=  FirebaseUtil.mDatabaseReference;
        eventDate= findViewById(R.id.event_date);
        eventTitle= findViewById(R.id.event_title);
        eventDetails= findViewById(R.id.event_details);
        Intent intent= getIntent();
        EventInput event_input= (EventInput) intent.getSerializableExtra("Event");
        if (event_input==null){
            event_input= new EventInput();
        }
        this.event_input = event_input;
        eventTitle.setText(event_input.getEventname());
        eventDate.setText(event_input.getEventdate());
        eventDetails.setText(event_input.getEventdetails());

        Toolbar mtoolbar=findViewById(R.id.eventToolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        spinner= findViewById(R.id.spinner);

        List<String> categories= new ArrayList<>();
        categories.add("Choose Category");
        categories.add("School");
        categories.add("Home");
        categories.add("Friend");
        categories.add("Work");
        categories.add("Recreational");

        ArrayAdapter<String> dataAdapter;
        dataAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Category")){

                }
                else {
                    String item= adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), "selected: "+item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imgUpload = findViewById(R.id.uploadImg);
        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(intent.createChooser(intent,"Insert Picture"), PICTURE_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            StorageReference ref= FirebaseUtil.mStorageRef.child(imageUri.getLastPathSegment());
            ref.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String url = taskSnapshot.getUploadSessionUri().toString();
                    event_input.setEventimage(url);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveEvent();
                Toast.makeText(this, "Event Saved", Toast.LENGTH_LONG).show();
                clean();
                backToList();
                return true;
            case R.id.delete_menu:
                deleteEvent();
                Toast.makeText(this, "Event deleted", Toast.LENGTH_LONG).show();
                backToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    private void saveEvent(){
        event_input.setEventname(eventTitle.getText().toString());
        event_input.setEventdate(eventDate.getText().toString());
        event_input.setEventdetails(eventDetails.getText().toString());
        if (event_input.getId() == null){
            mDatabaseReference.push().setValue(event_input);
        }
        else {
            mDatabaseReference.child(event_input.getId()).setValue(event_input);
        }
    }

    private void deleteEvent(){
        if (event_input == null){
            Toast.makeText(this, "please save this event before deleting", Toast.LENGTH_SHORT).show();
            return;
        }
        mDatabaseReference.child(event_input.getId()).removeValue();
    }

    private void backToList(){
        Intent intent= new Intent(this, EventList.class);
        startActivity(intent);
    }

    private void clean(){
        eventTitle.setText("");
        eventDetails.setText("");
        eventDate.setText("");
        eventTitle.requestFocus();
    }
}
