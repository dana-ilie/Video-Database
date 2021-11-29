package commands;

import database.Database;
import fileio.ActionInputData;

public class Command {
    private final String username;
    private final String videoTitle;

    public Command(final String name, final String title) {
        this.username = name;
        this.videoTitle = title;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param database ç
     * @return result message
     */
    public String commandAction(final Database database) {
        return "Command Message";
    }

    /**
     * @return command video title
     */
    public String getVideoTitle() {
        return videoTitle;
    }

    /**
     * @param database database with all users, actors, actions, movies, serials
     * @param action the action to be performed
     * @return result message
     */
    public String commandAction(final Database database, final ActionInputData action) {
        return "Rating command message";
    }
}
