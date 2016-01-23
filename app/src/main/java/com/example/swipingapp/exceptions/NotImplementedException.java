package com.example.swipingapp.exceptions;

public class NotImplementedException extends RuntimeException {

    // Constructors
    public NotImplementedException() {
        super("Not implemented yet");
    }

    public NotImplementedException(String message) {
        super(message);
    }
}

