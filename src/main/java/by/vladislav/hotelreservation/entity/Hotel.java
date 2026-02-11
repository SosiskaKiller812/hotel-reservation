package by.vladislav.hotelreservation.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "hotels")
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToOne
  @JoinTable(name = "adress_id")
  private Adress adress;

  private float rating;
  
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private List<Room> rooms = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "conviniences", joinColumns = @JoinColumn(name = "hotel_id"))
  private Set<Convinience> conviniences = new HashSet<>();
}
