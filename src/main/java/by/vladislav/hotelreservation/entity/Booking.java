package by.vladislav.hotelreservation.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity(name = "booking")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Date outDate;

  private Date inDate;

  private float price;

  private String status;
}
