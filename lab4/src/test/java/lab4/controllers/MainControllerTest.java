package lab4.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lab4.seeds.urls;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest {

    private MainController controller;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private List<String> urlsList;

    @BeforeEach
    public void setUp() {
        resultPanel = new JPanel();
        resultLabel = new JLabel();
        urlsList = List.of(urls.urls);
        controller = new MainController(urlsList, resultPanel, resultLabel);
    }

    @Test
    public void testStartScraping() {
        controller.startScraping();

        assertEquals(urlsList.size(), resultPanel.getComponentCount());
        assertTrue(resultLabel.getText().contains("Total scraping time"));
    }

    @Test
    public void testEmptyUrlList() {
        controller = new MainController(List.of(), resultPanel, resultLabel);
        controller.startScraping();

        assertEquals(0, resultPanel.getComponentCount());
        assertTrue(resultLabel.getText().contains("Total scraping time"));
    }

    @Test
    public void testInvalidUrl() {
        urlsList = List.of("http://invalid-url");
        controller = new MainController(urlsList, resultPanel, resultLabel);
        controller.startScraping();

        assertEquals(1, resultPanel.getComponentCount());
        assertTrue(resultLabel.getText().contains("Total scraping time"));
    }

    @Test
    public void testMultipleUrls() {
        urlsList = List.of("http://example.com", "https://www.google.com", "https://www.github.com");
        controller = new MainController(urlsList, resultPanel, resultLabel);
        controller.startScraping();

        assertEquals(urlsList.size(), resultPanel.getComponentCount());
        assertTrue(resultLabel.getText().contains("Total scraping time"));
    }

    @Test
    public void testScrapingWithTimeout() {
        urlsList = List.of("http://example.com");
        controller = new MainController(urlsList, resultPanel, resultLabel);
        controller.startScraping();

        assertEquals(1, resultPanel.getComponentCount());
        assertTrue(resultLabel.getText().contains("Total scraping time"));
    }
}