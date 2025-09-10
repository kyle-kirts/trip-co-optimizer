package com.tco.misc;

public class Person {

    String name;
    String netid;
    String hometown;
    String biography;

    private Person() {
    } // prevent use of default constructor -- please use Gson to serialize this

}
