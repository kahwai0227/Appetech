package com.example.appetech_smart_cafeteria;

public class Table {
    public Boolean tableBooked;
    public String bookingUserEmail;
    public String location;
    public Integer bookingTime;

    public Table(){
    }
    public Table(String location){
        this.location = location;
        this.tableBooked = false;
        this.bookingUserEmail = null;
        this.bookingTime = null;
    }

    public String getLocation() { return location; }
    public Boolean getTableBooked() { return tableBooked; }
    public String getBookingUser() { return bookingUserEmail; }
    public Integer getBookingTime() { return bookingTime; }
}
