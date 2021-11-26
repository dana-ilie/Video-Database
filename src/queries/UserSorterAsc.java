package queries;

import user.User;

import java.util.Comparator;

public class UserSorterAsc implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getNumberOfRatings() - o2.getNumberOfRatings();
    }
}
