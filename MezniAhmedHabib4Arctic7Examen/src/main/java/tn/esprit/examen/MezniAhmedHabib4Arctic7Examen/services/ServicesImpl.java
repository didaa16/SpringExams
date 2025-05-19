package tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.entities.*;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.IClientRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.ICuisinierRepository;
import tn.esprit.examen.MezniAhmedHabib4Arctic7Examen.repositories.IPlatRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ServicesImpl implements IServices {

    private final IClientRepository clientRepository;
    private final ICuisinierRepository cuisinierRepository;
    private final IPlatRepository platRepository;

    @Autowired
    public ServicesImpl(IClientRepository clientRepository, ICuisinierRepository cuisinierRepository, IPlatRepository platRepository) {
        this.clientRepository = clientRepository;
        this.cuisinierRepository = cuisinierRepository;
        this.platRepository = platRepository;
    }




    @Override
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void ajouterCuisinier(Cuisinier cuisinier) {
        cuisinierRepository.save(cuisinier);
    }

    @Override
    @Transactional
    public void ajouterPlatAffecterClientetCuisinier(Plat plat, Integer idClient, Integer idCuisinier) {
        Client client = clientRepository.findById(idClient).orElse(null);
        Cuisinier cuisinier = cuisinierRepository.findById(idCuisinier).orElse(null);
//        client.getPlats().add(plat);
//        cuisinier.getPlats().add(plat);
        plat.setClient(client);
        plat.getCuisiniers().add(cuisinier);
        platRepository.save(plat);
    }

    @Override
    public List<Plat> AfficherListePlatsParClient(String nom, String prenom) {
        Client client = clientRepository.findClientByNomAndPrenom(nom, prenom);
        Set<Plat> plats = client.getPlats();
        return new ArrayList<>(plats);
    }

    @Override
    public float MontantAPayerParClient(Integer idClient) {
        float total = 0;
        Client client = clientRepository.findById(idClient).orElse(null);
        Set<Plat> plats = client.getPlats();
        for (Plat p : plats)
            total += p.getPrix();

        return total;
    }

    @Override
    public void ModifierImc(Integer idClient) {
        float totalCalories = 0;
        Client client = clientRepository.findById(idClient).orElse(null);
        Set<Plat> plats = client.getPlats();
        for (Plat p : plats)
            totalCalories += p.getCalorie();

        if (totalCalories < 2000)
            client.setImc(Imc.FAIBLE);
        else if (totalCalories == 2000)
            client.setImc(Imc.IDEAL);
        else if (totalCalories > 2000)
            client.setImc(Imc.FORT);

        clientRepository.save(client);

    }

    @Scheduled(cron = "*/15 * * * * *")
    @Override
    public void AfficherLIsteCuisiniers() {
        List<Cuisinier>listCuisiniers = cuisinierRepository.findAll();
        for (Cuisinier c : listCuisiniers){
            Set<Plat>plats = c.getPlats();
            int nbrPlats = 0;
            for (Plat p : plats){
                if (p.getCategorie() == Categorie.PRINCIPAL)
                    nbrPlats += 1;
            }
            if (nbrPlats >= 2)
                log.info(c.getNom());
        }
    }

//   @Scheduled(cron = "*/15 * * * * *")
//    @Override
//    public void test() {
//        log.info("testing");
//    }
}
