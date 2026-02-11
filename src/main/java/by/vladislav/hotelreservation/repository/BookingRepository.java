package by.vladislav.hotelreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.vladislav.hotelreservation.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

} 