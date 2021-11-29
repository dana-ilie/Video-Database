package user;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class User {
    private final String username;
    private final String subscriptionType;
    private final ArrayList<String> favoriteMovies;
    private final Map<String, Integer> history;
    private final ArrayList<String> moviesRated;
    private final Map<String, ArrayList<Integer>> ratedSerials;
    private int numberOfRatings;


    public User() {
        this.username = "username";
        this.subscriptionType = "subscriptionType";
        this.favoriteMovies = new ArrayList<>();
        this.history = new LinkedHashMap<>();
        this.moviesRated = new ArrayList<>();
        this.ratedSerials = new LinkedHashMap<>();
        this.numberOfRatings = 0;
    }

    public User(final User user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.favoriteMovies = user.getFavoriteMovies();
        this.history = user.getHistory();
        this.moviesRated = user.getMoviesRated();
        this.ratedSerials = user.getRatedSerials();
        this.numberOfRatings = user.getNumberOfRatings();
    }

    public User(final UserInputData userData) {
        this.username = userData.getUsername();
        this.subscriptionType = userData.getSubscriptionType();
        this.favoriteMovies = userData.getFavoriteMovies();
        this.history = userData.getHistory();
        this.moviesRated = new ArrayList<>();
        this.ratedSerials = new LinkedHashMap<>();
        this.numberOfRatings = 0;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return user's history map
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * @return user's list of favorites
     */
    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    /**
     * @return list of movies rated by the user
     */
    public ArrayList<String> getMoviesRated() {
        return moviesRated;
    }

    /**
     * @return list of serials rated by the user
     */
    public Map<String, ArrayList<Integer>> getRatedSerials() {
        return ratedSerials;
    }

    /**
     * @return number of ratings given by the user
     */
    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    /**
     * @param numberOfRatings number of rating given by the user value to be set
     */
    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    /**
     * @return user's subscription type
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * @return string
     */
    @Override
    public String toString() {
        return "User{"
                +
                "username='" + username + '\''
                +
                ", subscriptionType='" + subscriptionType + '\''
                +
                ", favoriteMovies=" + favoriteMovies
                +
                ", history=" + history
                +
                '}';
    }
}
