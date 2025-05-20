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
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;
    private String titre;
    private String auteur;
    private String isbn;
    private Boolean disponible;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Action> Actions;
    @ManyToOne
    Bibliotheque bibliotheque;
}