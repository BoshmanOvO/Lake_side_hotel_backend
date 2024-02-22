package com.learning.lakesidehotel.Responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponce {
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String guestName;
    private String guestEmail;
    private int numberOfAdults;
    private int numberOfChildren;
    private int totalGuests;
    private String bookingConfirmationCode;
    private RoomResponce room;
    public BookingResponce(Long id, LocalDate checkIn, LocalDate checkOut, String bookingConfirmationCode) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
