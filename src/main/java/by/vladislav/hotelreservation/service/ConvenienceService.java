package by.vladislav.hotelreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.vladislav.hotelreservation.entity.Convenience;
import by.vladislav.hotelreservation.entity.DTO.ConvenienceDTO;
import by.vladislav.hotelreservation.repository.ConvenienceRepository;
import by.vladislav.hotelreservation.utils.ConvenienceMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ConvenienceService {

  private final ConvenienceRepository convenienceRepository;
  private final ConvenienceMapper convenienceMapper;

  public ConvenienceDTO create(ConvenienceDTO convenienceDTO) {
    Convenience entity = convenienceMapper.toEntity(convenienceDTO);
    entity = convenienceRepository.save(entity);
    return convenienceMapper.toDTO(entity);
  }

  public ConvenienceDTO findById(long id) {
    Convenience entity = convenienceRepository.findById(id).orElseThrow();
    return convenienceMapper.toDTO(entity);
  }

  public List<ConvenienceDTO> findAll() {
    List<Convenience> entityList = convenienceRepository.findAll();

    return entityList.stream()
        .map(entity -> convenienceMapper.toDTO(entity))
        .toList();
  }

  public ConvenienceDTO update(ConvenienceDTO convenienceDTO) {
    Convenience convenienceEntity = convenienceRepository.findByName(convenienceDTO.name()).orElseThrow();

    convenienceEntity.setName(convenienceDTO.name());

    Convenience newConvenience = convenienceRepository.save(convenienceEntity);

    return convenienceMapper.toDTO(newConvenience);
  }

  public void removeById(long id) {
    convenienceRepository.deleteById(id);
  }
}
