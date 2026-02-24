package by.vladislav.hotelreservation.entity.dto;

import java.time.LocalDate;

public record BookingDTO(
    Long id,
    String guestName,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    RoomDTO room) {
}
