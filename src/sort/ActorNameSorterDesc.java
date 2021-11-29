package sort;

import actor.Actor;

import java.util.Comparator;

public class ActorNameSorterDesc implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
