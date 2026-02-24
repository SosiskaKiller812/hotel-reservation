package by.vladislav.hotelreservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.vladislav.hotelreservation.entity.DTO.BookingDTO;
import by.vladislav.hotelreservation.service.BookingService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("booking/")
public class BookingCRUD {
  private final BookingService bookingService;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody BookingDTO bookingRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.create(bookingRequest));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.findById(id));
  }

  @GetMapping("")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.status(HttpStatus.FOUND).body(bookingService.findAll());
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@RequestBody BookingDTO bookingRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(bookingService.update(bookingRequest));
  }

  @DeleteMapping("/remove/{id}")
  public ResponseEntity<?> removeById(@PathVariable Long id) {
    bookingService.removeById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted");
  }

}
