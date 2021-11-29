package sort;

import entertainment.Video;

import java.util.Comparator;

/**
 * Video times added to favorite comparator(descending order)
 */
public class VideoFavoriteSorterDesc implements Comparator<Video> {
    /**
     * @param o1 first video
     * @param o2 second video
     * @return result of comparison
     */
    @Override
    public int compare(final Video o1, final Video o2) {
        if (o2.getTimesWasAddedToFavorite() - o1.getTimesWasAddedToFavorite() != 0) {
            return o2.getTimesWasAddedToFavorite() - o1.getTimesWasAddedToFavorite();
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
