package actor;

import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;
    private Double average;
    private int numberOfAwards;

    public Actor(final Actor actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        this.average = actor.getAverage();
        this.numberOfAwards = actor.getNumberOfAwards();
    }

    public Actor(final ActorInputData actor) {
        this.name = actor.getName();
        this.careerDescription = actor.getCareerDescription();
        this.filmography = actor.getFilmography();
        this.awards = actor.getAwards();
        this.average = 0.0;
        this.numberOfAwards = 0;
        calculateNumberOfAwards();
    }

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography,
                 final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.average = 0.0;
        this.numberOfAwards = 0;
        calculateNumberOfAwards();
    }

    /**
     * Calculates the number of awards the actor has won
     */
    private void calculateNumberOfAwards() {
        for (Map.Entry<ActorsAwards, Integer> entry : awards.entrySet()) {
            numberOfAwards += entry.getValue();
        }
    }

    /**
     * @return actor name
     */
    public String getName() {
        return name;
    }

    /**
     * @return actor career description
     */
    public String getCareerDescription() {
        return careerDescription;
    }

    /**
     * @return list of videos the actor has played in
     */
    public ArrayList<String> getFilmography() {
        return filmography;
    }

    /**
     * @return actor's awards
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    /**
     * @return actor's average
     */
    public Double getAverage() {
        return average;
    }

    /**
     * @param average actor's average value to be set
     */
    public void setAverage(final Double average) {
        this.average = average;
    }

    /**
     * @return actor's number of awards won
     */
    public int getNumberOfAwards() {
        return numberOfAwards;
    }

}
