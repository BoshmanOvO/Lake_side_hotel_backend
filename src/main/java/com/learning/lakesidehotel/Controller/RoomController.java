package com.learning.lakesidehotel.Controller;

import com.learning.lakesidehotel.Models.BookedRoom;
import com.learning.lakesidehotel.Models.Room;
import com.learning.lakesidehotel.Responce.RoomResponce;
import com.learning.lakesidehotel.Services.BookingServices;
import com.learning.lakesidehotel.Services.RoomService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired // autowired is used to inject the object dependency implicitly(automatically)
    private BookingServices bookingServices;

    @PostMapping(value = "/add/new-room", headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomResponce> addNewRoom(@RequestPart("photo") @NotNull MultipartFile photo, @RequestParam("roomType") String roomType, @RequestParam("roomPrice") BigDecimal roomPrice)
            throws IOException {
        System.out.println("RoomType: " + roomType + " RoomPrice: " + roomPrice + " Photo: " + photo.getOriginalFilename());
        try {
            Room r = roomService.addNewRoom(photo, roomType, roomPrice);
            RoomResponce roomResponce = new RoomResponce(r.getRoomid(), r.getRoomType(), r.getRoomPrice());
            return new ResponseEntity<RoomResponce>(roomResponce, HttpStatus.CREATED); // code 201
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // code 409
        }
    }
    @GetMapping("/room/type")
    public List<String> getAllRoomType() {
        return roomService.getAllRoomType();
    }

    @GetMapping("/all-rooms")
    public ResponseEntity<List<RoomResponce>> getAllRooms() {
        List<Room> rooms = roomService.getAllRoom();
        List<RoomResponce> roomResponces = new ArrayList<>();
        for (Room room : rooms) {
            Binary photoByte = roomService.getRoomPhotoById(room.getRoomid());
            if (photoByte != null && photoByte.length() > 0) {
                String photoUrl = Base64.encodeBase64String(photoByte.getData());
                RoomResponce rR = getRoomResponce(room);
                rR.setPhotoUrl(photoUrl);
                roomResponces.add(rR);
            }
        }
        return ResponseEntity.ok(roomResponces); // code 200
    }

    @NotNull
    public RoomResponce getRoomResponce(@NotNull Room room) {
        List<BookedRoom> bookedRooms = bookingServices.getAllBookingsByRoomId(room.getRoomid());
//        List<BookingResponce> bookingInfo = bookedRooms
//                .stream()
//                .map(bookedRoom -> new BookingResponce(bookedRoom.getBookingId(), bookedRoom.getCheckInDate(), bookedRoom.getCheckOutDate(), bookedRoom.getBookingConfirmationCode()))
//                .collect(Collectors.toList());
        Binary photoByte = room.getRoomImage();
        String photoUrl = photoByte != null ? Base64.encodeBase64String(photoByte.getData()) : null;
        System.out.println(room);
        return new RoomResponce(room.getRoomid(), room.getRoomType(), room.getRoomPrice(), room.isBooked(), photoByte);
    }

    @DeleteMapping("/delete/room/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Integer roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // code 204
    }

    @PutMapping(value = "/update/room/{roomId}",  headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<RoomResponce> updateRoom(@PathVariable(value = "roomId", required = false) Integer roomId,
                @RequestParam(value = "roomType", required = false) String roomType,
                @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
                @RequestPart(value = "roomPhoto", required = false) @NotNull MultipartFile photo)
            throws IOException {
        System.out.println("RoomId: " + roomId + " RoomType: " + roomType + " RoomPrice: " + roomPrice + " Photo: " + photo.getOriginalFilename());
        Binary photoByte = photo != null && !photo.isEmpty() ? new Binary(BsonBinarySubType.BINARY, photo.getBytes()) : roomService.getRoomPhotoById(roomId);
        String photoUrl = (photoByte != null && photoByte.length() > 0) ? Base64.encodeBase64String(photoByte.getData()) : null;
        Room r = roomService.updateRoom(roomId, photo, roomType, roomPrice);
        RoomResponce roomResponce = getRoomResponce(r);
        roomResponce.setPhotoUrl(photoUrl);
        return ResponseEntity.ok(roomResponce); // code 200
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<Optional<RoomResponce>> getRoomById(@PathVariable("roomId") Integer roomId) {
        Optional<Room> room = roomService.getRoomById(roomId);
        if (room.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // code 404
        }
        return room.map(
                r -> {
                    RoomResponce roomResponce = getRoomResponce(r);
                    return ResponseEntity.ok(Optional.of(roomResponce)); // code 200
                }).orElseThrow(() -> new RuntimeException("Room with id " + roomId + " not found")
        );
    }

}