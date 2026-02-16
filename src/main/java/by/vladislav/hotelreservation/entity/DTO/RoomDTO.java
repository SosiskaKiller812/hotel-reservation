package by.vladislav.hotelreservation.entity.DTO;

import java.math.BigDecimal;

public record RoomDTO(
    int number,
    String type,
    BigDecimal pricePerNight) {
}