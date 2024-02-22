package com.learning.lakesidehotel.Repository;

import com.learning.lakesidehotel.Models.BookedRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookedRoomRepository extends MongoRepository<BookedRoom, Integer> {
}
