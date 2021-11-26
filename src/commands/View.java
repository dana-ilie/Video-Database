package commands;

import user.User;

import java.util.ArrayList;

public class View extends Command {
    public View(String username, String videoTitle) {
        super("view", username, videoTitle);
    }

    @Override
    public String commandAction(ArrayList<User> users) {
        String message = super.commandAction(users);
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
                    nrViews = user.getHistory().get(super.getVideoTitle());
                } else {
                    /*
                     * view for the first time
                     */
                    String key = super.getVideoTitle();
                    user.getHistory().put(key, 1);
                    nrViews = 1;
                }
            }
            //int nrViews = user.getHistory().get(super.getVideoTitle());
            message = "success -> " + super.getVideoTitle() + " was viewed with total views of " + nrViews;
        }

        return message;
    }
}
