package by.vladislav.hotelreservation.controller;

import org.springframework.web.bind.annotation.RestController;

import by.vladislav.hotelreservation.entity.DTO.HotelDTO;
import by.vladislav.hotelreservation.service.HotelService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelCRUD {

  private final HotelService hotelService;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody HotelDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotelRequest));
  }

  @PostMapping("/create/non-transactional")
  public ResponseEntity<?> postMethodName(@RequestBody HotelDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.createWithoutTransactional(hotelRequest, false));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.findById(id));
  }

  @GetMapping("")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.findAll());
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody HotelDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.update(hotelRequest));
  }

  @DeleteMapping("/remove/{id}")
  public ResponseEntity<?> removeById(@PathVariable Long id) {
    hotelService.removeById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted");
  }
}
