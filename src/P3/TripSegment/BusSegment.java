package P3.TripSegment;

import P3.Stop.StopEvent;

/* abstract function
 * AF(start)->the start stop status(stop event) of this bus trip segment
 * AF(end) ->the end stop status(stop event) of this bus trip segment
 * AF(passTime) -> the time of this bus trip segment spend
 */

/* rep invariant
 * the start and the end rep can't be null
 */

/* safety from rep exposure
 * all rep are private , no return mutable rep methods
 */

// this class is immutable

public class BusSegment implements TripSegment {

    private final StopEvent start, end;
    private final int passTime;

    public BusSegment(StopEvent start, StopEvent end, int passTime) {
        this.start = start;
        this.end = end;
        this.passTime = passTime;
    }

    @Override
    public StopEvent getStart() {
        return this.start;
    }

    @Override
    public StopEvent getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "ride the bus " + start.getRoute() + " message:"
                + start.getLocation().toString() + ", at time:"
                + start.getTime() +
                " . through time : " + passTime + "s , to "
                + end.getLocation().toString() + " , at time : "
                + end.getTime() + "\n";
    }
}
