package by.vladislav.hotelreservation.entity.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record HotelDTO(
    Long id,
    String name,
    AddressDTO address,
    BigDecimal rating,
    List<RoomDTO> rooms,
    Set<ConvenienceDTO> conveniences) {
}
