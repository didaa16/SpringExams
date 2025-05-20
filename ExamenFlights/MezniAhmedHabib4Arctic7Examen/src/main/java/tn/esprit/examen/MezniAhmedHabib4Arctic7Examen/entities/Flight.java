package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFlight;
    private String number;
    private Date estimatedDeparture;
    private Date realDeparture;
    private float price;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="flight")
    private Set<Passenger> Passengers;
    @ManyToOne
    Airline airline;
}