package com.learning.lakesidehotel.Responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponce {
    private Integer id;
    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked;
    private String photoUrl;
    private List<BookingResponce> bookings;

    public RoomResponce(Integer id, String roomType, BigDecimal price) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = price;
    }

    public RoomResponce(Integer id, String roomType, BigDecimal price, boolean isBooked,
                        Binary photobyte) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = price;
        this.isBooked = isBooked;
        this.photoUrl = photobyte != null ? Base64.encodeBase64String(photobyte.getData()) : null;
//        this.bookings = bookings;
    }

}