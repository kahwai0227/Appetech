package com.example.appetech_smart_cafeteria;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class Table{
    public String tableNo;
    public String bookingUsername;
    public Boolean tableBooked;
    public String bookingUserEmail;
    public String location;
    public String bookingTime;
    public String bookingUserContact;
    public Boolean tableOccupied;

    public Table(){
    }
    public void addTableNo(String tableNo){
        this.tableNo = tableNo;
    }
    public void addBookingUsername(String bookingUsername){
        this.bookingUsername = bookingUsername;
    }
    public void addLocation(String location){
        this.location = location;
    }
    public void addBooking(Boolean tableBooked){
        this.tableBooked = tableBooked;
    }
    public void addBookingUserEmail(String bookingUserEmail){
        this.bookingUserEmail = bookingUserEmail;
    }
    public void addBookingTime(String bookingTime){
        this.bookingTime = bookingTime;
    }
    public void addBookingUserContact(String bookingUserContact){
        this.bookingUserContact = bookingUserContact;
    }

    public String getTableNo() { return tableNo; }
    public String getLocation() { return location; }
    public Boolean getTableBooked() { return tableBooked; }
    public String getBookingUserEmail() { return bookingUserEmail; }
    public String getBookingTime() { return bookingTime; }
    public String getBookingUserContact() { return bookingUserContact; }
    public String getBookingUsername() { return bookingUsername; }
    public Boolean getTableOccupied() { return tableOccupied; }
    public void removeBooking(){
        this.bookingUsername = null;
        this.bookingUserContact = null;
        this.bookingUserEmail = null;
        this.bookingTime = null;
        this.tableBooked = false;
    }
}
