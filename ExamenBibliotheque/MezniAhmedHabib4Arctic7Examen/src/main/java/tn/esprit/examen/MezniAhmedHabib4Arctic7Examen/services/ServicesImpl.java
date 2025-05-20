package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Action;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Bibliotheque;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.Livre;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.TypeAction;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.ActionRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.BibliothequeRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.LivreRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServicesImpl implements IServices {
    private final ActionRepository actionRepository;
    private final BibliothequeRepository bibliothequeRepository;
    private final LivreRepository livreRepository;
    @Override
    public Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }

    @Override
    @Transactional
    public Livre ajouterLivreEtAffecterABibliotheque(Livre livre, String libelle) {
        Bibliotheque b = bibliothequeRepository.findBibliothequesByLibelle(libelle);
        livre.setBibliotheque(b);
        livre.setDisponible(true);
        return livreRepository.save(livre);
    }

    @Override
    @Transactional
    public String ajouterActionEtAffecterALivre(Action action, String isbn) {
        Livre livre = livreRepository.findLivreByIsbn(isbn);
        if (action.getType() == TypeAction.EMPRUNT && !livre.getDisponible()){
            return "Le livre est déjà emprunté";
        }
        if (action.getType() == TypeAction.RETOUR && livre.getDisponible()) {
            return "Le livre n'est pas emprunté";
        }
        if(action.getType() == TypeAction.EMPRUNT){
            livre.setDisponible(false);
            actionRepository.save(action);
            livre.getActions().add(action);
            livreRepository.save(livre);
            return " EMPRUNT de Livre A approuvé avec succès.";
        }
        if(action.getType() == TypeAction.RETOUR){
            action.setDate(LocalDate.now());
            actionRepository.save(action);
            livre.getActions().add(action);
            livre.setDisponible(true);
            livreRepository.save(livre);
            return " RETOUR de Livre A approuvé avec succès.";
        }
        return "Function Done";
    }

    @Scheduled(cron = "*/30 * * * * *")
    @Override
    public void listerActionParDate() {
        List<Action>action = actionRepository.findByDateIn(Collections.singleton(LocalDate.now()));
        for (Action a: action){
            log.info("\n Les actions d'aujourd'hui sont :\n action de type " +a.getType()+ " planifiée le " +a.getDate());
        }
    }

    @Override
    public List<String> listerLivresTitresParBibliothequesIds(List<Long> idsBibliotheque) {
        return livreRepository.findTitresByBibliothequeIdsWithLastActionEmprunt(idsBibliotheque);
    }

    @Override
    public List<Action> listerActionsParTypeEtLivreTitreEtLivreAuteur(TypeAction type, String titre, String auteur) {
        return actionRepository.listerActionsParTypeEtLivreTitreEtLivreAuteur(type, titre, auteur);
    }

//   @Scheduled(cron = "*/15 * * * * *")
//    @Override
//    public void test() {
//        log.info("testing");
//    }
}
