package com.learning.lakesidehotel.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Document(collection = "rooms")
@Component
public class Room {

    @Id
    private Integer roomid;

    private String RoomType;
    private BigDecimal RoomPrice;

    private boolean isBooked = false;

    private Binary RoomImage;
    // one room can be booked by many guests
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private List<BookedRoom> bookings;

    // one room can be booked by many guests

    public Room() {
        this.bookings = new ArrayList<>();
    }

    public void addBooking(BookedRoom booking) {
        if (this.bookings == null) {
            this.bookings = new ArrayList<>();
        }
        this.bookings.add(booking);
        booking.setRoom(this);
        this.isBooked = true;
        String code = booking.getBookingId() + booking.getGuestName().substring(0, 3);
        booking.setBookingConfirmationCode(code);
    }
}


