package com.example.appetech_smart_cafeteria;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class Table{
    public String bookingUID;
    public String tableNo;
    public Boolean tableBooked;
    public String location;
    public String bookingTime;
    public String bookingUserContact;
    public Boolean tableOccupied;

    public Table(){
    }
    public void addTableNo(String tableNo){
        this.tableNo = tableNo;
    }
    public void addLocation(String location){
        this.location = location;
    }
    public void addBooking(Boolean tableBooked){
        this.tableBooked = tableBooked;
    }
    public void addBookingTime(String bookingTime){
        this.bookingTime = bookingTime;
    }
    public void addBookingUserContact(String bookingUserContact){ this.bookingUserContact = bookingUserContact; }
    public void addBookingUID(String bookingUID){ this.bookingUID = bookingUID; }

    public String getTableNo() { return tableNo; }
    public String getLocation() { return location; }
    public Boolean getTableBooked() { return tableBooked; }
    public String getBookingTime() { return bookingTime; }
    public String getBookingUserContact() { return bookingUserContact; }
    public Boolean getTableOccupied() { return tableOccupied; }

    public void removeBooking(){
        this.bookingUID = null;
        this.bookingUserContact = null;
        this.bookingTime = null;
        this.tableBooked = false;
    }
}
