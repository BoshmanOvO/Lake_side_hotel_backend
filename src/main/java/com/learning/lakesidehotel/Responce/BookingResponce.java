package com.learning.lakesidehotel.Responce;

import com.learning.lakesidehotel.Models.BookedRoom;
import com.learning.lakesidehotel.Models.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.types.Binary;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


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
