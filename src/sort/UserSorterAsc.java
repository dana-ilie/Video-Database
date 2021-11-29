package sort;

import user.User;

import java.util.Comparator;

public class UserSorterAsc implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if(o1.getNumberOfRatings() - o2.getNumberOfRatings() != 0) {
            return o1.getNumberOfRatings() - o2.getNumberOfRatings();
        }

        return o1.getUsername().compareTo(o2.getUsername());
    }
}
