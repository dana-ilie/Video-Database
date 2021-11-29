package commands;

import database.Database;
import user.User;

import java.util.ArrayList;

public class View extends Command {
    public View(final String username, final String videoTitle) {
        super(username, videoTitle);
    }

    /**
     * @param database the database
     * @return result message
     */
    @Override
    public String commandAction(final Database database) {
        ArrayList<User> users = database.getUsers();
        String message = super.commandAction(database);
        int nrViews = 0;

        for (User user : users) {
            if (user.getUsername().equals(super.getUsername())) {
                /*
                 * check if the user has seen the video
                 */
                if (user.getHistory().get(super.getVideoTitle()) != null) {
                    String key = super.getVideoTitle();
                    int value = user.getHistory().get(key);
                    user.getHistory().put(key, value + 1);
                    database.viewVideo(super.getVideoTitle());
                    nrViews = user.getHistory().get(super.getVideoTitle());
                } else {
                    /*
                     * view for the first time
                     */
                    String key = super.getVideoTitle();
                    user.getHistory().put(key, 1);
                    database.viewVideo(super.getVideoTitle());
                    nrViews = 1;
                }
            }

            message = "success -> " + super.getVideoTitle()
                    + " was viewed with total views of " + nrViews;
        }

        return message;
    }
}
