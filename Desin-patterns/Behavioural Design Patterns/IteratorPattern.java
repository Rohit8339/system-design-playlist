/*
 * ============================================================
 * ITERATOR DESIGN PATTERN - YOUTUBE PLAYLIST EXAMPLE
 * ============================================================
 *
 * REAL WORLD EXAMPLE:
 * -------------------
 * YouTube Playlist
 *
 * Next Video?  -> next()
 * More Videos? -> hasNext()
 *
 * User doesn't care whether videos are stored in
 * ArrayList, LinkedList, Array, Database, etc.
 *
 * ============================================================
 * PROBLEM (WITHOUT ITERATOR PATTERN)
 * ============================================================
 */

import java.util.ArrayList;
import java.util.List;

class Video {
    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class VideoPlaylistProblem {

    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    // ❌ Internal collection exposed
    public List<Video> getVideos() {
        return videos;
    }
}

class ProblemDemo {

    public static void main(String[] args) {

        VideoPlaylistProblem playlist = new VideoPlaylistProblem();

        playlist.addVideo(new Video("Java"));
        playlist.addVideo(new Video("Spring Boot"));

        // ❌ Client directly accesses collection
        for (Video video : playlist.getVideos()) {
            System.out.println(video.getTitle());
        }

        // ❌ Client can modify internal data
        playlist.getVideos().clear();
    }
}

/*
 * Problems:
 *
 * ❌ Internal collection exposed
 * ❌ Client knows List is being used
 * ❌ Client can modify data (clear/add/remove)
 * ❌ If storage changes, client may be affected
 *
 * ============================================================
 * SOLUTION (USING ITERATOR PATTERN)
 * ============================================================
 */

/* Iterator Interface */
interface PlaylistIterator {

    boolean hasNext();

    Video next();
}

/* Concrete Iterator */
class VideoPlaylistIterator implements PlaylistIterator {

    private List<Video> videos;
    private int position = 0;

    public VideoPlaylistIterator(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        if (hasNext()) {
            return videos.get(position++);
        }
        return null;
    }
}

/* Playlist */
class VideoPlaylist {

    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    // ✅ Return Iterator instead of exposing collection
    public PlaylistIterator createIterator() {
        return new VideoPlaylistIterator(videos);
    }
}

/* Client */
public class IteratorPattern {

    public static void main(String[] args) {

        VideoPlaylist playlist = new VideoPlaylist();

        playlist.addVideo(new Video("System Design"));
        playlist.addVideo(new Video("Spring Boot"));
        playlist.addVideo(new Video("Java Interview"));

        PlaylistIterator iterator = playlist.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}

/*
 * ============================================================
 * OUTPUT
 * ============================================================
 *
 * System Design
 * Spring Boot
 * Java Interview
 *
 * ============================================================
 * BENEFITS
 * ============================================================
 *
 * ✅ Internal collection hidden
 * ✅ Client doesn't know storage type
 * ✅ Traversal logic separated
 * ✅ Collection protected from direct modification
 * ✅ Changes isolated inside iterator implementation
 *
 * ============================================================
 * FLOW
 * ============================================================
 *
 * Client
 * |
 * v
 * PlaylistIterator
 * |
 * v
 * VideoPlaylist
 * |
 * v
 * Videos
 *
 * ============================================================
 * DEFINITION
 * ============================================================
 *
 * Iterator Pattern provides a way to access elements
 * of a collection sequentially without exposing its
 * internal structure.
 *
 * ============================================================
 */