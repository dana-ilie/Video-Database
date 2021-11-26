package entertainment;

import fileio.SerialInputData;

import java.util.ArrayList;

public class Serial extends Video{
    private int numberOfSeasons;
    private ArrayList<Season> seasons;

    public Serial(SerialInputData serialData) {
        super(serialData.getTitle(), serialData.getYear(), serialData.getCast(), serialData.getGenres());
        this.numberOfSeasons = serialData.getNumberSeason();
        this.seasons = serialData.getSeasons();
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
