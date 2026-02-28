package by.vladislav.hotelreservation.controller;

import org.springframework.web.bind.annotation.RestController;

import by.vladislav.hotelreservation.entity.dto.HotelDTO;
import by.vladislav.hotelreservation.service.HotelService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelCRUD {

  private final HotelService hotelService;

  @PostMapping
  public ResponseEntity<HotelDTO> create(@RequestBody HotelDTO hotelRequest, @RequestParam Boolean isException) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotelRequest, isException));
  }

  @PostMapping("/list")
  public ResponseEntity<List<HotelDTO>> createList(@RequestBody List<HotelDTO> hotelRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createList(hotelRequest));
  }

  @PostMapping("/non-transactional")
  public ResponseEntity<HotelDTO> postMethodName(@RequestBody HotelDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(hotelService.createWithoutTransactional(hotelRequest, true));
  }

  @GetMapping("/{id}")
  public ResponseEntity<HotelDTO> findById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<HotelDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.findAll());
  }

  @PutMapping
  public ResponseEntity<HotelDTO> update(@RequestBody HotelDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.update(hotelRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeById(@PathVariable Long id) {
    hotelService.removeById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted");
  }
}
