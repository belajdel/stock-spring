package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduit(Integer id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Integer id, Produit produitDetails) {
        Produit produit = produitRepository.findById(id).orElse(null);
        if (produit != null) {
            produit.setNom(produitDetails.getNom());
            produit.setPrix(produitDetails.getPrix());
            produit.setQuantite(produitDetails.getQuantite());
            return produitRepository.save(produit);
        }
        return null;
    }

    public void deleteProduit(Integer id) {
        produitRepository.deleteById(id);
    }

    public Integer getValeurStock() {
        return produitRepository.getValeurStock();
    }

    public List<Produit> getProduitsAlerte(int seuil) {
        List<Produit> tous = produitRepository.findAll();
        List<Produit> alertes = new ArrayList<>();
        for (Produit produit : tous) {
            if (produit.getQuantite() != null && produit.getQuantite() < seuil) {
                alertes.add(produit);
            }
        }
        return alertes;
    }
}
