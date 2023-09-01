package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CakeControllerTest {

    @Mock
    private CakeService cakeService;

    @InjectMocks
    private CakeController cakeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(cakeController).build();

    }

    @Test
    public void testGetCakesSuccess() throws Exception {
        List<Cake> cakeList = new ArrayList<>();
        Cake cake1 = Cake.builder()
                .cakeId(1L)
                .title("Cake1")
                .description("Nice cake")
                .image("cake1ImgUrl")
                .build();
        Cake cake2 = Cake.builder()
                .cakeId(2L)
                .title("Cake2")
                .description("Nice cake too")
                .image("cake2ImgUrl")
                .build();
        cakeList.add(cake1);
        cakeList.add(cake2);

        when(cakeService.getCakes()).thenReturn(cakeList);
        mockMvc.perform(get("/cakes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void testAddCakesSuccess() throws Exception {

        Cake cake = Cake.builder()
                .title("Cake2")
                .description("Nice cake too")
                .image("cake2ImgUrl")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cake );

        Cake newCake = Cake.builder()
                .cakeId(1L)
                .title(cake.getTitle())
                .description(cake.getDescription())
                .image(cake.getImage())
                .build();
        when(cakeService.addCake(cake)).thenReturn(newCake);
        mockMvc.perform(post("/cakes").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testEditCakesSuccess() throws Exception {

        Cake cake = Cake.builder()
                .cakeId(1L)
                .title("Cake2")
                .description("Nice cake too")
                .image("cake2ImgUrl")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cake );

        Cake newCake = Cake.builder()
                .cakeId(cake.getCakeId())
                .title(cake.getTitle())
                .description(cake.getDescription())
                .image(cake.getImage())
                .build();
        when(cakeService.updateCake(cake,cake.getCakeId())).thenReturn(newCake);
        mockMvc.perform(put("/cakes/1").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testDeleteCakeSuccess() throws Exception {

        mockMvc.perform(delete("/cakes/1"))
                .andExpect(status().is2xxSuccessful());
        verify(cakeService).removeCake(1L);

    }

}
