package by.vladislav.hotelreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.vladislav.hotelreservation.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}