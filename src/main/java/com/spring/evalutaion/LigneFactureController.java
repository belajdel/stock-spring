package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lignes-facture")
public class LigneFactureController {

    @Autowired
    private LigneFactureService ligneFactureService;

    @GetMapping
    public ResponseEntity<List<LigneFacture>> getAllLignes() {
        return ResponseEntity.ok(ligneFactureService.getAllLigneFactures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneFacture> getLigne(@PathVariable Integer id) {
        LigneFacture ligne = ligneFactureService.getLigneFacture(id);
        return ligne != null ? ResponseEntity.ok(ligne) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LigneFacture> createLigne(@RequestBody LigneFacture ligne) {
        LigneFacture saved = ligneFactureService.createLigneFacture(ligne);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneFacture> updateLigne(@PathVariable Integer id, @RequestBody LigneFacture ligne) {
        LigneFacture updated = ligneFactureService.updateLigneFacture(id, ligne);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigne(@PathVariable Integer id) {
        ligneFactureService.deleteLigneFacture(id);
        return ResponseEntity.noContent().build();
    }
}
