package com.example.demo.Model;

public class Person {
    private final String firstname;
    private final String lastname;
    private final int id;

    public Person(int id,String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getId() {
        return id;
    }
}
