package sort;

import entertainment.Video;

import java.util.Comparator;

/**
 * Videos views comparator(descending order)
 */
public class VideoViewsSorterDesc implements Comparator<Video> {
    /**
     * @param o1 first video
     * @param o2 second video
     * @return result of comparison
     */
    @Override
    public int compare(final Video o1, final Video o2) {
        if (o2.getViews() - o1.getViews() != 0) {
            return o2.getViews() - o1.getViews();
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
