package sort;

import entertainment.Video;

import java.util.Comparator;

public class VideoFavoriteSorterDesc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (o2.getTimesWasAddedToFavorite() - o1.getTimesWasAddedToFavorite() != 0) {
            return o2.getTimesWasAddedToFavorite() - o1.getTimesWasAddedToFavorite();
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
