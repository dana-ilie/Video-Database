package sort;

import actor.Actor;

import java.util.Comparator;

/**
 * Actors average comparator(ascending order)
 */
public class ActorAvgSorterAsc implements Comparator<Actor> {
    /**
     * @param o1 first actor
     * @param o2 second actor
     * @return result of comparison
     */
    @Override
    public int compare(final Actor o1, final Actor o2) {

        if (Double.compare(o1.getAverage(), o2.getAverage()) != 0) {
            return Double.compare(o1.getAverage(), o2.getAverage());
        }

        return o1.getName().compareTo(o2.getName());
    }
}
