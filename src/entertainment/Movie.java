package entertainment;

import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Video {
    private final List<Double> ratings;

    public Movie(final MovieInputData movieData) {
        super(movieData.getTitle(), movieData.getYear(), movieData.getCast(),
                movieData.getGenres(), movieData.getDuration());
        this.ratings = new ArrayList<>();
    }

    /**
     * @return the rating of the movie
     */
    public Double calculateRating() {
        Double sum = 0.0;
        for (Double rating : ratings) {
            sum += rating;
        }

        return sum / ratings.size();
    }

    /**
     * @return list of ratings
     */
    public List<Double> getRatings() {
        return ratings;
    }

}
