package com.learning.lakesidehotel.Models;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BookedRoom")
public class BookedRoom {

    @Id
    private Long bookingId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String guestName;
    private String guestEmail;
    private int NumofGuests;
    private int NumofAdults;
    private int NumofChildren;

    @Setter
    private String bookingConfirmationCode;

    private Room room;

    public void calcTotalGuest() {
        this.NumofGuests = this.NumofAdults + this.NumofChildren;
    }

    public void setNumofAdults(int numofAdults) {
        NumofAdults = numofAdults;
        calcTotalGuest();
    }

    public void setNumofChildren(int numofChildren) {
        NumofChildren = numofChildren;
        calcTotalGuest();
    }

}
