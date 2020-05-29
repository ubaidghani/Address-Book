package com.example.addressbook;

public class ContactInfo {


    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int mobileNo;
    private int id;

    public ContactInfo(int id, String firstName, String lastName, int age, String email, int mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.mobileNo = mobileNo;
        this.id = id;
    }

    public ContactInfo() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }
}
