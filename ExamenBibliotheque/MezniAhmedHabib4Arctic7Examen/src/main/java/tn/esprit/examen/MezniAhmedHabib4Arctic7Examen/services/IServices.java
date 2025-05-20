package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Action;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Bibliotheque;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Livre;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.TypeAction;

import java.util.List;

public interface IServices {
    Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque);
    Livre ajouterLivreEtAffecterABibliotheque(Livre livre, String libelle);
    String ajouterActionEtAffecterALivre(Action action, String isbn);
    void listerActionParDate();
    List<String> listerLivresTitresParBibliothequesIds(List<Long> idsBibliotheque);
    List<Action> listerActionsParTypeEtLivreTitreEtLivreAuteur (TypeAction type, String titre, String auteur);
    //void test();
}
