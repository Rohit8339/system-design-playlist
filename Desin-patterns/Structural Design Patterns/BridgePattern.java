/*
 * ============================================================
 * BRIDGE PATTERN
 * ============================================================
 *
 * PROBLEM:
 * --------
 * Suppose we have a Video Player application.
 *
 * Platforms:
 * 1. YouTube
 * 2. Netflix
 *
 * Qualities:
 * 1. 480P
 * 2. 720P
 * 3. 1080P
 *
 * Without Bridge Pattern, we need:
 *
 * Youtube480P
 * Youtube720P
 * Youtube1080P
 *
 * Netflix480P
 * Netflix720P
 * Netflix1080P
 *
 * If a new platform or quality is added,
 * the number of classes keeps increasing.
 *
 * This is called CLASS EXPLOSION.
 *
 * ============================================================
 * PROBLEM CODE (WITHOUT BRIDGE)
 * ============================================================
 */

// class Youtube480P {
//     void play() {
//         System.out.println("Playing YouTube in 480P");
//     }
// }
//
// class Youtube720P {
//     void play() {
//         System.out.println("Playing YouTube in 720P");
//     }
// }
//
// class Netflix480P {
//     void play() {
//         System.out.println("Playing Netflix in 480P");
//     }
// }
//
// class Netflix720P {
//     void play() {
//         System.out.println("Playing Netflix in 720P");
//     }
// }

/*
 * ============================================================
 * SOLUTION : BRIDGE PATTERN
 * ============================================================
 *
 * Separate:
 *
 * Video Player (Abstraction)
 * from
 * Video Quality (Implementation)
 *
 * So both can change independently.
 */

/* Implementation Hierarchy */

interface VideoQuality {
    void playQuality();
}

class Q480P implements VideoQuality {
    public void playQuality() {
        System.out.println("Playing in 480P");
    }
}

class Q720P implements VideoQuality {
    public void playQuality() {
        System.out.println("Playing in 720P");
    }
}

class Q1080P implements VideoQuality {
    public void playQuality() {
        System.out.println("Playing in 1080P");
    }
}

/* Abstraction Hierarchy */

abstract class VideoPlayer {

    protected VideoQuality quality;

    VideoPlayer(VideoQuality quality) {
        this.quality = quality;
    }

    abstract void play();
}

/* Refined Abstractions */

class YoutubePlayer extends VideoPlayer {

    YoutubePlayer(VideoQuality quality) {
        super(quality);
    }

    public void play() {
        System.out.print("YouTube : ");
        quality.playQuality();
    }
}

class NetflixPlayer extends VideoPlayer {

    NetflixPlayer(VideoQuality quality) {
        super(quality);
    }

    public void play() {
        System.out.print("Netflix : ");
        quality.playQuality();
    }
}

/*
 * ============================================================
 * CLIENT
 * ============================================================
 */

public class BridgePattern {

    public static void main(String[] args) {

        VideoPlayer youtube = new YoutubePlayer(new Q720P());
        youtube.play();

        VideoPlayer netflix = new NetflixPlayer(new Q1080P());
        netflix.play();
    }
}

/*
 * ============================================================
 * OUTPUT
 * ============================================================
 *
 * YouTube : Playing in 720P
 * Netflix : Playing in 1080P
 *
 * ============================================================
 * KEY IDEA
 * ============================================================
 *
 * VideoPlayer -----> VideoQuality
 *
 * VideoPlayer = WHAT to play
 * VideoQuality = HOW to play
 *
 * The reference variable:
 *
 * protected VideoQuality quality;
 *
 * acts as the BRIDGE between the two hierarchies.
 *
 * BENEFITS:
 * 1. Avoids class explosion
 * 2. Easy to add new players
 * 3. Easy to add new qualities
 * 4. Follows composition over inheritance
 *
 * ============================================================
 */