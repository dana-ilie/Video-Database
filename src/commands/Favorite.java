package commands;

import user.User;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Favorite extends Command {

    public Favorite(String username, String videoTitle) {
        super("favorite", username, videoTitle);
    }

    public String commandAction(ArrayList<User> users) {
        String message = "";

        for (User user : users) {
            if (user.getUsername().equals(super.getUsername())) {
                /*
                 *check if the user has seen the video
                 */
                if (user.getHistory().get(super.getVideoTitle()) != null) {
                    /*
                     * check if the video is already in the favorites list
                     */
                    Predicate<String> isFav = s -> s.equals(super.getVideoTitle());
                    boolean isInFavorite = user.getFavoriteMovies().stream().anyMatch(isFav);
                    if (isInFavorite) {
                        message = "error -> " + super.getVideoTitle() + " is already in favourite list";
                    } else {
                        user.getFavoriteMovies().add(super.getVideoTitle());
                        message = "success -> " + super.getVideoTitle() + " was added as favourite";
                    }

                } else {
                    message = "error -> " + super.getVideoTitle() + " is not seen";
                }
                break;
            }
        }

        return message;

    }
}
