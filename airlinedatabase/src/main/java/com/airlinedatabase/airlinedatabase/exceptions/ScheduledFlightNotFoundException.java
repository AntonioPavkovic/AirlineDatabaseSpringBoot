package com.airlinedatabase.airlinedatabase.exceptions;

public class ScheduledFlightNotFoundException extends RuntimeException{

    private static final long serialVersionId = 1L;

    public ScheduledFlightNotFoundException(String s){
        super(s);
    }

}
