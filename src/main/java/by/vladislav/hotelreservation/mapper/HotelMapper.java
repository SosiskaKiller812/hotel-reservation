package by.vladislav.hotelreservation.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Address;
import by.vladislav.hotelreservation.entity.Hotel;
import by.vladislav.hotelreservation.entity.dto.AddressDTO;
import by.vladislav.hotelreservation.entity.dto.ConvenienceDTO;
import by.vladislav.hotelreservation.entity.dto.HotelDTO;
import by.vladislav.hotelreservation.entity.dto.RoomDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HotelMapper {

  private final RoomMapper roomMapper;
  private final ConvenienceMapper convenienceMapper;

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

    Set<ConvenienceDTO> conveniencesDTOs = hotel.getConveniences().stream()
        .map(convenienceMapper::toDTO)
        .collect(Collectors.toSet());

    List<RoomDTO> roomsDTO = hotel.getRooms().stream()
        .map(room -> roomMapper.toDTO(room))
        .toList();

    return new HotelDTO(
        hotel.getId(),
        hotel.getName(),
        addressDTO,
        hotel.getRating(),
        roomsDTO,
        conveniencesDTOs);
  }
}
