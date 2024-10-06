package com.example.lab4_20210548.Objets;

import java.io.Serializable;
import java.util.List;

public class ResultDTO implements Serializable {
    List<Result> events;

    public List<Result> getEvents() {
        return events;
    }

    public void setEvents(List<Result> events) {
        this.events = events;
    }
}
