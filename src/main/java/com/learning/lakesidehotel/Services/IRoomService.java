package com.learning.lakesidehotel.Services;

import com.learning.lakesidehotel.Models.Room;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IRoomService {
    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException;
    List<String> getAllRoomType();
    List<Room> getAllRoom();
    Binary getRoomPhotoById(Integer roomid);
    void deleteRoom(Integer roomid);

    Room updateRoom(Integer roomId, MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException;

    Optional<Room> getRoomById(Integer roomId);
}
