package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Airline implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAirline;
    @Column(unique = true)
    private String codeIATA;
    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="airline")
    private Set<Flight> Flights;
}