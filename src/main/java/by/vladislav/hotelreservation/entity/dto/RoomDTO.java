package by.vladislav.hotelreservation.entity.dto;

import java.math.BigDecimal;

public record RoomDTO(
    Long id,
    int number,
    String type,
    BigDecimal pricePerNight) {
}
