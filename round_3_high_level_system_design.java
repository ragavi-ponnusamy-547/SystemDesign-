import java.util.*;
import java.util.concurrent.*;

public class InstagramSystem {

    // Database simulation
    static Map<Integer, String> database =
            new ConcurrentHashMap<>();

    // Cache for frequently accessed posts
    static Map<Integer, String> cache =
            new ConcurrentHashMap<>();

    // Message queue for asynchronous processing
    static BlockingQueue<String> queue =
            new LinkedBlockingQueue<>();

    // Media storage simulation
    static Map<Integer, String> mediaStorage =
            new ConcurrentHashMap<>();

    // Media Upload Service
    static void uploadMedia(int id, String fileName) {
        mediaStorage.put(id, fileName);
        queue.offer("Process media " + id);
        System.out.println("Media uploaded: " + fileName);
    }

    // Media Processing Service
    static void processMedia() {
        String task = queue.poll();
        if (task != null) {
            System.out.println("Processing: " + task);
        }
    }

    // Post Service
    static void publishPost(int id, String caption) {
        database.put(id, caption);
        cache.put(id, caption);
        queue.offer("Update feed for post " + id);
        System.out.println("Post published");
    }

    // Feed Service with Cache
    static String getFeed(int id) {
        if (cache.containsKey(id)) {
            return "Cached post: " + cache.get(id);
        }
        return "Database post: "
                + database.getOrDefault(id, "Not found");
    }

    // Notification Service
    static void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }

    public static void main(String[] args) {

        uploadMedia(1, "photo.jpg");
        processMedia();

        publishPost(1, "My first photo");

        System.out.println(getFeed(1));

        sendNotification("Your post is published");
    }
}
