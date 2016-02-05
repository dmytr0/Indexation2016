package com.lemon.Exceptions;


import java.io.IOException;

public class ProblemFileException extends IOException {

    public ProblemFileException(String message) {
        super(message);
    }
}
