package sort;

import actor.Actor;

import java.util.Comparator;

/**
 * Actors awards number comparator(ascending order)
 */
public class ActorAwardsSorterAsc implements Comparator<Actor> {
    /**
     * @param o1 first actor
     * @param o2 second actor
     * @return result of comparison
     */
    @Override
    public int compare(final Actor o1, final Actor o2) {
        if (o1.getNumberOfAwards() - o2.getNumberOfAwards() != 0) {
            return o1.getNumberOfAwards() - o2.getNumberOfAwards();
        }

        return o1.getName().compareTo(o2.getName());
    }
}
