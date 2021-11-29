package sort;

import entertainment.Video;

import java.util.ArrayList;
import java.util.List;

public class Sorter {
    private final String sortType;

    public Sorter(String sortType) {
        this.sortType = sortType;
    }

    public List<Video> videosSortedByRating (List<? extends Video> videos) {
        List<Video> sortedVideos = new ArrayList<>();
        for (Video video : videos) {
            sortedVideos.add(new Video(video));
        }

        if (sortType.equals("asc")) {
            sortedVideos.sort(new VideoRatingSorterAsc());
        } else {
            sortedVideos.sort(new VideoRatingSorterDesc());
        }

        return sortedVideos;
    }
}
