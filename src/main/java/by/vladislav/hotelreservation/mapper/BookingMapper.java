package by.vladislav.hotelreservation.mapper;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Booking;
import by.vladislav.hotelreservation.entity.dto.BookingDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookingMapper {

  private final RoomMapper roomMapper;

  public Booking toEntity(BookingDTO dto) {

    return Booking.builder()
        .guestName(dto.guestName())
        .checkInDate(dto.checkInDate())
        .checkOutDate(dto.checkOutDate())
        .totalPrice(null)
        .room(null)
        .build();
  }

  public BookingDTO toDTO(Booking entity) {
    return new BookingDTO(
        entity.getId(),
        entity.getGuestName(),
        entity.getCheckInDate(),
        entity.getCheckOutDate(),
        roomMapper.toDTO(entity.getRoom()));
  }
}
