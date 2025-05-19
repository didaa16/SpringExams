package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Plat implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idPlat;

    String label;
    Float prix;
    Float calorie;
    @Enumerated(EnumType.STRING)
    Categorie categorie;

    @ManyToOne
    Client client;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cuisinier> cuisiniers;
}
