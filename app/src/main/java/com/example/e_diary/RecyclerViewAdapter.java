package com.example.e_diary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    ArrayList<Event> event;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;

    public RecyclerViewAdapter() {
        FirebaseUtil.openFbReference("EventInput");
        mFirebaseDatabase=  FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference=  FirebaseUtil.mDatabaseReference;
       /*
        * added firebaseutil here
        * */
       event = FirebaseUtil.events;
        mChildListener= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Event event = snapshot.getValue(Event.class);
                assert event != null;
                event.setId(snapshot.getKey());
                RecyclerViewAdapter.this.event.add(event);
                notifyItemInserted(RecyclerViewAdapter.this.event.size()-1);
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        View view;
        LayoutInflater inflater=  LayoutInflater.from(context);
        view= inflater.inflate(R.layout.list_row, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.event_title.setText(event.get(position).getEventname());
        holder.event_date.setText(event.get(position).getEventdate ());
        holder.event_note.setText(event.get(position).getEventdetails());
        //holder.event_image.set
        if (event.get(position).getEventimage() != null) Picasso.get().load(event.get(position).getEventimage()).into(holder.event_image);
    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView event_title;
        TextView event_note;
        TextView event_date;
        ImageView event_image;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        event_title= itemView.findViewById(R.id.titleDiary);
        event_image= itemView.findViewById(R.id.imageDiary);
        event_date= itemView.findViewById(R.id.dateDiary);
        event_note= itemView.findViewById(R.id.noteDiary);
        itemView.setOnClickListener(this);

    }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("click", String.valueOf(position));
            Event selectedEvent= event.get(position);
//            Intent intent= new Intent(view.getContext(), MoreStory.class);
            Intent intent= new Intent(view.getContext(), MoreStory.class);
            intent.putExtra("Event", selectedEvent);
            view.getContext().startActivity(intent);
        }
    }

}
