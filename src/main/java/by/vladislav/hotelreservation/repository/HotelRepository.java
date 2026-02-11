package by.vladislav.hotelreservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.vladislav.hotelreservation.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
  Optional<Hotel> findByName(String name);

}
