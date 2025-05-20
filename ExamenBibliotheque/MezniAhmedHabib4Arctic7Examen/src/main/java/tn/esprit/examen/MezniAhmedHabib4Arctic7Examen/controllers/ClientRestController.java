package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Action;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Bibliotheque;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Livre;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.TypeAction;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services.IServices;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ClientRestController {
    private final IServices services;

    @PostMapping("/addBiblio")
    public Bibliotheque addBiblio(@RequestBody Bibliotheque b){
        return  services.ajouterBibliotheque(b);
    }

    @PostMapping("/addLivre/{libelle}")
    public Livre addLivre(@RequestBody Livre l, @PathVariable("libelle") String libelle){
        return  services.ajouterLivreEtAffecterABibliotheque(l, libelle);
    }

    @PostMapping("/addAction/{isbn}")
    public String addAction(@RequestBody Action a, @PathVariable("isbn") String isbn){
        return  services.ajouterActionEtAffecterALivre(a, isbn);
    }
    @GetMapping("/actionsList/{type}/{titre}/{auteur}")
    public List<Action> actions(@PathVariable("type") TypeAction type, @PathVariable("titre") String titre, @PathVariable("auteur") String auteur){
        return services.listerActionsParTypeEtLivreTitreEtLivreAuteur(type, titre, auteur);
    }

    @GetMapping("/livreTitreList/{idBiblio}")
    public List<String> livreTitreList(@PathVariable("idBiblio") List<Long> idsBibliotheque){
        return services.listerLivresTitresParBibliothequesIds(idsBibliotheque);
    }

}
