package lab4.controllers;

import lab4.models.ScrappingItem;
import lab4.utils.Logger;
import lab4.services.ScrappingService;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class MainController {
    private final ExecutorService threadPool;
    private final CompletionService<ScrappingItem> completionService;
    private final BlockingQueue<String> urlQueue;
    private final JPanel resultPanel;
    private final JLabel statusLabel;
    private final AtomicLong totalDataSize = new AtomicLong(0);
    private final AtomicLong totalDuration = new AtomicLong(0);

    public MainController(List<String> urls, JPanel resultPanel, JLabel statusLabel) {
        this.threadPool = Executors.newCachedThreadPool();
        this.completionService = new ExecutorCompletionService<>(threadPool);
        this.urlQueue = new LinkedBlockingQueue<>(urls);
        this.resultPanel = resultPanel;
        this.statusLabel = statusLabel;
    }

    public void startScraping() {
        int totalUrls = urlQueue.size();

        while (!urlQueue.isEmpty()) {
            try {
                String url = urlQueue.take();
                completionService.submit(new ScrappingService(url));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int remainingTasks = totalUrls;
        long startTime = System.currentTimeMillis();

        while (remainingTasks > 0) {
            try {
                Future<ScrappingItem> future = completionService.take();
                ScrappingItem result = future.get();

                totalDataSize.addAndGet(result.getDataSize());
                totalDuration.addAndGet(result.getDuration());

                String logMessage = STR."URL: \{result.getUrl()}, Status: \{result.getStatus()}, Duration: \{result.getDuration()} ms, Data Size: \{result.getDataSize()} characters, Title: \{result.getTitle()}, Description: \{result.getDescription()}, Link Count: \{result.getLinkCount()}, Image Count: \{result.getImageCount()}";
                Logger.log(logMessage);

                JPanel taskPanel = new JPanel();
                taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
                taskPanel.setBorder(BorderFactory.createTitledBorder(STR."Thread: \{result.getThreadName()}"));
                taskPanel.add(new JLabel(STR."URL: \{result.getUrl()}"));
                taskPanel.add(new JLabel(STR."Status: \{result.getStatus()}"));
                taskPanel.add(new JLabel(STR."Duration: \{result.getDuration()} ms"));
                taskPanel.add(new JLabel(STR."Data Size: \{result.getDataSize()} characters"));
                taskPanel.add(new JLabel(STR."Title: \{result.getTitle()}"));
                taskPanel.add(new JLabel(STR."Description: \{result.getDescription()}"));
                taskPanel.add(new JLabel(STR."Link Count: \{result.getLinkCount()}"));
                taskPanel.add(new JLabel(STR."Image Count: \{result.getImageCount()}"));

                resultPanel.add(taskPanel);
                resultPanel.revalidate();
                resultPanel.repaint();

                remainingTasks--;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long totalElapsedTime = endTime - startTime;
        String summary = STR."Total scraping time: \{totalElapsedTime} ms, Total data size: \{totalDataSize.get()} characters";
        Logger.log(summary);

        statusLabel.setText(summary);

        threadPool.shutdown();
    }
}