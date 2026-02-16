package by.vladislav.hotelreservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.vladislav.hotelreservation.entity.DTO.ConvenienceDTO;
import by.vladislav.hotelreservation.service.ConvenienceService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ConvenienceCRUD {
  private final ConvenienceService convenienceService;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody ConvenienceDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(convenienceService.create(hotelRequest));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.FOUND).body(convenienceService.findById(id));
  }

  @GetMapping("")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.status(HttpStatus.FOUND).body(convenienceService.findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody ConvenienceDTO convenienceRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(convenienceService.update(convenienceRequest));
  }

  @DeleteMapping("/remove/{id}")
  public ResponseEntity<?> removeById(@PathVariable Long id) {
    convenienceService.removeById(id);
    return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
  }

}
