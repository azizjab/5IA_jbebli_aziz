package tn.esprit.devops_project.services;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // Correct import pour post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Correct import pour status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // Correct import pour jsonPath
import static org.mockito.ArgumentMatchers.any; // Correct import pour any

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.controllers.StockController;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;

import javax.transaction.Transactional;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Transactional

public class StockServiceImplMock {
    @Mock
    private IStockService stockService;

    @InjectMocks
    private StockController stockController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    public void testAddStock() throws Exception {
        // Création d'un stock fictif
        Stock stock = new Stock();
        stock.setTitle("Stock Stylo");

        // Simulation du comportement du service
        when(stockService.addStock(any(Stock.class))).thenReturn(stock);

        // Effectuer la requête POST simulée pour ajouter un stock
        mockMvc.perform(post("/stock")
                        .contentType("application/json")
                        .content("{ \"title\": \"Stock Stylo\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Stock ABC"));

        // Vérifier que le service addStock a été appelé une seule fois avec n'importe quel objet Stock
        verify(stockService, times(1)).addStock(any(Stock.class));
    }
}