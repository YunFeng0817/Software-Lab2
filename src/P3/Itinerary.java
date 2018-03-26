package P3;

import java.util.LinkedList;
import java.util.List;

public class Itinerary {
    List<TripSegment> trip = new LinkedList<>();

    void add(TripSegment segment) {
        trip.add(0,segment);
    }

    int getStartTime() {
        return trip.get(1).getStart().getTime();
    }

    int getEndTime() {
        return trip.get(trip.size()-1).getEnd().getTime();
    }

    int getWaitTime() {
        return trip.stream().filter(item -> item instanceof WaitSegment).mapToInt(item -> item.getEnd().getTime() - item.getStart().getTime()).sum();
    }

    Stop getStartLocation() {
        return trip.get(trip.size() - 1).getStart().getLocation();
    }

    Stop getEndLocation() {
        return trip.get(0).getEnd().getLocation();
    }

    String getInstructions() {
        return trip.stream().map(Object::toString).reduce("", (pre, next) -> pre + next);
    }
}
