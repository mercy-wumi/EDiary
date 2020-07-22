package com.example.e_diary;

import java.io.Serializable;

public class EventInput implements Serializable {
    private String eventname;
    private String eventdetails;
    private String eventdate;
    private String  eventimage;
    private String id;

    public EventInput(){}

    public EventInput(String pic, String title,String details, String date ){
        this.eventname= title;
        this.eventdate= date;
        this.eventdetails= details;
        this.eventimage= pic;
    }

    public String getEventname() {
        return eventname;
    }

    public String getEventdetails() {
        return eventdetails;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getEventimage() {
        return eventimage;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setEventdetails(String eventdetails) {
        this.eventdetails = eventdetails;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public void setEventimage(String eventimage) {
        this.eventimage = eventimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
