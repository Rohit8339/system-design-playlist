/*
=========================================================
OBSERVER DESIGN PATTERN
=========================================================

REAL WORLD EXAMPLE:
YouTube Notifications

Problem:
When a YouTube channel uploads a new video,
all subscribers should receive a notification.

=========================================================
WITHOUT OBSERVER PATTERN (PROBLEM)
=========================================================

class YoutubeChannel {

    public void uploadVideo(String title) {

        System.out.println("Video Uploaded : " + title);

        // Manually notifying users
        System.out.println("Notification sent to User1");
        System.out.println("Notification sent to User2");
        System.out.println("Notification sent to User3");
    }
}

Problems:
1. Channel is tightly coupled with users.
2. Every time a new subscriber joins,
   we need to modify this class.
3. Violates Open/Closed Principle.
4. Difficult to maintain.

=========================================================
WITH OBSERVER PATTERN (SOLUTION)
=========================================================

Idea:
1. Create an Observer interface.
2. Every subscriber implements it.
3. YoutubeChannel keeps a list of subscribers.
4. When a video is uploaded,
   notify all subscribers automatically.

=========================================================
*/

import java.util.ArrayList;
import java.util.List;

/*
 * Observer Interface
 *
 * Every subscriber must implement update().
 */
interface Subscriber {
    void update(String videoTitle);
}

/*
 * Concrete Observer
 *
 * Email subscriber receives notifications.
 */
class EmailSubscriber implements Subscriber {

    private String email;

    EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(
                "Email sent to " + email +
                        " for video : " + videoTitle);
    }
}

/*
 * Subject / Observable
 *
 * YoutubeChannel maintains a list of subscribers.
 */
class YoutubeChannel {

    // List of all subscribers
    private List<Subscriber> subscribers = new ArrayList<>();

    /*
     * Add subscriber
     */
    void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /*
     * Remove subscriber
     */
    void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /*
     * Upload video and notify everyone
     */
    void uploadVideo(String videoTitle) {

        System.out.println(
                "\nVideo Uploaded : " + videoTitle);

        // Notify all subscribers
        for (Subscriber subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }
}

/*
 * Client Code
 */
public class ObserverPattern {

    public static void main(String[] args) {

        // Create Youtube Channel
        YoutubeChannel channel = new YoutubeChannel();

        // Create Subscribers
        Subscriber user1 = new EmailSubscriber("user1@gmail.com");

        Subscriber user2 = new EmailSubscriber("user2@gmail.com");

        // Subscribe users
        channel.subscribe(user1);
        channel.subscribe(user2);

        // Upload video
        channel.uploadVideo(
                "Observer Pattern Explained");
    }
}

/*
 * =========================================================
 * OUTPUT
 * =========================================================
 * 
 * Video Uploaded : Observer Pattern Explained
 * 
 * Email sent to user1@gmail.com
 * for video : Observer Pattern Explained
 * 
 * Email sent to user2@gmail.com
 * for video : Observer Pattern Explained
 * 
 * =========================================================
 * FLOW
 * =========================================================
 * 
 * Subscriber1 ---------|
 * |
 * Subscriber2 ---------|----> YoutubeChannel
 * | |
 * Subscriber3 ---------| |
 * |
 * Upload Video
 * |
 * V
 * Notify All Subscribers
 * 
 * =========================================================
 * INTERVIEW ONE-LINER
 * =========================================================
 * 
 * Observer Pattern establishes a one-to-many relationship
 * where multiple objects automatically get notified whenever
 * the state of another object changes.
 * 
 * Example:
 * YouTube Notifications, WhatsApp Groups,
 * Instagram Followers, Stock Price Alerts.
 * 
 * =========================================================
 */