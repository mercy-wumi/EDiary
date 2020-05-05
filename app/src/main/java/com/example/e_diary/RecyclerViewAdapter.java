package com.example.e_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    private Context mContext;
    private List<EventInput> mData;

    public RecyclerViewAdapter(Context mContext, List<EventInput> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=  LayoutInflater.from(mContext);
        view= inflater.inflate(R.layout.list_row, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
    holder.event_title.setText(mData.get(position).getEventname());
    //holder.event_date.setText(mData.get(position).getEventdate());
    holder.event_note.setText(mData.get(position).getEventdetails());
    holder.event_image.setImageResource(mData.get(position).getEventimage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView event_title;
        TextView event_note;
        //TextView event_date;
        ImageView event_image;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        event_title= itemView.findViewById(R.id.titleDiary);
        event_image= itemView.findViewWithTag(R.id.imageDiary);
        //event_date= itemView.findViewById(R.id.dateDiary);
        event_image= itemView.findViewById(R.id.imageDiary);

    }
}

}
