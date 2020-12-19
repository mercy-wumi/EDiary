package com.example.e_diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MoreStory extends AppCompatActivity {
    Event event_input;
    ImageView eventImg;
    TextView titleDetail;
    TextView eventDescriptn;
    TextView eventDate;
    FloatingActionButton editbutton;
    private Event mEvent_input;

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
//                        startActivity(new Intent(getApplicationContext(), EditEventActvity.class));
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
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        eventImg = findViewById(R.id.eventimage);
        titleDetail = findViewById(R.id.Titledetails);
        eventDescriptn = findViewById(R.id.eventDescription);
        eventDate = findViewById(R.id.Date);
        editbutton = findViewById(R.id.floatingActionButton);

        Intent intent= getIntent();
        mEvent_input = (Event) intent.getSerializableExtra("Event");

        this.event_input = mEvent_input;
        assert event_input != null;
        titleDetail.setText(mEvent_input.getEventname());
        eventDate.setText(mEvent_input.getEventdate());
        eventDescriptn.setText(mEvent_input.getEventdetails());
        showimage(mEvent_input.getEventimage());

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleDetail.getText().toString();
                String date = eventDate.getText().toString();
                String description = eventDescriptn.getText().toString();
                //ImageView image = eventImg.setImageResource();
                Intent intent= new Intent(MoreStory.this, EditEventActvity.class);
                intent.putExtra("Event", mEvent_input);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("description", description);
                startActivity(intent);

//                int position = getAdapterPosition();
//                Log.d("click", String.valueOf(position));
//                Event selectedEvent= event.get(position);
//                Intent intent= new Intent(view.getContext(), MoreStory.class);
//                Intent intent= new Intent(view.getContext(), EditEventActvity.class);
//                intent.putExtra("EditEventActvity", selectedEvent);
//                view.getContext().startActivity(intent);
            }
        });

    }

    private void showimage(String url){
        if (url != null && !url.isEmpty()){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.get().load(url).resize(width, width*2/3).centerCrop().into(eventImg);
        }
    }
}