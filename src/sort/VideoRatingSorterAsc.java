package sort;

import entertainment.Video;

import java.util.Comparator;

public class VideoRatingSorterAsc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (Double.compare(o1.getRating(), o2.getRating()) != 0) {
            return Double.compare(o1.getRating(), o2.getRating());
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
