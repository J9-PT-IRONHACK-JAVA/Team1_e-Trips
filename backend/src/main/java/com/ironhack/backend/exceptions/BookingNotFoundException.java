package com.ironhack.backend.exceptions;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(Long id){
        super("The booking with ID " + id + " was not found");
    }
}

