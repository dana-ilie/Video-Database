package sort;

import entertainment.Video;

import java.util.Comparator;

/**
 * Video times added to favorite comparator(ascending order)
 */
public class VideoFavoriteSorterAsc implements Comparator<Video> {
    /**
     * @param o1 first video
     * @param o2 second video
     * @return result of comparison
     */
    @Override
    public int compare(final Video o1, final Video o2) {
        if (o1.getTimesWasAddedToFavorite() - o2.getTimesWasAddedToFavorite() != 0) {
            return o1.getTimesWasAddedToFavorite() - o2.getTimesWasAddedToFavorite();
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
