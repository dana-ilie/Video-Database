package sort;

import entertainment.Video;

import java.util.Comparator;

/**
 * Videos rating comparator(descending order)
 */
public class VideoRatingSorterDesc implements Comparator<Video> {
    /**
     * @param o1 first video
     * @param o2 second video
     * @return result of comparison
     */
    @Override
    public int compare(final Video o1, final Video o2) {
        if (Double.compare(o2.getRating(), o1.getRating()) != 0) {
            return Double.compare(o2.getRating(), o1.getRating());
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
