package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.security.SecureRandomParameters;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Passenger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPassenger;
    private String lastName;
    private String firstName;
    private float compensationMiles;

    @ManyToOne
    Flight flight;

}