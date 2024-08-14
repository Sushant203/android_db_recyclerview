package com.example.myapplicationdb;

public class Student {
    private String id;
    private String name;
    private String rollNo;
    private String address;

    public Student(String id, String name, String rollNo, String address) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getAddress() {
        return address;
    }
}

