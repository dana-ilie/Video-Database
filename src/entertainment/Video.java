package entertainment;

import java.util.ArrayList;

public class Video {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private Double rating;
    int timesWasAddedToFavorite;

    public Video(Video video) {
        this.title = video.getTitle();
        this.year = video.getYear();
        this.cast = video.getCast();
        this.genres = video.getGenres();
        this.rating = video.getRating();
        this.timesWasAddedToFavorite = video.getTimesWasAddedToFavorite();
    }

    public Video(final String title, final int year,
                 final ArrayList<String> cast, final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.rating = 0.0;
        this.timesWasAddedToFavorite = 0;
    }

    public Double calculateRating() {
        return 0.0;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getTimesWasAddedToFavorite() {
        return timesWasAddedToFavorite;
    }

    public void setTimesWasAddedToFavorite(int timesWasAddedToFavorite) {
        this.timesWasAddedToFavorite = timesWasAddedToFavorite;
    }
}
