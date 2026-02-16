package by.vladislav.hotelreservation.utils;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Room;
import by.vladislav.hotelreservation.entity.DTO.RoomDTO;

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
    return new RoomDTO(roomEntity.getNumber(), roomEntity.getType(), roomEntity.getPricePerNight());
  }

}