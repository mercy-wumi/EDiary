package com.example.e_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MoreStory extends AppCompatActivity {
    EventInput event_input;
    ImageView eventImg;
    TextView titleDetail;
    TextView eventDescriptn;
    TextView eventDate;
    FloatingActionButton editbutton;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_story);

//        BottomNavigationView bottomNav = findViewById(R.id.btmNav);
//        bottomNav.setSelectedItemId(R.id.event_menu);
//        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case  R.id.event_menu:
//                        return true;
//                    case  R.id.insert_menu:
//                        startActivity(new Intent(getApplicationContext(), Event.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case  R.id.logout_menu:
//                        startActivity(new Intent(getApplicationContext(), Signin.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });

        Toolbar mtoolbar=findViewById(R.id.eventToolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        eventImg = findViewById(R.id.eventimage);
        titleDetail = findViewById(R.id.Titledetails);
        eventDescriptn = findViewById(R.id.eventDescription);
        eventDate = findViewById(R.id.Date);
        editbutton = findViewById(R.id.floatingActionButton);
        Intent intent= getIntent();
        EventInput event_input= (EventInput) intent.getSerializableExtra("Event");

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleDetail.getText().toString();
                String date = eventDate.getText().toString();
                String description = eventDescriptn.getText().toString();
                //ImageView image = eventImg.setImageResource();
                Intent intent= new Intent(MoreStory.this, Event.class);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("description", description);
                startActivity(intent);

//                int position = getAdapterPosition();
//                Log.d("click", String.valueOf(position));
//                EventInput selectedEvent= event.get(position);
//                Intent intent= new Intent(view.getContext(), MoreStory.class);
//                Intent intent= new Intent(view.getContext(), Event.class);
//                intent.putExtra("Event", selectedEvent);
//                view.getContext().startActivity(intent);
            }
        });

        this.event_input = event_input;
        titleDetail.setText(event_input.getEventname());
        eventDate.setText(event_input.getEventdate());
        eventDescriptn.setText(event_input.getEventdetails());
        showimage(event_input.getEventimage());
    }

    private void showimage(String url){
        if (url != null && !url.isEmpty()){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.get().load(url).resize(width, width*2/3).centerCrop().into(eventImg);
        }
    }
}