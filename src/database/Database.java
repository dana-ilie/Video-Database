package database;

import actor.Actor;
import entertainment.Movie;
import entertainment.Serial;
import fileio.Input;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Database {
    private final ArrayList<Actor> actors;
    private final ArrayList<User> users;
    private final List<ActionInputData> actions;
    private final ArrayList<Movie> movies;
    private final ArrayList<Serial> serials;

    public Database(final Input input) {
        actions = input.getCommands();
        users = new ArrayList<>();
        for (UserInputData user : input.getUsers()) {
            users.add(new User(user));
        }

        movies = new ArrayList<>();
        for (MovieInputData movie : input.getMovies()) {
            movies.add(new Movie((movie)));
        }

        serials = new ArrayList<>();
        for (SerialInputData serial : input.getSerials()) {
            serials.add(new Serial(serial));
        }
        actors = new ArrayList<>();
        for (ActorInputData actor : input.getActors()) {
            actors.add(new Actor(actor));
        }
        setActorsAverage();
        setAddedToFavoriteTimes();
        calculateViews();
    }

    /**
     * Calculates the number of views of each video
     */
    public void calculateViews() {
        for (User user : users) {
            for (Map.Entry<String, Integer> entry : user.getHistory().entrySet()) {
                // find video
                for (Movie movie : movies) {
                    if (entry.getKey().equals(movie.getTitle())) {
                        // increment movie views
                        movie.setViews(movie.getViews() + entry.getValue());
                    }
                }
                for (Serial serial : serials) {
                    if (entry.getKey().equals(serial.getTitle())) {
                        // increment serial views
                        serial.setViews(serial.getViews() + entry.getValue());
                    }
                }
            }
        }
    }

    /**
     * @param videoTitle the title of the video to be viewed
     */
    public void viewVideo(final String videoTitle) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(videoTitle)) {
                movie.setViews(movie.getViews() + 1);
            }
        }
        for (Serial serial : serials) {
            if (serial.getTitle().equals(videoTitle)) {
                serial.setViews(serial.getViews() + 1);
            }
        }
    }

    /**
     * Sets the average for each actor
     */
    public void setActorsAverage() {
        for (Actor actor : actors) {
            /*
             * calculate average for actor
             */
            int totalVideos = 0;
            Double avg = 0.0;

            for (String film : actor.getFilmography()) {
                for (Movie movie : movies) {
                    if (film.equals(movie.getTitle()) && movie.getRating() != 0) {
                        avg += movie.getRating();
                        totalVideos++;
                    }
                }
                for (Serial serial : serials) {
                    if (film.equals(serial.getTitle()) && serial.getRating() != 0) {
                        avg += serial.getRating();
                        totalVideos++;
                    }
                }
            }

            if (totalVideos != 0) {
                avg = avg / totalVideos;
                actor.setAverage(avg);
            }
        }
    }

    /**
     * Sets the number of times each video was added to a favorite list
     */
    public void setAddedToFavoriteTimes() {
        for (User user : users) {
            for (String favorite : user.getFavoriteMovies()) {
                incVideoFavoriteTimes(favorite);
            }
        }
    }

    /**
     * @param videoTitle The title of the video to have its timesWasAddedToFavorite var incremented
     */
    public void incVideoFavoriteTimes(final String videoTitle) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(videoTitle)) {
                movie.setTimesWasAddedToFavorite(movie.getTimesWasAddedToFavorite() + 1);
            }
        }

        for (Serial serial : serials) {
            if (serial.getTitle().equals(videoTitle)) {
                serial.setTimesWasAddedToFavorite(serial.getTimesWasAddedToFavorite() + 1);
            }
        }
    }

    /**
     * @return actors list
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }

    /**
     * @return users list
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @return actions list
     */
    public List<ActionInputData> getActions() {
        return actions;
    }

    /**
     * @return movies list
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * @return serials list
     */
    public ArrayList<Serial> getSerials() {
        return serials;
    }
}
