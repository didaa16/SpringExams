package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Client;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Cuisinier;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Plat;

import java.util.List;

public interface IServices {
    Client ajouterClient(Client client);
    void ajouterCuisinier(Cuisinier cuisinier);
    void ajouterPlatAffecterClientetCuisinier(Plat plat, Integer idClient, Integer idCuisinier);
    List<Plat> AfficherListePlatsParClient(String nom, String prenom);
    float MontantAPayerParClient(Integer idClient);
    void ModifierImc(Integer idClient);
    void AfficherLIsteCuisiniers();
    //void test();
}
