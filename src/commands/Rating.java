package commands;

import database.Database;
import entertainment.Movie;
import entertainment.Serial;
import fileio.ActionInputData;
import user.User;

import java.util.ArrayList;

public class Rating extends Command {
    private final Double grade;

    public Rating(final String username, final String videoTitle, final Double grade) {
        super(username, videoTitle);
        this.grade = grade;
    }


    /**
     * @param database database with all users, actors, actions, movies, serials
     * @param action the action to be performed data
     * @return result message
     */
    public String commandAction(final Database database, final ActionInputData action) {
        ArrayList<User> users = database.getUsers();
        ArrayList<Movie> movies = database.getMovies();
        ArrayList<Serial> serials = database.getSerials();

        String message = super.commandAction(database, action);
        User user = new User();

        /*
         * find the user
         */
        for (User u : users) {
            if (u.getUsername().equals(super.getUsername())) {
                user = u;
            }
        }

        /*
         * check if the video was seen
         */
        int videoWasSeen = 0;
        if (user.getHistory().get(super.getVideoTitle()) != null) {
            videoWasSeen = 1;
        }

        if (videoWasSeen == 1) {
            /*
             * rate a movie
             */
            if (action.getSeasonNumber() == 0) {
                if (user.getMoviesRated().size() == 0) {
                    message = rateMovie(database, movies, user);
                } else {
                    /*
                     * check if the movie was rated
                     */
                    int movieWasRated = 0;
                    for (String movieTitle : user.getMoviesRated()) {
                        if (movieTitle.equals(super.getVideoTitle())) {
                            message = "error -> " + movieTitle + " has been already rated";
                            movieWasRated = 1;
                        }
                    }
                    if (movieWasRated == 0) {
                        message = rateMovie(database, movies, user);
                    }
                }
            } else {
                /*
                 * rate a tv show season
                 */
                int seasonNumber = action.getSeasonNumber();
                if (user.getRatedSerials().size() == 0 || user.getRatedSerials()
                        .get(super.getVideoTitle()) == null) {
                    ArrayList<Integer> ratedSeasons = new ArrayList<>();
                    ratedSeasons.add(seasonNumber);
                    user.getRatedSerials().put(super.getVideoTitle(), ratedSeasons);

                    message = rateSerial(database, serials, user, seasonNumber);
                } else {
                    /*
                     * check if the season was rated
                     */
                    int seasonWasRated = 0;
                    for (Integer seasonNr : user.getRatedSerials().get(super.getVideoTitle())) {
                        if (seasonNr.equals(seasonNumber)) {
                            seasonWasRated = 1;
                            break;
                        }
                    }

                    if (seasonWasRated == 1) {
                        message = "error -> " + super.getVideoTitle() + " has been already rated";
                    } else {
                        user.getRatedSerials().get(super.getVideoTitle()).add(seasonNumber);
                        message = rateSerial(database, serials, user, seasonNumber);
                    }
                }
            }
        } else {
            message = "error -> " + super.getVideoTitle() + " is not seen";
        }

        return message;
    }

    private String rateSerial(final Database database,
                              final ArrayList<Serial> serials,
                              final User user,
                              final int seasonNumber) {
        String message;
        for (Serial serial : serials) {
            if (serial.getTitle().equals(super.getVideoTitle())) {
                serial.getSeasons().get(seasonNumber - 1).getRatings().add(grade);
                serial.setRating(serial.calculateRating());
                database.setActorsAverage();
            }
        }

        user.setNumberOfRatings(user.getNumberOfRatings() + 1);
        message = "success -> " + super.getVideoTitle()
                + " was rated with " + grade + " by " + super.getUsername();
        return message;
    }

    private String rateMovie(final Database database,
                             final ArrayList<Movie> movies,
                             final User user) {
        String message;
        user.getMoviesRated().add(super.getVideoTitle());

        for (Movie movie : movies) {
            if (movie.getTitle().equals(super.getVideoTitle())) {
                movie.getRatings().add(grade);
                movie.setRating(movie.calculateRating());
                database.setActorsAverage();
            }
        }

        user.setNumberOfRatings(user.getNumberOfRatings() + 1);
        message = "success -> " + super.getVideoTitle()
                + " was rated with " + grade + " by " + user.getUsername();
        return message;
    }

}
