package by.vladislav.hotelreservation.utils;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Convenience;
import by.vladislav.hotelreservation.entity.DTO.ConvenienceDTO;

@Component
public class ConvenienceMapper {
  public Convenience toEntity(ConvenienceDTO convenienceDTO) {
    return Convenience.builder()
        .name(convenienceDTO.name())
        .build();
  }

  public ConvenienceDTO toDTO(Convenience entity) {
    return new ConvenienceDTO(
        entity.getId(),
        entity.getName());
  }
}
