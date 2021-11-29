package sort;

import entertainment.Video;

import java.util.Comparator;

public class VideoFavoriteSorterAsc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (o1.getTimesWasAddedToFavorite() - o2.getTimesWasAddedToFavorite() != 0) {
            return o1.getTimesWasAddedToFavorite() - o2.getTimesWasAddedToFavorite();
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
