package com.learning.lakesidehotel.Controller;

import com.learning.lakesidehotel.Models.BookedRoom;
import com.learning.lakesidehotel.Models.Room;
import com.learning.lakesidehotel.Responce.BookingResponce;
import com.learning.lakesidehotel.Responce.RoomResponce;
import com.learning.lakesidehotel.Services.BookingServices;
import com.learning.lakesidehotel.Services.IRoomService;
import com.learning.lakesidehotel.Services.RoomService;
import org.bson.types.Binary;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private BookingServices bookingServices;

    @Autowired
    private Room room;

    @RequestMapping(value = "/add/new-room", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RoomResponce> addNewRoom(
            @RequestPart(value = "photo", required = true) MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") BigDecimal roomPrice) throws IOException {
        Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        RoomResponce roomResponce = new RoomResponce(savedRoom.getRoomid(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
        return ResponseEntity.ok(roomResponce);
    }

    @GetMapping("/room/type")
    public List<String> getRoomType() {
        return roomService.getAllRoomType();
    }

    @GetMapping("/all-rooms")
    public ResponseEntity<List<RoomResponce>> getAllRoom() {
        List<Room> rooms = roomService.getAllRoom();
        List<RoomResponce> roomResponces = new ArrayList<>();
        for (Room r : rooms) {
            byte[] photo = roomService.getRoomPhotoById(r.getRoomid()).getData();
            if (photo != null) {
                roomResponces.add(new RoomResponce(r.getRoomid(), r.getRoomType(), r.getRoomPrice(), r.isBooked(), new Binary(photo)));
            }
            else {
                roomResponces.add(new RoomResponce(r.getRoomid(), r.getRoomType(), r.getRoomPrice()));
            }
        }
        return ResponseEntity.ok(roomResponces);
    }

    @Contract("_ -> new")
    private @NotNull RoomResponce getRoomResponce(@NotNull Room r) {
        List<BookedRoom> bookedRooms = bookingServices.getAllBookingsByRoomId(r.getRoomid());
//        List<BookingResponce> bookingsInfo = bookedRooms
//                .stream()
//                .map(bookedRoom -> new BookingResponce(bookedRoom.getBookingId(), bookedRoom.getCheckInDate(), bookedRoom.getCheckOutDate(), bookedRoom.getBookingConfirmationCode()))
//                .toList();
        Binary photo = roomService.getRoomPhotoById(r.getRoomid());
        if (photo != null) {
            try {
                return new RoomResponce(r.getRoomid(), r.getRoomType(), r.getRoomPrice(), r.isBooked(), photo);
            }
            catch (HttpClientErrorException.NotFound e) {
                return new RoomResponce(r.getRoomid(), r.getRoomType(), r.getRoomPrice());
            }
        }
        return new RoomResponce(r.getRoomid(), r.getRoomType(), r.getRoomPrice());
    }

}
