package by.vladislav.hotelreservation.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import by.vladislav.hotelreservation.entity.Booking;
import by.vladislav.hotelreservation.entity.Room;
import by.vladislav.hotelreservation.entity.dto.BookingDTO;
import by.vladislav.hotelreservation.entity.dto.RoomDTO;
import by.vladislav.hotelreservation.exception.EntityNotFoundException;
import by.vladislav.hotelreservation.mapper.BookingMapper;
import by.vladislav.hotelreservation.repository.BookingRepository;
import by.vladislav.hotelreservation.repository.RoomRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final BookingMapper bookingMapper;
  private final RoomRepository roomRepository;

  public BookingDTO create(BookingDTO dto) {

    RoomDTO roomDto = dto.room();
    Room room = roomRepository.findById(roomDto.id())
        .orElseThrow(() -> new EntityNotFoundException("Room", "id", roomDto.id()));

    Booking entity = bookingMapper.toEntity(dto);

    BigDecimal totalPrice = calculatePrice(
        dto.checkInDate(),
        dto.checkOutDate(),
        dto.room().pricePerNight());

    entity.setTotalPrice(totalPrice);
    entity.setRoom(room);

    entity = bookingRepository.save(entity);
    return bookingMapper.toDTO(entity);

  }

  public BookingDTO findById(long id) {
    Booking entity = bookingRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Booking", "id", id));
    return bookingMapper.toDTO(entity);
  }

  public List<BookingDTO> findAll() {
    List<Booking> entityList = bookingRepository.findAll();

    return entityList.stream()
        .map(entity -> bookingMapper.toDTO(entity))
        .toList();
  }

  public BookingDTO update(BookingDTO dto) {
    Booking entity = bookingRepository.findById(dto.id())
        .orElseThrow(
            () -> new EntityNotFoundException("Booking", "id", dto.id()));

    entity.setGuestName(dto.guestName());
    entity.setCheckInDate(dto.checkInDate());
    entity.setCheckOutDate(dto.checkOutDate());

    BigDecimal newPrice = calculatePrice(
        dto.checkInDate(),
        dto.checkOutDate(),
        dto.room().pricePerNight());

    entity.setTotalPrice(newPrice);

    Booking updatedEntity = bookingRepository.save(entity);

    return bookingMapper.toDTO(updatedEntity);
  }

  public void removeById(long id) {
    bookingRepository.deleteById(id);
  }

  private BigDecimal calculatePrice(LocalDate start, LocalDate end, BigDecimal pricePerNight) {
    if (start == null || end == null || !end.isAfter(start)) {
      throw new IllegalArgumentException("Invalid dates: check-out must be after check-in");
    }
    long nights = ChronoUnit.DAYS.between(start, end);
    return pricePerNight.multiply(BigDecimal.valueOf(nights));
  }
}
