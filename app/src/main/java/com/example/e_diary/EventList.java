package com.example.e_diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

       List<EventInput> eventList = new ArrayList<>();
        eventList.add(new EventInput(R.drawable.ediary, "Esther\'s Birthday","my sister Esther celebrated her birthday on the 21st of April and it was awesome though i wasn\'t there. she also got cake from her friend, peju."));
        eventList.add(new EventInput(R.drawable.ediary, "Esther\'s Birthday","my sister Esther celebrated her birthday on the 21st of April and it was awesome though i wasn\'t there. she also got cake from her friend, peju."));
        eventList.add(new EventInput(R.drawable.ediary, "Esther\'s Birthday","my sister Esther celebrated her birthday on the 21st of April and it was awesome though i wasn\'t there. she also got cake from her friend, peju."));
        eventList.add(new EventInput(R.drawable.ediary, "Esther\'s Birthday","my sister Esther celebrated her birthday on the 21st of April and it was awesome though i wasn\'t there. she also got cake from her friend, peju."));
        eventList.add(new EventInput(R.drawable.ediary, "Esther\'s Birthday","my sister Esther celebrated her birthday on the 21st of April and it was awesome though i wasn\'t there. she also got cake from her friend, peju."));

        RecyclerView eventRecycler= findViewById(R.id.recycler);
        RecyclerViewAdapter myadapter= new RecyclerViewAdapter(this, eventList);
        eventRecycler.setLayoutManager(new LinearLayoutManager(this));
        eventRecycler.setAdapter(myadapter);

    }
}
