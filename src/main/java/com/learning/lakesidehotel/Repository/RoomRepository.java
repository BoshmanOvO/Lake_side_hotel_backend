package com.learning.lakesidehotel.Repository;

import com.learning.lakesidehotel.Models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, Integer> {
}
