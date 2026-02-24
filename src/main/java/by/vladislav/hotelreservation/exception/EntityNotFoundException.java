package by.vladislav.hotelreservation.exception;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(long id) {
    super("Entity with id: " + id + " not found");
  }

  public EntityNotFoundException(String name) {
    super("Entity with name: " + name + " not found");
  }

  public EntityNotFoundException(String entityName, String fieldName, Object fieldValue) {
    super(String.format("%s with %s : %s not found"));
  }
}
