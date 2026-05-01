package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Facture getFacture(Integer id) {
        return factureRepository.findById(id).orElse(null);
    }

    public Facture createFacture(Facture facture) {
        // Ensure the relationship is set correctly for both sides if needed
        if (facture.getLigneFactures() != null) {
            facture.getLigneFactures().forEach(line -> line.setFacture(facture));
        }
        return factureRepository.save(facture);
    }

    public Facture updateFacture(Integer id, Facture factureDetails) {
        Facture facture = factureRepository.findById(id).orElse(null);
        if (facture != null) {
            facture.setNumFacture(factureDetails.getNumFacture());
            facture.setDateFacture(factureDetails.getDateFacture());
            facture.setNumClient(factureDetails.getNumClient());
            // Update lines if needed (simplified for now)
            return factureRepository.save(facture);
        }
        return null;
    }

    public void deleteFacture(Integer id) {
        factureRepository.deleteById(id);
    }
}
