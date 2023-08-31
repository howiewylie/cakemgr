package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CakeController {

    @Autowired
    CakeService cakeService;

    @GetMapping("/cakes")
    Iterable<Cake> listAllCakes() {
        return cakeService.getCakes();
    }

    @PostMapping("/cakes")
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        try {
        Cake newCake = cakeService.addCake(cake);
        return new ResponseEntity<>(newCake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cakes")
    public ResponseEntity<Cake> updateCake(@RequestBody Cake cake, @PathVariable Long id) {
        try {
            Cake updatedCake = cakeService.updateCake(cake, id);
            return new ResponseEntity<>(updatedCake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cakes")
    public void deleteCake(@PathVariable Long id) {
        cakeService.removeCake(id);
    }
}
