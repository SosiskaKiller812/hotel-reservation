package by.vladislav.hotelreservation.controller;

import org.springframework.web.bind.annotation.RestController;

import by.vladislav.hotelreservation.entity.Hotel;
import by.vladislav.hotelreservation.service.HotelService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RestController
public class MainController {

  private final HotelService hotelService;

  @GetMapping("/hotel/name")
  public Hotel getHotel(@RequestParam String hotel) {
    return hotelService.findByName(hotel);
  }

  @GetMapping("/hotel/id/{id}")
  public ResponseEntity<Hotel> getMethodName(@PathVariable Long id) {
    return ResponseEntity.ok(hotelService.findById(id));
  }

}
