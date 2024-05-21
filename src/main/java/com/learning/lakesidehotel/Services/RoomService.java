package com.learning.lakesidehotel.Services;

import com.learning.lakesidehotel.Models.Room;
import com.learning.lakesidehotel.Repository.BookedRoomRepository;
import com.learning.lakesidehotel.Repository.RoomRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Component
public class RoomService implements IRoomService {

    @Autowired
    private Room room;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookedRoomRepository bookedRoomRepository;

    public int getMaxId(){
        return roomRepository.findAll().size() + 1;
    }
    @Override
    public Room addNewRoom(@NotNull MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException {
        Room r = room;
        r.setRoomid(getMaxId());
        r.setRoomType(roomType);
        r.setRoomPrice(roomPrice);
        if (!photo.isEmpty()) {
            r.setRoomImage(new Binary(BsonBinarySubType.BINARY, photo.getBytes()));
        }
        return roomRepository.save(r);
    }

    @Override
    public List<String> getAllRoomType() {
        return roomRepository.findDistinctRoomType().stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Binary getRoomPhotoById(Integer roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new RuntimeException("Room with id " + roomId + " not found");
        }
        Binary photo = room.get().getRoomImage();
        if (photo != null && photo.length() > 0) {
            return photo;
        }
        return null;
    }

    @Override
    public void deleteRoom(Integer roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new RuntimeException("Room with id " + roomId + " not found");
        }
        roomRepository.deleteById(roomId);
    }

    @Override
    public Room updateRoom(Integer roomId, MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new RuntimeException("Room with id " + roomId + " not found");
        }
        Room r = room.get();
        r.setRoomType(roomType);
        r.setRoomPrice(roomPrice);
        if (!photo.isEmpty()) {
            try {
                r.setRoomImage(new Binary(BsonBinarySubType.BINARY, photo.getBytes()));
            } catch (IOException e) {
                throw new IOException("Error occurred while updating room photo");
            }
        }
        return roomRepository.save(r);
    }

    @Override
    public Optional<Room> getRoomById(Integer roomId) {
        return Optional.of(roomRepository.findById(roomId).get());
    }
}