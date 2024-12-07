package lab4.models;

public class ScrappingItem {
    private final String threadName;
    private final String url;
    private final long duration;
    private final int dataSize;
    private final String status;
    private final String title;
    private final String description;
    private final int linkCount;
    private final int imageCount;

    public ScrappingItem(String threadName, String url, long duration, int dataSize, String status, String title, String description, int linkCount, int imageCount) {
        this.threadName = threadName;
        this.url = url;
        this.duration = duration;
        this.dataSize = dataSize;
        this.status = status;
        this.title = title;
        this.description = description;
        this.linkCount = linkCount;
        this.imageCount = imageCount;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getUrl() {
        return url;
    }

    public long getDuration() {
        return duration;
    }

    public int getDataSize() {
        return dataSize;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLinkCount() {
        return linkCount;
    }

    public int getImageCount() {
        return imageCount;
    }

    @Override
    public String toString() {
        return STR."ScrappingResult{threadName='\{threadName}', url='\{url}', duration=\{duration}, dataSize=\{dataSize}, status='\{status}', title='\{title}', description='\{description}', linkCount=\{linkCount}, imageCount=\{imageCount}}";
    }
}
