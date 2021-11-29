package sort;

import entertainment.Video;

import java.util.Comparator;

/**
 * Videos duration comparator(ascending order)
 */
public class VideoDurationSorterAsc implements Comparator<Video> {
    /**
     * @param o1 first video
     * @param o2 second video
     * @return result of comparison
     */
    @Override
    public int compare(final Video o1, final Video o2) {
        if (o1.getTotalDuration() - o2.getTotalDuration() != 0) {
            return o1.getTotalDuration() - o2.getTotalDuration();
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
