package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;


    @GetMapping
    public ResponseEntity<List<Produit>> listerProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable int id) {
        Produit produit = produitService.getProduitById(id);
        
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(produit);
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> createProduit(@RequestBody Produit produit) {
        produitService.createProduit(produit);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Produit créé avec succès");
        response.put("nom", produit.getNom());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/alerte/{seuil}")
    public ResponseEntity<List<Produit>> getProduitsAlerte(@PathVariable int seuil) {
        List<Produit> produits = produitService.getProduitsAlerte(seuil);
        return ResponseEntity.ok(produits);
    }

}
