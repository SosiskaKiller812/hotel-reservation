package by.vladislav.hotelreservation.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Address;
import by.vladislav.hotelreservation.entity.Convenience;
import by.vladislav.hotelreservation.entity.Hotel;
import by.vladislav.hotelreservation.entity.DTO.AddressDTO;
import by.vladislav.hotelreservation.entity.DTO.HotelDTO;
import by.vladislav.hotelreservation.entity.DTO.RoomDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HotelMapper {

  private final RoomMapper roomMapper;

  public Hotel toEntity(HotelDTO dto) {
    Address address = Address.builder()
        .country(dto.address().country())
        .city(dto.address().city())
        .street(dto.address().street())
        .build();

    return Hotel.builder()
        .name(dto.name())
        .address(address)
        .rating(dto.rating())
        .build();
  }

  public HotelDTO toDTO(Hotel hotel) {

    AddressDTO addressDTO = new AddressDTO(
        hotel.getAddress().getId(),
        hotel.getAddress().getCountry(),
        hotel.getAddress().getCity(),
        hotel.getAddress().getStreet());

    Set<String> conveniencesStrings = hotel.getConveniences().stream()
        .map(Convenience::getName)
        .collect(Collectors.toSet());

    List<RoomDTO> roomsDTO = hotel.getRooms().stream()
        .map(room -> roomMapper.toDTO(room))
        .toList();

    HotelDTO hotelDTO = new HotelDTO(
        hotel.getId(),
        hotel.getName(),
        addressDTO,
        hotel.getRating(),
        roomsDTO,
        conveniencesStrings);

    return hotelDTO;
  }
}
