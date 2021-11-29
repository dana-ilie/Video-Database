package sort;

import entertainment.Video;

import java.util.Comparator;

public class VideoDurationSorterDesc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (o2.getTotalDuration() - o1.getTotalDuration() != 0) {
            return o2.getTotalDuration() - o1.getTotalDuration();
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
