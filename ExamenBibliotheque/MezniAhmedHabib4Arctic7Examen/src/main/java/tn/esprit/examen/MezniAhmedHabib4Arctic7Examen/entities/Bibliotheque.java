package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Bibliotheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBibliotheque;
    private String libelle;
    private String adresse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="bibliotheque")
    private Set<Livre> Livres;
}