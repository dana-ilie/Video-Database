package main;

import commands.Command;
import commands.Favorite;
import commands.Rating;
import commands.View;
import common.Constants;
import entertainment.Movie;
import entertainment.Serial;
import entertainment.Video;
import fileio.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import queries.Query;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")

public class Solver {

    public static JSONArray solve(Input input, Writer fileWriter, JSONArray arrayResult) {
        List<ActionInputData> actions = input.getCommands();
        ArrayList<User> users = new ArrayList<User>();
        for (UserInputData user : input.getUsers()) {
            users.add(new User(user));
        }

        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (MovieInputData movie : input.getMovies()) {
            movies.add(new Movie((movie)));
        }

        ArrayList<Serial> serials = new ArrayList<Serial>();
        for (SerialInputData serial : input.getSerials()) {
            serials.add(new Serial(serial));
        }

        for (ActionInputData action : actions) {
            if (action.getActionType().equals("command")) {
                if (action.getType().equals("favorite")) {
                    Command favorite = new Favorite(action.getUsername(), action.getTitle());
                    String message = favorite.commandAction(users);
                    try {
                        JSONObject object = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (action.getType().equals("view")) {
                    Command view = new View(action.getUsername(), action.getTitle());
                    String message = view.commandAction(users);
                    try {
                        JSONObject object = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (action.getType().equals("rating")) {
                    Command rating = new Rating(action.getUsername(), action.getTitle(), action.getGrade());
                    String message = rating.commandAction(users, movies, serials, action);
                    try {
                        JSONObject object = fileWriter.writeFile(action.getActionId(), "", message);
                        arrayResult.add(object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (action.getActionType().equals("query")) {
                String objectType = action.getObjectType();
                int number = action.getNumber();
                String sortType = action.getSortType();
                List<List<String>> filters = action.getFilters();
                String criteria = action.getCriteria();

                Query query = new Query(objectType, number, sortType, filters, criteria);
                String message = query.queryAction(users, movies, serials);
                try {
                    JSONObject object = fileWriter.writeFile(action.getActionId(), "", message);
                    arrayResult.add(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (action.getActionType().equals("recommendation")) {

            }
        }



        return arrayResult;
    }
}
