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
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idClient;
    String nom;
    String prenom;
    @Enumerated(EnumType.STRING)
    Imc imc;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="client")
    private Set<Plat> Plats;
}
