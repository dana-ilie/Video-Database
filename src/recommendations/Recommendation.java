package recommendations;

import entertainment.Movie;
import entertainment.Serial;
import entertainment.Video;
import sort.VideoRatingSorterAsc;
import user.User;

import java.util.*;

import static utils.Utils.stringToGenre;

public class Recommendation {
    private final String type;
    private final String username;
    private final String genre;

    public Recommendation(String type, String username, String genre) {
        this.type = type;
        this.username = username;
        this.genre = genre;
    }

    private String standardRecommendation(ArrayList<User> users,
                                          ArrayList<Movie> movies,
                                          ArrayList<Serial> serials) {
        String videoName = "standard";

        /*
         * find the user
         */
        User user = new User();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }

        for (Movie movie : movies) {
            /*
             * check if user has seen the movie
             */
            if (user.getHistory().get(movie.getTitle()) == null) {
                videoName = movie.getTitle();
                return videoName;
            }
        }

        for (Serial serial : serials) {
            /*
             * check if user has seen the serial
             */
            if (user.getHistory().get(serial.getTitle()) == null) {
                videoName = serial.getTitle();
                return videoName;
            }
        }

        return videoName;
    }

    private String bestUnseenRecommendation(ArrayList<User> users,
                                            ArrayList<Movie> movies,
                                            ArrayList<Serial> serials) {
        String videoName = "bestUnseen";
        User user = new User();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }

        List<Video> allVideos = new ArrayList<>();
        allVideos.addAll(movies);
        allVideos.addAll(serials);

        allVideos.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));

        for (Video video : allVideos) {
            /*
             * check if user has seen the video
             */
            if (user.getHistory().get(video.getTitle()) == null) {
                videoName = video.getTitle();
                break;
            }
        }


        return videoName;
    }

    private String popularRecommendation(ArrayList<User> users,
                                         ArrayList<Movie> movies,
                                         ArrayList<Serial> serials) {
        String videoName = "popular";
        Map<String, Integer> genreViews = new LinkedHashMap<>();
        User user = new User();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }

        /*
         * calculate popularity of all genres
         */
        for (Movie movie : movies) {
            for (String genre : movie.getGenres()) {
                genreViews.put(genre, movie.getViews());
            }
        }
        for (Serial serial : serials) {
            for (String genre : serial.getGenres()) {
                genreViews.merge(genre, serial.getViews(), Integer::sum);
            }
        }

        /*
         * sort genres by number of views
         */
        Map<String, Integer> sortedGenres = new LinkedHashMap<>();
        genreViews.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x-> sortedGenres.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> entry : sortedGenres.entrySet()) {
            /*
             * find a video with the most popular genre
             */
            for (Movie movie: movies) {
                for (String genre : movie.getGenres()) {
                    if (genre.equals(entry.getKey())) {
                        /*
                         * check if the user has not seen the video
                         */
                        if (user.getHistory().get(movie.getTitle()) == null) {
                            videoName = movie.getTitle();
                            return videoName;
                        }
                    }
                }
            }
            for (Serial serial : serials) {
                for (String genre : serial.getGenres()) {
                    if (genre.equals(entry.getKey())) {
                        if (user.getHistory().get(serial.getTitle()) == null) {
                            videoName = serial.getTitle();
                            return videoName;
                        }
                    }
                }
            }
        }

        return videoName;
    }

    private String favoriteRecommendation(ArrayList<User> users,
                                          ArrayList<Movie> movies,
                                          ArrayList<Serial> serials) {
        String videoName = "favorite";
        Map<String, Integer> favoriteVideos = new LinkedHashMap<>();
        User user = new User();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }

        /*
         * calculate how many times each video was added to favorites
         */

        List<Video> allVideos = new ArrayList<>();
        allVideos.addAll(movies);
        allVideos.addAll(serials);

        for (Video video : allVideos) {
            /*
             * check if video is in a favorite list
             */
            for (User u : users) {
                for (String userVideo : u.getFavoriteMovies()) {
                    if (video.getTitle().equals(userVideo)) {
                        favoriteVideos.merge(video.getTitle(), 1, Integer::sum);
                    }
                }
            }

        }

        Map<String, Integer> sortedVideos = new LinkedHashMap<>();
        favoriteVideos.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x-> sortedVideos.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> entry : sortedVideos.entrySet()) {
            if (user.getHistory().get(entry.getKey()) == null) {
                videoName = entry.getKey();
                return videoName;
            }
        }

        return videoName;
    }

    private List<Video> searchRecommendation(ArrayList<User> users,
                                             ArrayList<Movie> movies,
                                             ArrayList<Serial> serials) {
        List<Video> allVideos = new ArrayList<>();
        List<Video> unseenVideos = new ArrayList<>();
        User user = new User();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }

        allVideos.addAll(movies);
        allVideos.addAll(serials);

        for (Video video : allVideos) {
            /*
             * check genre
             */
            int hasGenre = 0;
            for (String videoGenre : video.getGenres()) {
                if (videoGenre.equals(genre)) {
                    hasGenre = 1;
                    break;
                }
            }
            if (hasGenre == 1) {
                /*
                 * check if the user has not seen the movie
                 */
                if (user.getHistory().get(video.getTitle()) == null) {
                    unseenVideos.add(new Video(video));
                }
            }
        }

        unseenVideos.sort(new VideoRatingSorterAsc());

        return unseenVideos;
    }

    public String recommend(ArrayList<User> users, ArrayList<Movie> movies,
                            ArrayList<Serial> serials) {
        String message = "";

        if (type.equals("standard")) {
            String videoName = standardRecommendation(users, movies, serials);

            if (videoName.equals("standard")) {
                message = "StandardRecommendation cannot be applied!";
            } else {
                message = "StandardRecommendation result: " + videoName;
            }
        } else if (type.equals("best_unseen")) {
            String videoName = bestUnseenRecommendation(users,movies,serials);

            if (videoName.equals("bestUnseen")) {
                message = "BestRatedUnseenRecommendation cannot be applied!";
            } else {
                message = "BestRatedUnseenRecommendation result: " + videoName;
            }
        } else {
            User user = new User();
            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    user = u;
                }
            }

            if (type.equals("popular")) {
                if (user.getSubscriptionType().equals("PREMIUM")) {
                    String videoName = popularRecommendation(users, movies, serials);

                    if (videoName.equals("popular")) {
                        message = "PopularRecommendation cannot be applied!";
                    } else {
                        message = "PopularRecommendation result: " + videoName;
                    }
                } else {
                    message = "PopularRecommendation cannot be applied!";
                }
            } else if (type.equals("favorite")) {
                if (user.getSubscriptionType().equals("PREMIUM")) {
                    String videoName = favoriteRecommendation(users, movies, serials);
                    if (videoName.equals("favorite")) {
                        message = "FavoriteRecommendation cannot be applied!";
                    } else {
                        message = "FavoriteRecommendation result: " + videoName;
                    }
                } else {
                    message = "FavoriteRecommendation cannot be applied!";
                }
            } else if(type.equals("search")) {
                if (user.getSubscriptionType().equals("PREMIUM")) {
                    if (stringToGenre(genre) != null) {
                        List<Video> videos = searchRecommendation(users, movies, serials);
                        List<String> videoNames = new ArrayList<>();

                        for (Video video : videos) {
                            videoNames.add(video.getTitle());
                        }

                        if (videoNames.size() != 0) {
                            message = "SearchRecommendation result: " + videoNames;
                        } else {
                            message = "SearchRecommendation cannot be applied!";
                        }
                    } else {
                        message = "SearchRecommendation cannot be applied!";
                    }

                } else {
                    message = "SearchRecommendation cannot be applied!";
                }
            }
        }


        return message;
    }
}
