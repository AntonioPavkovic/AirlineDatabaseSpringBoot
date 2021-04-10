package com.airlinedatabase.airlinedatabase.exceptions;

public class RecordAlreadyPresentException extends RuntimeException{
    public RecordAlreadyPresentException(String s){
        super(s);
    }
}
