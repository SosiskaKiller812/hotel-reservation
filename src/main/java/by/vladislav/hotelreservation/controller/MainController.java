package by.vladislav.hotelreservation.controller;

import org.springframework.web.bind.annotation.RestController;

import by.vladislav.hotelreservation.entity.dto.HotelDTO;
import by.vladislav.hotelreservation.service.HotelService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestController
public class MainController {

  private final HotelService hotelService;

  @GetMapping("/hotel/name")
  public ResponseEntity<HotelDTO> getHotel(@RequestParam String hotel) {
    return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.findByName(hotel));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<HotelDTO> getMethodName(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.findById(id));
  }

}
