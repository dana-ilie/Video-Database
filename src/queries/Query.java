package queries;

import entertainment.Movie;
import entertainment.Serial;
import entertainment.Video;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private int number;
    private String objectType;
    private String sortType;
    private List<List<String>> filters = new ArrayList<>();
    private String criteria;

    public Query(String objType, int nr, String sType, List<List<String>> filters, String criteria) {
        this.objectType = objType;
        this.number = nr;
        this.sortType = sType;
        this.filters = filters;
        this.criteria = criteria;
    }

    private ArrayList<User> usersSortedByRatings(ArrayList<User> users) {
        ArrayList<User> sortedUsers = new ArrayList<>();
        for (User user : users) {
            sortedUsers.add(new User(user));
        }

        if (sortType.equals("asc")) {
            sortedUsers.sort(new UserSorterAsc());
        } else {
            sortedUsers.sort(new UserSorterDesc());
        }

        return sortedUsers;
    }


    public String queryAction(ArrayList<User> users, ArrayList<Movie> movies, ArrayList<Serial> serials) {
        String message = new String("");
        if (objectType.equals("users")) {
            ArrayList<User> sortedUsers = usersSortedByRatings(users);
            ArrayList<String> usernames = new ArrayList<>();

            int i = 0;
            for (User user : sortedUsers) {
                if (i < number && user.getNumberOfRatings() > 0) {
                    usernames.add(new String(user.getUsername()));
                }
                i++;
            }
            message = "Query result: " + usernames;
        } else if (objectType.equals("movies")) {
            Video video;

        }


        return message;
    }

}
