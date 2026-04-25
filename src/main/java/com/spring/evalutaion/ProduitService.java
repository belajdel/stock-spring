package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    public void createProduit(Produit produit) {
        produitRepository.save(produit);
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
