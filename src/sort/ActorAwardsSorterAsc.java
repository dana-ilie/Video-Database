package sort;

import actor.Actor;

import java.util.Comparator;

public class ActorAwardsSorterAsc implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        if (o1.getNumberOfAwards() - o2.getNumberOfAwards() != 0) {
            return o1.getNumberOfAwards() - o2.getNumberOfAwards();
        }

        return o1.getName().compareTo(o2.getName());
    }
}
