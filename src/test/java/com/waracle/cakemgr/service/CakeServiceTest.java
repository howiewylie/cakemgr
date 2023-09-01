package com.waracle.cakemgr.service;

import com.waracle.cakemgr.data.repo.CakeRepository;
import com.waracle.cakemgr.entity.Cake;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CakeServiceTest {

    @Mock
    CakeRepository cakeRepo;

    @InjectMocks
    CakeService cakeService;

    @Test
    public void testGetCakes() {
        when(cakeRepo.findAll()).thenReturn(new ArrayList<>());
        Iterable<Cake> cakeList = cakeService.getCakes();
        assertNotNull(cakeList);
        verify(cakeRepo).findAll();
    }

    @Test
    public void testAddCake() {
        Cake cake = Cake.builder()
                .title("TestTitle")
                .description("TestDescription")
                .image("ImgUrl")
                .build();

        Cake newCake = Cake.builder()
                .cakeId(1L)
                .title(cake.getTitle())
                .description(cake.getDescription())
                .image(cake.getImage())
                .build();
        when(cakeRepo.save(cake)).thenReturn(newCake);
        cakeService.addCake(cake);
        verify(cakeRepo).save(cake);
    }

    @Test
    public void testUpdateCake() {
        Cake cake = Cake.builder()
                .cakeId(1L)
                .title("TestTitle")
                .description("TestDescription")
                .image("ImgUrl")
                .build();
        when(cakeRepo.save(cake)).thenReturn(cake);
        cakeService.updateCake(cake, cake.getCakeId());
        verify(cakeRepo).findById(cake.getCakeId());
        verify(cakeRepo).save(cake);
    }

    @Test
    public void testRemoveCake() {
        cakeService.removeCake(1L);
        verify(cakeRepo).deleteById(1L);
    }
}
