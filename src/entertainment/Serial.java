package entertainment;

import fileio.SerialInputData;

import java.util.ArrayList;

public class Serial extends Video {
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    public Serial(final SerialInputData serialData) {
        super(serialData.getTitle(), serialData.getYear(),
                serialData.getCast(), serialData.getGenres());
        this.numberOfSeasons = serialData.getNumberSeason();
        this.seasons = serialData.getSeasons();
        super.setTotalDuration(calculateDuration());
    }

    /**
     * @return total duration of the serial(sum of seasons durations)
     */
    public int calculateDuration() {
        int duration = 0;
        for (Season season : seasons) {
            duration += season.getDuration();
        }

        return duration;
    }

    /**
     * @return the rating of the serial
     */
    public Double calculateRating() {
        Double sum = 0.0;

        for (Season season : seasons) {
            for (Double rating : season.getRatings()) {
                sum += rating;
            }
        }

        return sum / numberOfSeasons;
    }

    /**
     * @return list of seasons
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

}
