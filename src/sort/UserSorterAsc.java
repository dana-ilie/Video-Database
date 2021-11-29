package sort;

import user.User;

import java.util.Comparator;

/**
 * Users number of rating comparator(ascending order)
 */
public class UserSorterAsc implements Comparator<User> {
    /**
     * @param o1 first user
     * @param o2 second user
     * @return result of comparison
     */
    @Override
    public int compare(final User o1, final User o2) {
        if (o1.getNumberOfRatings() - o2.getNumberOfRatings() != 0) {
            return o1.getNumberOfRatings() - o2.getNumberOfRatings();
        }

        return o1.getUsername().compareTo(o2.getUsername());
    }
}
