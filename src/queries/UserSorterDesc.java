package queries;

import user.User;

import java.util.Comparator;

public class UserSorterDesc implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o2.getNumberOfRatings() - o1.getNumberOfRatings();
    }
}
