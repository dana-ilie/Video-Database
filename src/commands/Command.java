package commands;

import database.Database;
import entertainment.Movie;
import entertainment.Serial;
import fileio.ActionInputData;
import user.User;

import java.util.ArrayList;

public class Command {
    private String commandType;
    private String username;
    private String videoTitle;

    public Command(String type, String name, String title) {
        this.commandType = type;
        this.username = name;
        this.videoTitle = title;
    }

    public String getUsername() {
        return username;
    }

    public String commandAction(Database database) {
        return new String("Command Message");
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String commandAction(Database database, ActionInputData action) {
        return new String("Rating command message");
    }
}
