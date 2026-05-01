package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LigneFactureService {

    @Autowired
    private LigneFactureRepository ligneFactureRepository;

    public List<LigneFacture> getAllLigneFactures() {
        return ligneFactureRepository.findAll();
    }

    public LigneFacture getLigneFacture(Integer id) {
        return ligneFactureRepository.findById(id).orElse(null);
    }

    public LigneFacture createLigneFacture(LigneFacture ligneFacture) {
        return ligneFactureRepository.save(ligneFacture);
    }

    public LigneFacture updateLigneFacture(Integer id, LigneFacture ligneDetails) {
        LigneFacture ligne = ligneFactureRepository.findById(id).orElse(null);
        if (ligne != null) {
            ligne.setQuantite(ligneDetails.getQuantite());
            ligne.setProduit(ligneDetails.getProduit());
            ligne.setFacture(ligneDetails.getFacture());
            return ligneFactureRepository.save(ligne);
        }
        return null;
    }

    public void deleteLigneFacture(Integer id) {
        ligneFactureRepository.deleteById(id);
    }
}
