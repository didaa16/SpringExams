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
public class Cuisinier implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idCuisinier;

    String nom;
    String prenom;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="cuisiniers", cascade = CascadeType.ALL)
    private Set<Plat> plats;
}
