package com.learning.lakesidehotel.Services;


import com.learning.lakesidehotel.Models.BookedRoom;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServices {

    public List<BookedRoom> getAllBookingsByRoomId(Integer roomid) {
        return null;
    }

}
