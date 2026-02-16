package by.vladislav.hotelreservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.vladislav.hotelreservation.entity.Convenience;

@Repository
public interface ConvenienceRepository extends JpaRepository<Convenience, Long> {
  Optional<Convenience> findByName(String name);
}