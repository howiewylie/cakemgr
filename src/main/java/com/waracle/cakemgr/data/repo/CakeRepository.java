package com.waracle.cakemgr.data.repo;

import com.waracle.cakemgr.entity.Cake;
import org.springframework.data.repository.CrudRepository;

public interface CakeRepository extends CrudRepository<Cake, Long> {
}
