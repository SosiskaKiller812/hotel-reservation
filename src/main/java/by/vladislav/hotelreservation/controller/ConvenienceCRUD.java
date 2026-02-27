package by.vladislav.hotelreservation.controller;

import java.util.List;

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

import by.vladislav.hotelreservation.entity.dto.ConvenienceDTO;
import by.vladislav.hotelreservation.service.ConvenienceService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/conveniences")
public class ConvenienceCRUD {
  private final ConvenienceService convenienceService;

  @PostMapping
  public ResponseEntity<ConvenienceDTO> create(@RequestBody ConvenienceDTO hotelRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(convenienceService.create(hotelRequest));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ConvenienceDTO> findById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.FOUND).body(convenienceService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<ConvenienceDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.FOUND).body(convenienceService.findAll());
  }

  @PutMapping
  public ResponseEntity<ConvenienceDTO> update(@RequestBody ConvenienceDTO convenienceRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(convenienceService.update(convenienceRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeById(@PathVariable Long id) {
    convenienceService.removeById(id);
    return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
  }

}
