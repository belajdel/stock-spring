package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(int id) {
        return produitRepository.findById(id);
    }

    public void createProduit(Produit produit) {
        produitRepository.save(produit);
    }

    public void updateProduit(Produit produit) {
        produitRepository.update(produit);
    }

    public void deleteProduit(int id) {
        produitRepository.delete(id);
    }

    public String verifierStockCritique(int produitId, int seuilCritique) {
        Produit produit = produitRepository.findById(produitId);
        
        if (produit == null) {
            return "Produit non trouvé";
        }
        
        if (produit.getQuantite() != null && produit.getQuantite() < seuilCritique) {
            return "ALERTE : Stock critique pour le produit '" + produit.getNom() + 
                   "'. Quantité actuelle : " + produit.getQuantite() + 
                   " (Seuil critique : " + seuilCritique + ")";
        }
        
        return "Stock normal pour le produit '" + produit.getNom() + 
               "'. Quantité : " + produit.getQuantite();
    }

    public Integer getValeurStock() {
        return produitRepository.getValeurStock();
    }

    public List<Produit> getProduitsAlerte(int seuil) {
        List<Produit> tous = produitRepository.findAll();
        List<Produit> alertes = new java.util.ArrayList<>();
        for (Produit produit : tous) {
            if (produit.getQuantite() != null && produit.getQuantite() < seuil) {
                alertes.add(produit);
            }
        }
        return alertes;
    }
}
