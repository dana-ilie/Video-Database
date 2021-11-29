package main;

import actor.Actor;
import commands.Command;
import commands.Favorite;
import commands.Rating;
import commands.View;
import database.Database;
import entertainment.Movie;
import entertainment.Serial;
import fileio.ActionInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import queries.Query;
import recommendations.Recommendation;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")

public class Solver {

    /**
     * @param input application input
     * @param fileWriter file writer
     * @param arrayResult JSONArray with all the results
     */
    public void solve(final Input input,
                                  final Writer fileWriter,
                                  final JSONArray arrayResult) {
        Database database = new Database(input);
        ArrayList<User> users = database.getUsers();
        ArrayList<Movie> movies = database.getMovies();
        ArrayList<Serial> serials = database.getSerials();
        ArrayList<Actor> actors = database.getActors();
        List<ActionInputData> actions = database.getActions();

        for (ActionInputData action : actions) {
            if ("command".equals(action.getActionType())) {
                if ("favorite".equals(action.getType())) {
                    String username = action.getUsername();
                    String title = action.getTitle();
                    Command favorite = new Favorite(username, title);
                    String message = favorite.commandAction(database);

                    try {
                        int actionId = action.getActionId();
                        JSONObject object = fileWriter.writeFile(actionId, "", message);
                        arrayResult.add(object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if ("view".equals(action.getType())) {
                    Command view = new View(action.getUsername(), action.getTitle());
                    String message = view.commandAction(database);

                    try {
                        int actionId = action.getActionId();
                        JSONObject object = fileWriter.writeFile(actionId, "", message);
                        arrayResult.add(object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if ("rating".equals(action.getType())) {
                    String username = action.getUsername();
                    String title = action.getTitle();
                    Double grade = action.getGrade();
                    Command rating = new Rating(username, title, grade);
                    String message = rating.commandAction(database, action);
                    database.setActorsAverage();

                    try {
                        int actionId = action.getActionId();
                        JSONObject object = fileWriter.writeFile(actionId, "", message);
                        arrayResult.add(object);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if ("query".equals(action.getActionType())) {
                String objectType = action.getObjectType();
                int number = action.getNumber();
                String sortType = action.getSortType();
                List<List<String>> filters = action.getFilters();
                String criteria = action.getCriteria();

                Query query = new Query(objectType, number, sortType, filters, criteria);
                String message = query.queryAction(users, movies, serials, actors);

                try {
                    int actionId = action.getActionId();
                    JSONObject object = fileWriter.writeFile(actionId, "", message);
                    arrayResult.add(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("recommendation".equals(action.getActionType())) {
                String type = action.getType();
                String username = action.getUsername();
                String genre = action.getGenre();

                Recommendation recommendation = new Recommendation(type, username, genre);
                String message = recommendation.recommend(users, movies, serials);

                try {
                    int actionId = action.getActionId();
                    JSONObject object = fileWriter.writeFile(actionId, "", message);
                    arrayResult.add(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                String message = "default message";

                try {
                    int actionId = action.getActionId();
                    JSONObject object = fileWriter.writeFile(actionId, "", message);
                    arrayResult.add(object);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
