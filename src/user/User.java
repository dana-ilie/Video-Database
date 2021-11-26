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


    public User() {
        this.username = "username";
        this.subscriptionType = "subscriptionType";
        this.favoriteMovies = new ArrayList<String>();
        this.history = new LinkedHashMap<>();
        this.moviesRated = new ArrayList<String>();
        this.ratedSerials = new LinkedHashMap<>();
    }

    public User(UserInputData userData) {
        this.username = userData.getUsername();
        this.subscriptionType = userData.getSubscriptionType();
        this.favoriteMovies = userData.getFavoriteMovies();
        this.history = userData.getHistory();
        this.moviesRated = new ArrayList<String>();
        this.ratedSerials = new LinkedHashMap<>();

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
