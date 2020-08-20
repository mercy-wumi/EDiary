package com.example.e_diary;

import java.io.Serializable;

public class EventInput implements Serializable {
    private String eventname;
    private String eventdetails;
    private String eventdate;
    private String eventimage;
    private String id;

    public EventInput (){}

    public EventInput(String eventimage, String eventname,String eventdetails, String eventdate){
        this.setEventimage(eventimage);
        this.setEventdetails(eventdetails);
        this.setEventdate(eventdate);
        this.setEventname(eventname);
        this.setId(id);
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdetails() {
        return eventdetails;
    }

    public void setEventdetails(String eventdetails) {
        this.eventdetails = eventdetails;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventimage() {
        return eventimage;
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
