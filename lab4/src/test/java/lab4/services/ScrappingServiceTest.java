package lab4.services;

import org.junit.jupiter.api.Test;
import lab4.models.ScrappingItem;
import lab4.seeds.urls;

import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.*;

public class ScrappingServiceTest {

    @Test
    public void testScrappingServiceSuccess() throws Exception {
        String testUrl = urls.urls[0];
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertEquals("SUCCESS", result.getStatus());
        assertEquals(testUrl, result.getUrl());
        assertNotNull(result.getTitle());
        assertTrue(result.getDataSize() > 0);
        assertTrue(result.getImageCount() > 0);
        assertTrue(result.getLinkCount() > 0);
    }

    @Test
    public void testScrappingServiceFailure() throws Exception {
        String testUrl = "http://invalid-url";
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertEquals("Failed", result.getStatus());
        assertEquals(testUrl, result.getUrl());
        assertEquals("", result.getTitle());
        assertEquals(0, result.getDataSize());
        assertEquals(0, result.getImageCount());
        assertEquals(0, result.getLinkCount());
    }

    @Test
    public void testScrappingServiceTimeout() throws Exception {
        String testUrl = urls.urls[1];
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertNotEquals("Timeout", result.getStatus());
    }

    @Test
    public void testScrappingServiceWithNoMetaTags() throws Exception {
        String testUrl = urls.urls[2];
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertEquals("SUCCESS", result.getStatus());
        assertEquals(testUrl, result.getUrl());
        assertNotNull(result.getTitle());
        assertEquals(31, result.getLinkCount());
        assertEquals(3, result.getImageCount());
    }

    @Test
    public void testScrappingServiceWithLargePage() throws Exception {
        String testUrl = urls.urls[3];
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertEquals("SUCCESS", result.getStatus());
        assertEquals(testUrl, result.getUrl());
        assertTrue(result.getDataSize() > 10000);
    }

    @Test
    public void testScrappingServiceWithImages() throws Exception {
        String testUrl = urls.urls[4];
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertEquals("SUCCESS", result.getStatus());
        assertEquals(testUrl, result.getUrl());
        assertTrue(result.getImageCount() > 0);
    }

    @Test
    public void testScrappingServiceWithLinks() throws Exception {
        String testUrl = urls.urls[5];
        ScrappingService service = new ScrappingService(testUrl);

        FutureTask<ScrappingItem> task = new FutureTask<>(service);
        new Thread(task).start();
        ScrappingItem result = task.get();

        assertEquals("SUCCESS", result.getStatus());
        assertEquals(testUrl, result.getUrl());
        assertTrue(result.getLinkCount() > 0);
    }
}