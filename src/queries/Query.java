package queries;

import actor.Actor;
import entertainment.Movie;
import entertainment.Serial;
import entertainment.Video;
import sort.Sorter;
import user.User;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

import static utils.Utils.stringToAwards;

public class Query {
    private final int number;
    private final String objectType;
    private final String sortType;
    private final List<List<String>> filters;
    private final String criteria;
    public static final int AWARDS_FILTER_NUM = 3;
    public static final int WORDS_FILTER_NUM = 2;

    public Query(final String objType, final int nr, final String sType,
                 final List<List<String>> filters, final String criteria) {
        this.objectType = objType;
        this.number = nr;
        this.sortType = sType;
        this.filters = filters;
        this.criteria = criteria;
    }

    /**
     * @param actors list of actors to be filtered
     * @return a list of filtered actors
     */
    private ArrayList<Actor> filterActors(final ArrayList<Actor> actors) {
        ArrayList<Actor> filteredActors = new ArrayList<>();

        /*
         * if awards filter exists => filter actors by awards
         */
        if (filters.get(AWARDS_FILTER_NUM) != null && filters.get(AWARDS_FILTER_NUM).size() != 0) {
            for (Actor actor : actors) {
                /*
                 * check if the actor has all awards
                 */
                int actorHasAllAwards = 1;
                for (String award : filters.get(AWARDS_FILTER_NUM)) {
                    if (actor.getAwards().get(stringToAwards(award)) == null) {
                        actorHasAllAwards = 0;
                    }
                }
                if (actorHasAllAwards == 1) {
                    filteredActors.add(actor);
                }
            }
        }

        /*
         * if words filter exists => filter actors by words in career description
         */
        if (filters.get(WORDS_FILTER_NUM) != null && filters.get(WORDS_FILTER_NUM).size() != 0) {
            for (Actor actor : actors) {
                /*
                 * check if actor description has all words
                 */
                int descriptionHasAllWords = 1;
                for (String word : filters.get(WORDS_FILTER_NUM)) {
                    String word1 = " " + word + " ";
                    String word2 = " " + word + ",";
                    String word3 = " " + word + ".";
                    String word4 = "-" + word + " ";
                    String description = actor.getCareerDescription().toLowerCase();

                    if (!description.contains(word1.toLowerCase())) {
                        if (!description.contains(word2.toLowerCase())) {
                            if (!description.contains(word3.toLowerCase())) {
                                if (!description.contains(word4.toLowerCase())) {
                                    descriptionHasAllWords = 0;
                                    break;
                                }
                            }
                        }
                    }
                }

                if (descriptionHasAllWords == 1) {
                    filteredActors.add(actor);
                }
            }
        }

        return filteredActors;
    }

    /**
     * @param videos list of videos to be sorted
     * @return a list of filtered videos
     */
    private List<Video> filterVideo(final ArrayList<? extends Video> videos) {
        List<Video> filteredVideos = new ArrayList<>();
        for (Video video : videos) {
            filteredVideos.add(new Video(video));
        }

        if (filters != null && filters.size() != 0) {
            Predicate<Video> videoIsFromYears = video -> {
                boolean isFromYears = false;
                for (String year : filters.get(0)) {
                    if (String.valueOf(video.getYear()).equals(year)) {
                        isFromYears = true;
                        break;
                    }
                }
                return isFromYears;
            };

            Predicate<Video> hasGenre = video -> {
                boolean has = false;

                for (String genre : filters.get(1)) {
                    for (String videoGenre : video.getGenres()) {
                        if (videoGenre.equals(genre)) {
                            has = true;
                            break;
                        }
                    }
                }

                return has;
            };

            /*
             * if year filter exists => filter the videos by the years
             */
            if (filters.get(0) != null && filters.get(0).get(0) != null) {
                filteredVideos = filteredVideos.stream().filter(videoIsFromYears).toList();
            }
            /*
             * if genre filter exists => filter the videos by genres
             */
            if (filters.get(1) != null && filters.get(1).get(0) != null) {
                filteredVideos = filteredVideos.stream().filter(hasGenre).toList();
            }
        }

        return filteredVideos;
    }

    /**
     * @param users list of users
     * @param movies list of movies
     * @param serials list of serials
     * @param actors list of actors
     * @return action result message
     */
    public String queryAction(final ArrayList<User> users,
                              final ArrayList<Movie> movies,
                              final ArrayList<Serial> serials,
                              final ArrayList<Actor> actors) {
        String message = "";
        Sorter sorter = new Sorter(sortType);

        if (objectType.equals("users")) {
            ArrayList<User> sortedUsers = sorter.usersSortedByRatings(users);
            ArrayList<String> usernames = new ArrayList<>();

            int i = 0;
            for (User user : sortedUsers) {
                if (i < number && user.getNumberOfRatings() > 0) {
                    usernames.add(user.getUsername());
                    i++;
                }
            }
            message = "Query result: " + usernames;
        } else if (objectType.equals("actors")) {
            if ("average".equals(criteria)) {
                ArrayList<Actor> sortedActors = sorter.actorsSortedByAverage(actors);
                ArrayList<String> actorNames = new ArrayList<>();

                int i = 0;
                for (Actor actor : sortedActors) {
                    if (i < number && actor.getAverage() > 0) {
                        actorNames.add(actor.getName());
                        i++;
                    }
                }
                message = "Query result: " + actorNames;
            } else if ("awards".equals(criteria)) {
                ArrayList<Actor> filteredActors = filterActors(actors);
                ArrayList<Actor> sortedActors = sorter.actorsSortedByAwards(filteredActors);
                ArrayList<String> actorNames = new ArrayList<>();

                int i = 0;
                for (Actor actor : sortedActors) {
                    if (i < number && actor.getNumberOfAwards() > 0) {
                        actorNames.add(actor.getName());
                        i++;
                    }
                }
                message = "Query result: " + actorNames;
            } else if ("filter_description".equals(criteria)) {
                ArrayList<Actor> filteredActors = filterActors(actors);
                ArrayList<Actor> sortedActors = sorter.actorsSortedByName(filteredActors);
                ArrayList<String> actorNames = new ArrayList<>();

                int i = 0;
                for (Actor actor : sortedActors) {
                    if (i < number) {
                        actorNames.add(actor.getName());
                        i++;
                    }
                }
                message = "Query result: " + actorNames;
            }
        } else if (criteria.equals("ratings")) {
            List<Video> filteredVideos = new ArrayList<>();

            if (objectType.equals("movies")) {
                filteredVideos = filterVideo(movies);

            } else if (objectType.equals("shows")) {
                filteredVideos = filterVideo(serials);
            }

            List<Video> sortedVideos = sorter.videosSortedByRating(filteredVideos);
            List<String> videoNames = new ArrayList<>();

            int i = 0;
            for (Video video : sortedVideos) {
                if (i < number && video.getRating() > 0) {
                    videoNames.add(video.getTitle());
                    i++;
                }
            }
            message = "Query result: " + videoNames;
        } else if (criteria.equals("favorite")) {
            List<Video> filteredVideos = new ArrayList<>();

            if (objectType.equals("movies")) {
                filteredVideos = filterVideo(movies);
            } else if (objectType.equals("shows")) {
                filteredVideos = filterVideo(serials);
            }
            List<Video> sortedVideos = sorter.videosSortedByFavorite(filteredVideos);
            List<String> videoNames = new ArrayList<>();

            int i = 0;
            for (Video video : sortedVideos) {
                if (i < number && video.getTimesWasAddedToFavorite() != 0) {
                    videoNames.add(video.getTitle());
                    i++;
                }
            }
            message = "Query result: " + videoNames;

        } else if (criteria.equals("longest")) {
            List<Video> filteredVideos = new ArrayList<>();

            if (objectType.equals("movies")) {
                filteredVideos = filterVideo(movies);
            } else if (objectType.equals("shows")) {
                filteredVideos = filterVideo(serials);
            }

            List<Video> sortedVideos = sorter.videosSortedByDuration(filteredVideos);
            List<String> videoNames = new ArrayList<>();

            int i = 0;
            for (Video video : sortedVideos) {
                if (i < number && video.getTotalDuration() != 0) {
                    videoNames.add(video.getTitle());
                    i++;
                }
            }
            message = "Query result: " + videoNames;
        } else if (criteria.equals("most_viewed")) {
            List<Video> filteredVideos = new ArrayList<>();

            if (objectType.equals("movies")) {
                filteredVideos = filterVideo(movies);
            } else if (objectType.equals("shows")) {
                filteredVideos = filterVideo(serials);
            }

            List<Video> sortedVideos = sorter.videosSortedByViews(filteredVideos);
            List<String> videoNames = new ArrayList<>();

            int i = 0;
            for (Video video : sortedVideos) {
                if (i < number && video.getViews() != 0) {
                    videoNames.add(video.getTitle());
                    i++;
                }
            }
            message = "Query result: " + videoNames;
        }

        return message;
    }
}
