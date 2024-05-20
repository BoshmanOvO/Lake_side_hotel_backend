package com.learning.lakesidehotel.Repository;

import com.learning.lakesidehotel.Models.BookedRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookedRoomRepository extends MongoRepository<BookedRoom, Integer> {
}
