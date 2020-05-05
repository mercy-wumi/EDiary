package com.example.e_diary;

import android.widget.ImageView;

public class EventInput {
    private String eventname;
    private String eventdetails;
    //private String eventdate;
    private int eventimage;

// add date as parameter to EventInput constructor

    public EventInput(int pic, String title,String details ){
        eventimage= pic;
        eventname= title;
        //eventdate = date;
        eventdetails = details;
    }

    public String getEventname() {
        return eventname;
    }

    public String getEventdetails() {
        return eventdetails;
    }

//    public String getEventdate() {
//        return eventdate;
//    }

    public int getEventimage() {
        return eventimage;
    }

//    public void setEventdate(String eventdate) {
//        this.eventdate = eventdate;
//    }

}
