package user;

import entertainment.Movie;
import entertainment.Video;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class User {
    private String username;
    private String subscriptionType;
    private ArrayList<String> favoriteMovies;
    private Map<String, Integer> history;
    private ArrayList<String> moviesRated;
    private Map<String, ArrayList<Integer>> ratedSerials;
    private int numberOfRatings;


    public User() {
        this.username = "username";
        this.subscriptionType = "subscriptionType";
        this.favoriteMovies = new ArrayList<String>();
        this.history = new LinkedHashMap<>();
        this.moviesRated = new ArrayList<String>();
        this.ratedSerials = new LinkedHashMap<>();
        this.numberOfRatings = 0;
    }

    public User (User user) {
        this.username = user.username;
        this.subscriptionType = user.subscriptionType;
        this.favoriteMovies = user.favoriteMovies;
        this.history = user.history;
        this.moviesRated = user.moviesRated;
        this.ratedSerials = user.ratedSerials;
        this.numberOfRatings = user.numberOfRatings;
    }

    public User(UserInputData userData) {
        this.username = userData.getUsername();
        this.subscriptionType = userData.getSubscriptionType();
        this.favoriteMovies = userData.getFavoriteMovies();
        this.history = userData.getHistory();
        this.moviesRated = new ArrayList<String>();
        this.ratedSerials = new LinkedHashMap<>();
        this.numberOfRatings = 0;

    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<String> getMoviesRated() {
        return moviesRated;
    }

    public Map<String, ArrayList<Integer>> getRatedSerials() {
        return ratedSerials;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", favoriteMovies=" + favoriteMovies +
                ", history=" + history +
                '}';
    }
}
