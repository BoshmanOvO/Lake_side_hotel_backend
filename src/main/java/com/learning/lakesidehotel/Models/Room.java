package com.learning.lakesidehotel.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Document(collection = "rooms")
public class Room {

    @Id
    private Long Roomid;

    private String RoomType;
    private BigDecimal RoomPrice;

    private boolean isBooked = false;

    @Lob
    private Blob RoomImage;

    // one room can be booked by many guests
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private List<BookedRoom> bookings;

    // one room can be booked by many guests

    public Room() {
        this.bookings = new ArrayList<>();
    }

    public void addBooking(BookedRoom booking) {
        if(this.bookings == null) {
            this.bookings = new ArrayList<>();
        }
        this.bookings.add(booking);
        booking.setRoom(this);
        this.isBooked = true;
        String code = booking.getBookingId() + booking.getGuestName().substring(0, 3);
        booking.setBookingConfirmationCode(code);
    }
}


