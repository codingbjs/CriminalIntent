package com.codingbjs.criminalintent.crime;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID uuid;
    private String title;
    private Date date;
    private boolean solved;

    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
//    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime (UUID id){
        this.uuid = id;
        setDate(new Date());
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
