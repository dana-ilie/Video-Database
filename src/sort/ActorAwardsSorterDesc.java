package sort;

import actor.Actor;

import java.util.Comparator;

public class ActorAwardsSorterDesc implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        if (o2.getNumberOfAwards() - o1.getNumberOfAwards() != 0) {
            return o2.getNumberOfAwards() - o1.getNumberOfAwards();
        }

        return o2.getName().compareTo(o1.getName());
    }
}
