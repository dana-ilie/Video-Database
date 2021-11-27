package entertainment;

import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Video{
    private final List<Double> ratings;

    public Movie(MovieInputData movieData) {
        super(movieData.getTitle(), movieData.getYear(), movieData.getCast(), movieData.getGenres(), movieData.getDuration());
        this.ratings = new ArrayList<>();
    }

    public Double calculateRating() {
        Double sum = 0.0;
        for (Double rating : ratings) {
            sum += rating;
        }

        return sum / ratings.size();
    }

    public List<Double> getRatings() {
        return ratings;
    }

}
