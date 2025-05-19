package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Client;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Cuisinier;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Plat;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services.IServices;

import java.util.List;

@RequestMapping("examen")
@RestController
public class ClientRestController {
    private final IServices services;

    @Autowired
    public ClientRestController(IServices services) {
        this.services = services;
    }

    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client c){
        return  services.ajouterClient(c);
    }

    @PostMapping("/addCuisinier")
    public void addCuisinier(@RequestBody Cuisinier c){
        services.ajouterCuisinier(c);
    }

    @PostMapping("/addPlat/{idClient}/{idCuisinier}")
    public void addPlat(@RequestBody Plat plat, @PathVariable("idClient") Integer idClient, @PathVariable("idCuisinier") Integer idCuisinier){
        services.ajouterPlatAffecterClientetCuisinier(plat, idClient, idCuisinier);
    }


    @GetMapping("/getPlatByClient/{nom}/{prenom}")
    public List<Plat> addPlat(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom){
        return services.AfficherListePlatsParClient(nom, prenom);
    }

    @GetMapping("/montantAPayer/{idClient}")
    public float montantAPayer(@PathVariable("idClient") Integer idClient){
        return services.MontantAPayerParClient(idClient);
    }

    @PutMapping("/modifierImc/{idClient}")
    public void modifierImc(@PathVariable("idClient") Integer idClient){
        services.ModifierImc(idClient);
    }





}
