package com.learning.lakesidehotel.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookedrooms")
public class BookedRoom {

    @Id
    private Long bookingId;

//    @Column(name = "check_in_date")
    private LocalDate checkInDate;

//    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

//    @Column(name = "guest_name")
    private String guestName;

//    @Column(name = "guest_email")
    private String guestEmail;

//    @Column(name = "num_of_guests")
    private int NumofGuests;

//    @Column(name = "num_of_adults")
    private int NumofAdults;

//    @Column(name = "num_of_children")
    private int NumofChildren;

//    @Column(name = "booking_confirmation_code")
    private String bookingConfirmationCode;


    // many guests can book one room at different times in the future
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id")
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
