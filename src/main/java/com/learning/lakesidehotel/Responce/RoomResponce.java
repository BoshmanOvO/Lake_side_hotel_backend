package com.learning.lakesidehotel.Responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponce {
    private Long id;
    private String roomType;
    private BigDecimal price;
    private boolean isBooked;
    private String photoUrl;
    private List<BookingResponce> bookings;

    public RoomResponce(Long id, String roomType, BigDecimal price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
    }

    public RoomResponce(Long id, String roomType, BigDecimal price, boolean isBooked,
                        byte [] photobyte, List<BookingResponce> bookings) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = isBooked;
        this.photoUrl = photobyte != null ? Base64.encodeBase64String(photobyte) : null;
        this.bookings = bookings;
    }
}
