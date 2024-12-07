package lab4.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import lab4.models.ScrappingItem;
import lab4.utils.Logger;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

public class ScrappingService implements Callable<ScrappingItem> {
    private final String url;

    public ScrappingService(String url) {
        this.url = url;
    }

    @Override
    public ScrappingItem call() {
        long startTime = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();

        try {
            Document document = Jsoup.connect(url)
                    .timeout(5000)
                    .get();

            String title = document.title();
            String description = document.select("meta[name=description]").attr("content");
            String bodyText = document.body().text();
            int countImages = document.select("img").size();
            int countLinks = document.select("a").size();
            long duration = System.currentTimeMillis() - startTime;

            return new ScrappingItem(threadName, url, duration, bodyText.length(), "SUCCESS", title, description, countLinks, countImages);
        } catch (Exception e) {
            Logger.log(MessageFormat.format("Error on URL {0}: {1}", url, e.getMessage()));
            long duration = System.currentTimeMillis() - startTime;
            String status = e instanceof java.net.SocketTimeoutException ? "Timeout" : "Failed";
            return new ScrappingItem(threadName, url, duration, 0, status, "", "", 0, 0);
        }
    }
}