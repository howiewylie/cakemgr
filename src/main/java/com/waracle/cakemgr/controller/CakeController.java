package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CakeController {

    Logger logger = LoggerFactory.getLogger(CakeController.class);

    @Autowired
    CakeService cakeService;

    @GetMapping("/cakes")
    Iterable<Cake> listAllCakes() {
        logger.info("In listAllCakes");
        return cakeService.getCakes();
    }

    @PostMapping("/cakes")
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        logger.info("In createCake");
        try {
        Cake newCake = cakeService.addCake(cake);
        return new ResponseEntity<>(newCake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cakes/{id}")
    public ResponseEntity<Cake> updateCake(@RequestBody Cake cake, @PathVariable Long id) {
        logger.info("In updateCake");
        try {
            Cake updatedCake = cakeService.updateCake(cake, id);
            return new ResponseEntity<>(updatedCake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cakes/{id}")
    public void deleteCake(@PathVariable Long id) {
        logger.info("In deleteCake");
        cakeService.removeCake(id);
    }
}
