package queries;

import actor.Actor;

import java.util.Comparator;

public class ActorAvgSorterDesc implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        if (Double.compare(o2.getAverage(), o1.getAverage())!= 0) {
            return Double.compare(o2.getAverage(), o1.getAverage());
        }

        return o2.getName().compareTo(o1.getName());
    }
}
