package com.learning.lakesidehotel.Models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Long Roomid;
    private String RoomType;
    private String RoomPrice;

    private boolean isBooked = false;

    private List<BookedRoom> bookings;

    public Room() {
        this.bookings = new ArrayList<>();
    }



}
