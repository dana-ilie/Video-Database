package entertainment;

import fileio.SerialInputData;

import java.util.ArrayList;

public class Serial extends Video{
    private int numberOfSeasons;
    private ArrayList<Season> seasons;
    Double rating;

    public Serial(SerialInputData serialData) {
        super(serialData.getTitle(), serialData.getYear(), serialData.getCast(), serialData.getGenres());
        this.numberOfSeasons = serialData.getNumberSeason();
        this.seasons = serialData.getSeasons();
        this.rating = 0.0;
    }

    public Double calculateRating() {
        Double sum = 0.0;

        for (Season season : seasons) {
            for (Double rating : season.getRatings()) {
                sum += rating;
            }
        }

        return sum / numberOfSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
