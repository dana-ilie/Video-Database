package sort;

import entertainment.Video;

import java.util.Comparator;

/**
 * Videos rating comparator(ascending order)
 */
public class VideoRatingSorterAsc implements Comparator<Video> {
    /**
     * @param o1 first video
     * @param o2 second video
     * @return result of comparison
     */
    @Override
    public int compare(final Video o1, final Video o2) {
        if (Double.compare(o1.getRating(), o2.getRating()) != 0) {
            return Double.compare(o1.getRating(), o2.getRating());
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
