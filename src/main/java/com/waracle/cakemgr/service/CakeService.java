package com.waracle.cakemgr.service;

import com.waracle.cakemgr.controller.CakeController;
import com.waracle.cakemgr.data.repo.CakeRepository;
import com.waracle.cakemgr.entity.Cake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeService {

    Logger logger = LoggerFactory.getLogger(CakeController.class);

    @Autowired
    CakeRepository repo;
    public Iterable<Cake> getCakes() {
        logger.info("get list of cakes");
        return repo.findAll();
    }

    public Cake addCake(Cake cake) {
        logger.info("adding cake");
        return repo.save(cake);
    }

    public Cake updateCake(Cake cake, Long id) {
        logger.info("updating cake");
        return repo.findById(id)
                .map(c -> {
                    c.setTitle(cake.getTitle());
                    c.setDescription(cake.getDescription());
                    c.setImage(cake.getImage());
                    return repo.save(c);
                })
                .orElseGet(() -> repo.save(cake));
    }
    public void removeCake(Long id) {
        logger.info("removing cake");
        repo.deleteById(id);
    }


}
