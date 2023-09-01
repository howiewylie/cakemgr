package com.waracle.cakemgr.service;

import com.waracle.cakemgr.data.repo.CakeRepository;
import com.waracle.cakemgr.entity.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeService {

    @Autowired
    CakeRepository repo;
    public Iterable<Cake> getCakes() {
        return repo.findAll();
    }

    public Cake addCake(Cake cake) {
        System.out.println("adding cake");
        return repo.save(cake);
    }

    public Cake updateCake(Cake cake, Long id) {
        System.out.println("updating cake");
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
        repo.deleteById(id);
    }


}
