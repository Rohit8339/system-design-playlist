import java.util.HashMap;
import java.util.Map;

// =====================================================
// PROBLEM CODE (WITHOUT PROXY PATTERN)
// =====================================================

/*
Problem:

Every request directly goes to RealVideoDownloader.

Even if the same video is requested multiple times,
it gets downloaded again and again.

Problems:

1. No caching
2. Slower performance
3. Unnecessary network calls
4. Real object handles every request
*/

class RealVideoDownloader {

    public String downloadVideo(String url) {

        System.out.println("Downloading Video : " + url);

        String content = "Video Content From " + url;

        System.out.println("Download Complete");

        return content;
    }
}

/*
 * Client Code
 * 
 * RealVideoDownloader downloader =
 * new RealVideoDownloader();
 * 
 * downloader.downloadVideo("video1");
 * 
 * downloader.downloadVideo("video1");
 * 
 * Problem:
 * 
 * The same video is downloaded twice.
 */

// =====================================================
// SOLUTION CODE (PROXY PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Create a Proxy that sits between
 * the Client and RealVideoDownloader.
 * 
 * The Proxy first checks the cache.
 * 
 * If the video is already available,
 * return it from cache.
 * 
 * Otherwise, download it and store it
 * for future requests.
 */

// Common Interface
interface VideoDownloader {

    String downloadVideo(String url);
}

// Real Object
class RealVideoDownloaderProxyTarget implements VideoDownloader {

    @Override
    public String downloadVideo(String url) {

        System.out.println("Downloading Video : " + url);

        String content = "Video Content From " + url;

        System.out.println("Download Complete");

        return content;
    }
}

// Proxy Object
class CachedVideoDownloader implements VideoDownloader {

    // Real object
    private RealVideoDownloaderProxyTarget realVideoDownloader;

    // Cache storage
    private Map<String, String> cache;

    CachedVideoDownloader() {

        realVideoDownloader = new RealVideoDownloaderProxyTarget();

        cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String url) {

        // Check whether video already exists
        if (cache.containsKey(url)) {

            System.out.println("Returning Cached Video : " + url);

            return cache.get(url);
        }

        // Download from real object
        String content = realVideoDownloader.downloadVideo(url);

        // Store in cache
        cache.put(url, content);

        return content;
    }
}

public class ProxyPattern {

    public static void main(String[] args) {

        VideoDownloader downloader = new CachedVideoDownloader();

        // First request
        downloader.downloadVideo("https://getVideo/myVideo");

        System.out.println();

        // Second request
        downloader.downloadVideo("https://getVideo/myVideo");
    }
}

/*
 * OUTPUT
 * 
 * Downloading Video : https://getVideo/myVideo
 * Download Complete
 * 
 * Returning Cached Video : https://getVideo/myVideo
 * 
 * 
 * CLASS DIAGRAM
 * 
 * 
 * VideoDownloader
 * ^
 * |
 * +-------------+-------------+
 * | |
 * | |
 * RealVideoDownloader CachedVideoDownloader
 * |
 * |
 * +------> RealVideoDownloader
 * 
 * 
 * FLOW
 * 
 * 
 * WITHOUT PROXY
 * 
 * Client
 * |
 * v
 * RealVideoDownloader
 * 
 * Every request downloads the video again.
 * 
 * 
 * 
 * WITH PROXY
 * 
 * Client
 * |
 * v
 * CachedVideoDownloader
 * |
 * +--> Check Cache
 * |
 * +--> If Found
 * | |
 * | v
 * | Return Cached Video
 * |
 * +--> Else
 * |
 * v
 * RealVideoDownloader
 * |
 * v
 * Download Video
 * 
 * 
 * BENEFITS
 * 
 * 1. Adds caching
 * 2. Improves performance
 * 3. Reduces unnecessary downloads
 * 4. Controls access to the real object
 * 5. Client code remains unchanged
 */