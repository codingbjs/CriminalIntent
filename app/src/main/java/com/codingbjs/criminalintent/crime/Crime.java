package com.codingbjs.criminalintent.crime;

import java.util.UUID;

public class Crime {
    private UUID uuid;
    private String title;

    public Crime() {
        this.uuid = UUID.randomUUID();
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
}
