package sort;

import actor.Actor;
import entertainment.Video;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Sorter {
    private final String sortType;

    public Sorter(final String sortType) {
        this.sortType = sortType;
    }

    /**
     * @param videos list of videos to be sorted
     * @return a list of videos sorted by rating
     */
    public List<Video> videosSortedByRating(final List<? extends Video> videos) {
        List<Video> sortedVideos = new ArrayList<>();
        for (Video video : videos) {
            sortedVideos.add(new Video(video));
        }

        if (sortType.equals("asc")) {
            sortedVideos.sort(new VideoRatingSorterAsc());
        } else {
            sortedVideos.sort(new VideoRatingSorterDesc());
        }

        return sortedVideos;
    }

    /**
     * @param users list of users to be sorted
     * @return a list of users sorted by number of ratings given
     */
    public ArrayList<User> usersSortedByRatings(final ArrayList<User> users) {
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

    /**
     * @param actors list of actors to be sorted
     * @return a list of actors sorted by average rating of videos they played in
     */
    public ArrayList<Actor> actorsSortedByAverage(final ArrayList<Actor> actors) {
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

    /**
     * @param actors list of actors to be sorted
     * @return a list of actors sorted by the number of awards they won
     */
    public ArrayList<Actor> actorsSortedByAwards(final ArrayList<Actor> actors) {
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

    /**
     * @param actors list of actors to be sorted
     * @return a list of actors sorted by their name
     */
    public ArrayList<Actor> actorsSortedByName(final ArrayList<Actor> actors) {
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

    /**
     * @param videos list of videos to be sorted
     * @return a list of videos sorted by the number of occurrences in users' favorite lists
     */
    public List<Video> videosSortedByFavorite(final List<? extends Video> videos) {
        List<Video> sortedVideos = new ArrayList<>();
        for (Video video : videos) {
            sortedVideos.add(new Video(video));
        }

        if (sortType.equals("asc")) {
            sortedVideos.sort(new VideoFavoriteSorterAsc());
        } else {
            sortedVideos.sort(new VideoFavoriteSorterDesc());
        }

        return sortedVideos;
    }

    /**
     * @param videos list of videos to be sorted
     * @return a list of videos sorted by their duration
     */
    public List<Video> videosSortedByDuration(final List<? extends Video> videos) {
        List<Video> sortedVideos = new ArrayList<>();
        for (Video video : videos) {
            sortedVideos.add(new Video(video));
        }

        if (sortType.equals("asc")) {
            sortedVideos.sort(new VideoDurationSorterAsc());
        } else {
            sortedVideos.sort(new VideoDurationSorterDesc());
        }

        return sortedVideos;
    }

    /**
     * @param videos list of videos to be sorted
     * @return a list of videos sorted by the number of views
     */
    public List<Video> videosSortedByViews(final List<? extends Video> videos) {
        List<Video> sortedVideos = new ArrayList<>();
        for (Video video : videos) {
            sortedVideos.add(new Video(video));
        }

        if (sortType.equals("asc")) {
            sortedVideos.sort(new VideoViewsSorterAsc());
        } else {
            sortedVideos.sort(new VideoViewsSorterDesc());
        }

        return sortedVideos;
    }

}
