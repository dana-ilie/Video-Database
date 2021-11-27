package queries;

import entertainment.Video;

import java.util.Comparator;

public class VideoViewsSorterDesc implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        if (o2.getViews() - o1.getViews() != 0) {
            return o2.getViews() - o1.getViews();
        }

        return o2.getTitle().compareTo(o1.getTitle());
    }
}
