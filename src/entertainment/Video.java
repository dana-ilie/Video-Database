package entertainment;

import java.util.ArrayList;

public class Video {
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;
    private Double rating;
    private int timesWasAddedToFavorite;
    private int totalDuration;
    private int views;

    public Video(final Video video) {
        this.title = video.getTitle();
        this.year = video.getYear();
        this.cast = video.getCast();
        this.genres = video.getGenres();
        this.rating = video.getRating();
        this.timesWasAddedToFavorite = video.getTimesWasAddedToFavorite();
        this.totalDuration = video.getTotalDuration();
        this.views = video.getViews();
    }

    public Video(final String title, final int year,
                 final ArrayList<String> cast,
                 final ArrayList<String> genres,
                 final int totalDuration) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.rating = 0.0;
        this.timesWasAddedToFavorite = 0;
        this.totalDuration = totalDuration;
        this.views = 0;
    }

    public Video(final String title, final int year,
                 final ArrayList<String> cast,
                 final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.rating = 0.0;
        this.timesWasAddedToFavorite = 0;
        this.views = 0;
    }

    /**
     * @return default rating = 0.0
     */
    public Double calculateRating() {
        return 0.0;
    }

    /**
     * @return video title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return cast list
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * @return genres list
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * @return video rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating rating value to be set
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }

    /**
     * @return number of times a video was added to a favorite list
     */
    public int getTimesWasAddedToFavorite() {
        return timesWasAddedToFavorite;
    }

    /**
     * @param timesWasAddedToFavorite number of times a video was added to a favorite list
     */
    public void setTimesWasAddedToFavorite(final int timesWasAddedToFavorite) {
        this.timesWasAddedToFavorite = timesWasAddedToFavorite;
    }

    /**
     * @return video total duration
     */
    public int getTotalDuration() {
        return totalDuration;
    }

    /**
     * @param totalDuration video total duration value to be set
     */
    public void setTotalDuration(final int totalDuration) {
        this.totalDuration = totalDuration;
    }

    /**
     * @return video number of views
     */
    public int getViews() {
        return views;
    }

    /**
     * @param views video number of views to be set
     */
    public void setViews(final int views) {
        this.views = views;
    }
}
