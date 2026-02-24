package by.vladislav.hotelreservation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import by.vladislav.hotelreservation.entity.Convenience;
import by.vladislav.hotelreservation.entity.Hotel;
import by.vladislav.hotelreservation.entity.Room;
import by.vladislav.hotelreservation.entity.DTO.HotelDTO;
import by.vladislav.hotelreservation.repository.ConvenienceRepository;
import by.vladislav.hotelreservation.repository.HotelRepository;
import by.vladislav.hotelreservation.utils.HotelMapper;
import by.vladislav.hotelreservation.utils.RoomMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class HotelService {

  private final HotelRepository repository;
  private final HotelMapper hotelMapper;
  private final RoomMapper roomMapper;
  private final ConvenienceRepository convenienceRepository;

  public HotelDTO findByName(String name) {
    Hotel hotel = repository.findByName(name)
        .orElseThrow(() -> new EntityNotFoundException("Hotel with with name:" + name + " not found"));
    return hotelMapper.toDTO(hotel);
  }

  public HotelDTO findById(long id) {
    Hotel hotel = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Hotel with with id:" + id + " not found"));
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

  public void removeById(long id) {
    repository.deleteById(id);
  }

  @Transactional
  public HotelDTO create(HotelDTO dto) {
    Set<Convenience> conveniences = new HashSet<>(convenienceRepository.findByNameIn(dto.conveniences()));

    Hotel hotel = hotelMapper.toEntity(dto);
    hotel.setConveniences(conveniences);

    Hotel savedHotel = repository.save(hotel);

    if (dto.rooms() != null) {
      List<Room> rooms = dto.rooms().stream()
          .map(roomMapper::toEntity)
          .peek(room -> room.setHotel(savedHotel))
          .toList();

      savedHotel.setRooms(rooms);
    }

    return hotelMapper.toDTO(savedHotel);
  }

  public HotelDTO createWithoutTransactional(HotelDTO dto, boolean isTransactional) {
    Set<Convenience> conveniences = new HashSet<>(convenienceRepository.findByNameIn(dto.conveniences()));

    Hotel hotel = hotelMapper.toEntity(dto);
    hotel.setConveniences(conveniences);

    Hotel savedHotel = repository.save(hotel);

    if (!isTransactional) {
      throw new RuntimeException("Error");
    }

    if (dto.rooms() != null) {
      List<Room> rooms = dto.rooms().stream()
          .map(roomMapper::toEntity)
          .peek(room -> room.setHotel(savedHotel))
          .toList();

      savedHotel.setRooms(rooms);
    }

    return hotelMapper.toDTO(savedHotel);
  }

  @Transactional
  public Hotel update(HotelDTO dto) {

    Hotel hotel = repository.findById(dto.id())
        .orElseThrow(() -> new EntityNotFoundException("Hotel with name:" + dto.id() + " not found"));

    hotel.setName(dto.name());
    hotel.setRating(dto.rating());

    hotel.getAddress().setCountry(dto.address().country());
    hotel.getAddress().setCity(dto.address().city());
    hotel.getAddress().setStreet(dto.address().street());

    Set<Convenience> conveniences = new HashSet<>(convenienceRepository.findByNameIn(dto.conveniences()));
    hotel.setConveniences(conveniences);

    hotel.getRooms().clear();

    if (dto.rooms() != null) {
      List<Room> rooms = dto.rooms().stream()
          .map(roomDto -> {
            Room room = roomMapper.toEntity(roomDto);
            room.setHotel(hotel);
            return room;
          })
          .toList();

      hotel.getRooms().addAll(rooms);
    }

    return hotel;
  }

}
