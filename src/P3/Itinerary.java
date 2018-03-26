package P3;

import P3.Stop.Stop;
import P3.Stop.StopEvent;
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
     * @return the time the rider get on the bus,if the trip's size is 0(the rider can't get the destination),return -1
     */
    int getStartTime() {
        if (trip.size() != 0)
            return trip.get(1).getStart().getTime();
        return -1;
    }

    /**
     * get the time the rider get off the bus
     *
     * @return the time the rider get off the bus,if the trip's size is 0(the rider can't get the destination),return -1
     */
    int getEndTime() {
        if (trip.size() != 0)
            return trip.get(trip.size() - 1).getEnd().getTime();
        return -1;
    }

    /**
     * get the whole time the rider spend waiting the bus
     *
     * @return the whole time the rider spend waiting the bus,if the trip's size is 0(the rider can't get the destination),return -1
     */
    int getWaitTime() {
        if (trip.size() != 0)
            return trip.stream().filter(item -> item instanceof WaitSegment).mapToInt(item -> item.getEnd().getTime() - item.getStart().getTime()).sum();
        return -1;
    }

    /**
     * get the start location of this trip
     *
     * @return get the start location of this trip,if the trip's size is 0(the rider can't get the destination),return null
     */
    Stop getStartLocation() {
        return trip.stream().filter(item -> item instanceof BusSegment).map(TripSegment::getStart).map(StopEvent::getLocation).findFirst().orElse(null);
    }

    /**
     * get the end location of this trip
     *
     * @return the end location of this trip,if the trip's size is 0(the rider can't get the destination),return null
     */
    Stop getEndLocation() {
        if (trip.size() != 0)
            return trip.get(trip.size() - 1).getEnd().getLocation();
        return null;
    }

    /**
     * get instruction about how one rider can get to the destination as fast as possible
     *
     * @return instruction about how one rider can get to the destination as fast as possible
     */
    String getInstructions() {
        if (trip.size() != 0)
            return trip.stream().map(Object::toString).reduce("", (pre, next) -> pre + next);
        return "the rider can't ride a bus in max wait time";
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || ((Itinerary) obj).getInstructions().equals(this.getInstructions());
    }
}
