package queries;

import actor.Actor;

import java.util.Comparator;

public class ActorAvgSorterAsc implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {

        if (Double.compare(o1.getAverage(), o2.getAverage()) != 0) {
            return Double.compare(o1.getAverage(), o2.getAverage());
        }

        return o1.getName().compareTo(o2.getName());
    }
}
