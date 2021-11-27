package queries;

import entertainment.Video;

import java.util.Comparator;

public class VideoDurationSorterAsc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (o1.getTotalDuration() - o2.getTotalDuration() != 0) {
            return o1.getTotalDuration() - o2.getTotalDuration();
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
