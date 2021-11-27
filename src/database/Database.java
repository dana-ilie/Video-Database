package database;

import actor.Actor;
import entertainment.Movie;
import entertainment.Serial;
import fileio.*;
import user.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private ArrayList<Actor> actors;
    private ArrayList<User> users;
    private List<ActionInputData> actions;
    private ArrayList<Movie> movies;
    private ArrayList<Serial> serials;

    public Database(Input input) {
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
        setAddedToFavoriteTimes();
    }

    public void setActorsAverage(){
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

            avg = avg / totalVideos;
            actor.setAverage(avg);
        }
    }

    public void setAddedToFavoriteTimes() {
        for (User user : users) {
            for (String favorite : user.getFavoriteMovies()) {
                incVideoFavoriteTimes(favorite);
            }
        }
    }

    public void incVideoFavoriteTimes(String videoTitle) {
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

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public List<ActionInputData> getActions() {
        return actions;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Serial> getSerials() {
        return serials;
    }
}
