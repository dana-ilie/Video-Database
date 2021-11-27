package queries;

import actor.Actor;
import entertainment.Movie;
import entertainment.Serial;
import entertainment.Video;
import user.User;
import java.util.*;

import static utils.Utils.stringToAwards;

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

    private ArrayList<Actor> actorsSortedByAverage(ArrayList<Actor> actors) {
        ArrayList<Actor> sortedActors = new ArrayList<>();
        for (Actor actor : actors) {
            sortedActors.add(new Actor(actor));
        }

        if (sortType.equals("asc")) {
            sortedActors.sort(new ActorAvgSorterAsc());
        } else {
            sortedActors.sort(new ActorAvgSorterDesc());
        }

        return sortedActors;
    }

    private ArrayList<Actor> filterActors(ArrayList<Actor> actors) {
        ArrayList<Actor> filteredActors = new ArrayList<>();

        // if awards filter exists => filter actors by awards
        if (filters.get(3) != null && filters.get(3).size() != 0 ) {
            for (Actor actor : actors) {
                // check if actor has all awards
                int actorHasAllAwards = 1;
                for (String award : filters.get(3)) {
                    if (actor.getAwards().get(stringToAwards(award)) == null) {
                        actorHasAllAwards = 0;
                    }
                }
                if (actorHasAllAwards == 1) {
                    filteredActors.add(actor);
                }
            }
        }

        // if words filter exists => filter actors by words in career description
        if (filters.get(2) != null && filters.get(2).size() != 0) {
            for (Actor actor : actors) {
                // check if actor description has all words
                int descriptionHasAllWords = 1;
                for (String word : filters.get(2)) {
                    if (!actor.getCareerDescription().toLowerCase().contains(word.toLowerCase())) {
                        descriptionHasAllWords = 0;
                        break;
                    }
                }

                if (descriptionHasAllWords == 1) {
                    filteredActors.add(actor);
                }
            }
        }

        return filteredActors;
    }

    private ArrayList<Actor> actorsSortedByAwards (ArrayList<Actor> actors) {
        ArrayList<Actor> sortedActors = new ArrayList<>();
        for (Actor actor : actors) {
            sortedActors.add(new Actor(actor));
        }

        if (sortType.equals("asc")) {
            sortedActors.sort(new ActorAwardsSorterAsc());
        } else {
            sortedActors.sort(new ActorAwardsSorterDesc());
        }

        return sortedActors;
    }

    private ArrayList<Actor> actorsSortedByName (ArrayList<Actor> actors) {
        ArrayList<Actor> sortedActors = new ArrayList<>();
        for (Actor actor : actors) {
            sortedActors.add(new Actor(actor));
        }

        if (sortType.equals("asc")) {
            sortedActors.sort(new ActorNameSorterAsc());
        } else {
            sortedActors.sort(new ActorNameSorterDesc());
        }

        return sortedActors;
    }

    public String queryAction(ArrayList<User> users, ArrayList<Movie> movies,
                              ArrayList<Serial> serials,
                              ArrayList<Actor> actors) {
        String message = "";
        if (objectType.equals("users")) {
            ArrayList<User> sortedUsers = usersSortedByRatings(users);
            ArrayList<String> usernames = new ArrayList<>();

            int i = 0;
            for (User user : sortedUsers) {
                if (i < number && user.getNumberOfRatings() > 0) {
                    usernames.add(user.getUsername());
                }
                i++;
            }

            message = "Query result: " + usernames;
        } else if (objectType.equals("actors")) {
            if (criteria.equals("average")) {
                ArrayList<Actor> sortedActors = actorsSortedByAverage(actors);
                ArrayList<String> actorNames = new ArrayList<>();

                int i = 0;
                for (Actor actor : sortedActors) {
                    if (i < number && actor.getAverage() > 0) {
                        actorNames.add(actor.getName());
                    }
                    i++;
                }

                message = "Query result: " + actorNames;
            } else if (criteria.equals("awards")) {
                ArrayList<Actor> filteredActors = filterActors(actors);
                ArrayList<Actor> sortedActors = actorsSortedByAwards(filteredActors);
                ArrayList<String> actorNames = new ArrayList<>();

                int i = 0;
                for (Actor actor : sortedActors) {
                    if (i < number && actor.getNumberOfAwards() > 0) {
                        actorNames.add(actor.getName());
                    }
                    i++;
                }

                message = "Query result: " + actorNames;
            } else if (criteria.equals("filter_description")) {
                ArrayList<Actor> filteredActors = filterActors(actors);
                ArrayList<Actor> sortedActors = actorsSortedByName(filteredActors);
                ArrayList<String> actorNames = new ArrayList<>();

                int i = 0;
                for (Actor actor : sortedActors) {
                    if (i < number) {
                        actorNames.add(actor.getName());
                    }
                    i++;
                }

                message = "Query result: " + actorNames;
            }
        } else if (criteria.equals("ratings")) {
            if (objectType.equals("movies")) {


            } else if (objectType.equals("shows")) {

            }

        } else if (criteria.equals("favorite")) {

        } else if (criteria.equals("longest")) {

        } else if (criteria.equals("most_viewed")) {

        }

        return message;
    }

}
