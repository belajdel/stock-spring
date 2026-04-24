package com.spring.evalutaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ValeurController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/valeur")
    public ResponseEntity<Integer> getValeurStock() {
        Integer valeur = produitService.getValeurStock();
        return ResponseEntity.ok(valeur);
    }
}
