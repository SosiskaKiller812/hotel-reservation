package by.vladislav.hotelreservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "adress")
public class Adress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String street;

  private int streetNumber;
}
