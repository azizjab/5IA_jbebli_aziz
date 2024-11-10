package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Isolation;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional

class StockServiceImplTest {

    @Autowired
    private StockServiceImpl stockService;

    @Autowired
    private StockRepository stockRepository;

    // Nettoyage de la base de données avant chaque test
    @BeforeEach
    void setUp() {
        stockRepository.deleteAll();
    }

    @Test
    void testAddStock() {
        // Arrange: Créer un objet stock avec des données
        Stock stock = new Stock();
        stock.setTitle("Stock de Test");

        // Act: Ajouter le stock en appelant la méthode du service
        Stock savedStock = stockService.addStock(stock);

        // Assert: Vérifier que l'ID du stock a été généré et que le titre correspond
        assertNotNull(savedStock.getIdStock());
        assertEquals("Stock de Test", savedStock.getTitle());

        // Vérifier que le stock est bien présent dans la base de données
        Stock retrievedStock = stockRepository.findById(savedStock.getIdStock()).orElse(null);
        assertNotNull(retrievedStock, "Affichage stock");
        assertEquals("Stock de Test", retrievedStock.getTitle());
    }
}