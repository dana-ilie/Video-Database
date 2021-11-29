package sort;

import actor.Actor;

import java.util.Comparator;

/**
 * Actors awards number comparator(descending order)
 */
public class ActorAwardsSorterDesc implements Comparator<Actor> {
    /**
     * @param o1 first actor
     * @param o2 second actor
     * @return result of comparison
     */
    @Override
    public int compare(final Actor o1, final Actor o2) {
        if (o2.getNumberOfAwards() - o1.getNumberOfAwards() != 0) {
            return o2.getNumberOfAwards() - o1.getNumberOfAwards();
        }

        return o2.getName().compareTo(o1.getName());
    }
}
