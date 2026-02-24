package by.vladislav.hotelreservation.mapper;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Room;
import by.vladislav.hotelreservation.entity.dto.RoomDTO;

@Component
public class RoomMapper {

  public Room toEntity(RoomDTO roomRequest) {
    return Room.builder()
        .number(roomRequest.number())
        .type(roomRequest.type())
        .pricePerNight(roomRequest.pricePerNight())
        .build();
  }

  public RoomDTO toDTO(Room roomEntity) {
    return new RoomDTO(
        roomEntity.getId(),
        roomEntity.getNumber(),
        roomEntity.getType(),
        roomEntity.getPricePerNight());
  }
}
