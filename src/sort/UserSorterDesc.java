package sort;

import user.User;

import java.util.Comparator;

/**
 *  * Users number of rating comparator(descending order)
 */
public class UserSorterDesc implements Comparator<User> {
    /**
     * @param o1 first user
     * @param o2 second user
     * @return result of comparison
     */
    @Override
    public int compare(final User o1, final User o2) {
        if (o2.getNumberOfRatings() - o1.getNumberOfRatings() != 0) {
            return o2.getNumberOfRatings() - o1.getNumberOfRatings();
        }

        return o2.getUsername().compareTo(o1.getUsername());
    }
}
