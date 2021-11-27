package main;

import actor.Actor;
import commands.Command;
import commands.Favorite;
import commands.Rating;
import commands.View;
import database.Database;
import entertainment.Movie;
import entertainment.Serial;
import fileio.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import queries.Query;
import user.User;

import javax.lang.model.type.ArrayType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")

public class Solver {

    public static JSONArray solve(Input input, Writer fileWriter, JSONArray arrayResult) {
        Database database = new Database(input);
        ArrayList<User> users = database.getUsers();
        ArrayList<Movie> movies = database.getMovies();
        ArrayList<Serial> serials = database.getSerials();
        ArrayList<Actor> actors = database.getActors();
        List<ActionInputData> actions = database.getActions();

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
                    database.setActorsAverage();
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
                String message = query.queryAction(users, movies, serials, actors);
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
