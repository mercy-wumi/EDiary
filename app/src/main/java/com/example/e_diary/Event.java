package com.example.e_diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Event extends AppCompatActivity {
    private Spinner spinner;
    Button imgUpload;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private static final int PICTURE_RESULT = 42;
    private StorageReference mStorageRef;
    final Calendar myCalendar = Calendar.getInstance();
    TextInputEditText eventTitle;
    TextInputEditText eventDetails;
    ImageView imageView;
    EventInput event_input;
    EditText eventDate;
    DatePickerDialog datePickerDialog;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        FirebaseUtil.openFbReference("EventInput");
        mFirebaseDatabase=  FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference=  FirebaseUtil.mDatabaseReference;
        mStorageRef = FirebaseStorage.getInstance().getReference();

        eventDate= findViewById(R.id.event_date);
        eventTitle= findViewById(R.id.event_title);
        eventDetails= findViewById(R.id.event_details);
        imageView= findViewById(R.id.image);

        Intent intent= getIntent();

//        eventTitle.setText(intent.getExtras().getString("title"));
//        eventDate.setText(intent.getExtras().getString("date"));
//        eventDetails.setText(intent.getExtras().getString("description"));

        //EventInput event_input= (EventInput) intent.getSerializableExtra("Event");

        /**guess the if statement should be removed */
        if (event_input == null){

            // TODO: Event Input cannot be null as you thought it to be later because you have initialized it here.

            event_input = new EventInput();
        }

        this.event_input = event_input;
        eventTitle.setText(event_input.getEventname());
        eventDate.setText(event_input.getEventdate());
        eventDetails.setText(event_input.getEventdetails());
        showimage(event_input.getEventimage());

        Toolbar mtoolbar=findViewById(R.id.eventToolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



//        spinner= findViewById(R.id.spinner);
//
//        List<String> categories= new ArrayList<>();
//        categories.add("Choose Category");
//        categories.add("School");
//        categories.add("Home");
//        categories.add("Friend");
//        categories.add("Work");
//        categories.add("Recreational");
//
//        ArrayAdapter<String> dataAdapter;
//        dataAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (adapterView.getItemAtPosition(i).equals("Choose Category")){
//
//                }
//                else {
//                    String item= adapterView.getItemAtPosition(i).toString();
//                    Toast.makeText(adapterView.getContext(), "selected: "+item, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

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

        // perform click event on edit text
        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Event.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                eventDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            final StorageReference ref= FirebaseUtil.mStorageRef.child(imageUri.getLastPathSegment());
            ref.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // This method is the correct way of getting download url from cloud storage
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url =  uri.toString(); //ref.getDownloadUrl().toString();
                            //getUploadSessionUri().toString();
                            event_input.setEventimage(url);
                            showimage(url);
                        }
                    });
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
                // Check if fields are empty
                if (isClean()){
                    Toast.makeText(this, "There's Nothing to delete!", Toast.LENGTH_SHORT).show();
                    return true;
                }
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

        // TODO: event_input cannot be null bcos you have initialized it above.
        //  Hence this part of the code will still run and that's what's causing the error
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

    /** I created this method to check if the fields are empty. So instead of checking if the event_input
     *  is null I checked for empty fields when the delete action is triggered*/
    private boolean isClean(){
        return eventTitle.getText().toString().equals("") ||
                eventDetails.getText().toString().equals("")
                ||eventDate.getText().toString().equals("");
    }

    private void showimage(String url){
        if (url != null && !url.isEmpty()){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.get().load(url).resize(width, width*2/3).centerCrop().into(imageView);
        }
    }
}
