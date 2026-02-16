package by.vladislav.hotelreservation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import by.vladislav.hotelreservation.entity.Address;
import by.vladislav.hotelreservation.entity.Convenience;
import by.vladislav.hotelreservation.entity.Hotel;
import by.vladislav.hotelreservation.entity.Room;
import by.vladislav.hotelreservation.entity.DTO.HotelDTO;
import by.vladislav.hotelreservation.entity.DTO.RoomDTO;
import by.vladislav.hotelreservation.repository.ConvenienceRepository;
import by.vladislav.hotelreservation.repository.HotelRepository;
import by.vladislav.hotelreservation.utils.HotelMapper;
import by.vladislav.hotelreservation.utils.RoomMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class HotelService {

  private final HotelRepository repository;
  private final HotelMapper hotelMapper;
  private final RoomMapper roomMapper;
  private final ConvenienceRepository convinienceRepository;

  public HotelDTO findByName(String name) {
    Hotel hotel = repository.findByName(name).orElseThrow(null);
    return hotelMapper.toDTO(hotel);
  }

  public HotelDTO findById(long id) {
    Hotel hotel = repository.findById(id).orElseThrow(null);
    return hotelMapper.toDTO(hotel);
  }

  public List<HotelDTO> findAll() {
    List<Hotel> hotels = repository.findAll();
    List<HotelDTO> hotelsDTO = new ArrayList<>(hotels.size());
    for (Hotel hotel : hotels) {
      hotelsDTO.add(hotelMapper.toDTO(hotel));
    }
    return hotelsDTO;
  }

  @Transactional
  public HotelDTO create(HotelDTO hotelRequest) {
    Hotel hotelEntity = createEntityFromDTO(hotelRequest);

    Hotel newHotel = repository.save(hotelEntity);

    return hotelMapper.toDTO(newHotel);
  }

  public HotelDTO createWithoutTransactional(HotelDTO hotelRequest) {
    Hotel hotelEntity = createEntityFromDTO(hotelRequest);

    Hotel newHotel = repository.save(hotelEntity);

    return hotelMapper.toDTO(newHotel);
  }

  @Transactional
  public HotelDTO update(HotelDTO hotelRequest) {
    Hotel hotel = repository.findByName(hotelRequest.name()).orElseThrow();

    changeDifference(hotel, hotelRequest);

    return hotelMapper.toDTO(hotel);
  }

  public void removeById(long id) {
    repository.deleteById(id);
  }

  private void changeDifference(Hotel entity, HotelDTO dto) {
    Hotel newEnityStats = createEntityFromDTO(dto);

    if (newEnityStats.getName() != null) {
      entity.setName(newEnityStats.getName());
    }

    if (newEnityStats.getAddress() != null) {
      entity.setAddress(newEnityStats.getAddress());
    }

    if (newEnityStats.getRating() != null) {
      entity.setRating(newEnityStats.getRating());
    }

    if (newEnityStats.getRooms() != null) {
      entity.getRooms().clear();
      for (RoomDTO roomDTO : dto.rooms()) {
        Room room = roomMapper.toEntity(roomDTO);
        room.setHotel(entity);
        entity.getRooms().add(room);
      }
    }

    if (newEnityStats.getConveniences() != null) {
      entity.setConveniences(newEnityStats.getConveniences());
    }
  }

  private Hotel createEntityFromDTO(HotelDTO hotelDTO) {
    Set<Convenience> conviniencesSet = new HashSet<>();
    for (String convinienceName : hotelDTO.conveniences()) {
      Convenience newConvinience = convinienceRepository.findByName(convinienceName).orElseThrow();
      if (newConvinience != null) {
        conviniencesSet.add(newConvinience);
      }
    }

    Address newAddress = Address.builder()
        .country(hotelDTO.address().country())
        .city(hotelDTO.address().city())
        .street(hotelDTO.address().street())
        .build();

    List<Room> roomsList = new ArrayList<>(hotelDTO.rooms().size());

    for (RoomDTO roomRequest : hotelDTO.rooms()) {
      Room newRoom = roomMapper.toEntity(roomRequest);
      roomsList.add(newRoom);
    }

    return hotelMapper.toEntity(hotelDTO, conviniencesSet, newAddress, roomsList);
  }
}
