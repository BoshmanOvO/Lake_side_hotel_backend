package com.learning.lakesidehotel;

import com.learning.lakesidehotel.Models.BookedRoom;
import com.learning.lakesidehotel.Repository.BookedRoomRepository;
import com.learning.lakesidehotel.Repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableMongoRepositories(basePackageClasses = {RoomRepository.class, BookedRoomRepository.class})
public class LakeSideHotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(LakeSideHotelApplication.class, args);
    }
}
