package by.vladislav.hotelreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.vladislav.hotelreservation.entity.Hotel;
import by.vladislav.hotelreservation.repository.HotelRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class HotelService {

  private final HotelRepository repository;

  public Hotel findByName(String name) {
    return repository.findByName(name).orElse(null);
  }

  public Hotel findById(long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("PIZDA"));
  }

  public List<Hotel> findAll() {
    return repository.findAll();
  }
}
