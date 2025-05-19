package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Course;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Exercice;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Level;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Person;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services.IServices;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ClientRestController {
    private final IServices services;

    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person p){
        return  services.addPerson(p);
    }

    @PostMapping("/addCourse/{idPerson}")
    public Course addCourse(@RequestBody Course c, @PathVariable("idPerson") Long idPerson){
        return  services.addCourseAssignCoach(c, idPerson);
    }

    @GetMapping("/CoursesList")
    public List<Course> courses(){
        return services.retrieveCourse(Level.ADVANCED, "Yasmine");
    }

    @PostMapping("/assignPersonToCourse/{idPerson}/{numCourse}")
    public Course assignAthleteToCours(@PathVariable("idPerson") Long idPerson, @PathVariable("numCourse") Integer numCourse){
        return  services.assignAthleteCourse(idPerson, numCourse);
    }

    @GetMapping("/participant")
    public List<Person> participant(){
        return services.retrieveAthlete("Abdos");
    }

    @GetMapping("/rigide")
    public List<Exercice> rigide(){
        return services.retrieveRigidEx(LocalDate.of(2023, 1, 1), LocalDate.of(2026, 12, 31));
    }

}
