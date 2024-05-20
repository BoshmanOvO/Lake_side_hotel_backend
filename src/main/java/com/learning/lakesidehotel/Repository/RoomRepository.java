package com.learning.lakesidehotel.Repository;

import com.learning.lakesidehotel.Models.Room;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, Integer> {
//    Select unique RoomType without id
    @Query(value = "{'RoomType': {$exists: true}}", fields = "{'RoomType': 1, '_id': 1}")
    List<String> findDistinctRoomType();

}
