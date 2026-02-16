package by.vladislav.hotelreservation.entity.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record HotelDTO(
    String name,
    AddressDTO address,
    BigDecimal rating,
    List<RoomDTO> rooms,
    Set<String> conveniences) {
}