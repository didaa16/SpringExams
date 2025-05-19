package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personID;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer nbMounthsTraining;
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="person")
    private Set<Course> Courses;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="persons", cascade = CascadeType.ALL)
    private Set<Course> courses;

}