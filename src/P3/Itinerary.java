package P3;

import P3.Stop.Stop;
import P3.TripSegment.*;

import java.util.LinkedList;
import java.util.List;

/* abstract function
 * AF(trip)->the whole segment of the rider's trip ,contain the wait segment and the bus segment
 */

/* rep invariant
 * true
 */

/* safety from rep exposure
 * all rep are private , no return mutable rep methods
 */

// this class is mutable
public class Itinerary {
    private final List<TripSegment> trip = new LinkedList<>();

    public void add(TripSegment segment) {
        trip.add(0, segment);
    }

    /**
     * get the time the rider get on the bus
     *
     * @return the time the rider get on the bus
     */
    int getStartTime() {
        return trip.get(1).getStart().getTime();
    }

    /**
     * get the time the rider get off the bus
     *
     * @return the time the rider get off the bus
     */
    int getEndTime() {
        return trip.get(trip.size() - 1).getEnd().getTime();
    }

    /**
     * get the whole time the rider spend waiting the bus
     *
     * @return the whole time the rider spend waiting the bus
     */
    int getWaitTime() {
        return trip.stream().filter(item -> item instanceof WaitSegment).mapToInt(item -> item.getEnd().getTime() - item.getStart().getTime()).sum();
    }

    /**
     * get the start location of this trip
     *
     * @return get the start location of this trip
     */
    Stop getStartLocation() {
        return trip.get(trip.size() - 1).getStart().getLocation();
    }

    /**
     * get the end location of this trip
     *
     * @return the end location of this trip
     */
    Stop getEndLocation() {
        return trip.get(0).getEnd().getLocation();
    }

    /**
     * get instruction about how one rider can get to the destination as fast as possible
     *
     * @return instruction about how one rider can get to the destination as fast as possible
     */
    String getInstructions() {
        return trip.stream().map(Object::toString).reduce("", (pre, next) -> pre + next);
    }
}
