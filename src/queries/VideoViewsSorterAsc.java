package queries;

import entertainment.Video;

import java.util.Comparator;

public class VideoViewsSorterAsc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (o1.getViews() - o2.getViews() != 0) {
            return o1.getViews() - o2.getViews();
        }

        return o1.getTitle().compareTo(o2.getTitle());
    }
}
