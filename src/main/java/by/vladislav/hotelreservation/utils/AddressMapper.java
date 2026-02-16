package by.vladislav.hotelreservation.utils;

import org.springframework.stereotype.Component;

import by.vladislav.hotelreservation.entity.Address;
import by.vladislav.hotelreservation.entity.DTO.AddressDTO;

@Component
public class AddressMapper {
  public Address toEntity(AddressDTO dto) {
    return Address.builder()
        .country(dto.country())
        .city(dto.city())
        .street(dto.street())
        .build();
  }

  public AddressDTO toDTO(Address entity){
    return new AddressDTO(entity.getCountry(), entity.getCity(), entity.getStreet());
  }
}
