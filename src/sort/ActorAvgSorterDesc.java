package sort;

import actor.Actor;

import java.util.Comparator;

/**
 * Actors average comparator(descending order)
 */
public class ActorAvgSorterDesc implements Comparator<Actor> {
    /**
     * @param o1 first actor
     * @param o2 second actor
     * @return result of comparison
     */
    @Override
    public int compare(final Actor o1, final Actor o2) {
        if (Double.compare(o2.getAverage(), o1.getAverage()) != 0) {
            return Double.compare(o2.getAverage(), o1.getAverage());
        }

        return o2.getName().compareTo(o1.getName());
    }
}
