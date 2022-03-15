package com.example.norman_lee.myapplication;

public class Utils {
    static void checkInvalidInputs (String value) throws IllegalArgumentException{
        if (Double.parseDouble(value)<=0) throw new IllegalArgumentException();
    }
}
