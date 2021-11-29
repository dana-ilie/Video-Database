package sort;

import entertainment.Video;

import java.util.Comparator;

public class VideoRatingSorterDesc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (Double.compare(o2.getRating(), o1.getRating()) != 0) {
            return Double.compare(o2.getRating(), o1.getRating());
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
