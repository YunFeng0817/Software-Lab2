package P3.TripSegment;

import P3.Stop.StopEvent;

/* abstract function
 * AF(start)->the start stop status(stop event) of this wait trip segment
 * AF(end) ->the end stop status(stop event) of this wait trip segment
 * AF(passTime) -> the time of this  wait trip segment spend
 */

/* rep invariant
 * the start and the end rep can't be null
 */

/* safety from rep exposure
 * all rep are private , no return mutable rep methods
 */

// this class is immutable

public class WaitSegment implements TripSegment {
    private final StopEvent start, end;

    public WaitSegment(StopEvent start, StopEvent end) {
        this.start = start;
        this.end = end;
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
        return "wait at stop : " + start.getLocation().getName() + " from " + start.getTime() + "s , wait " + (end.getTime() - start.getTime()) + "s " + "\n";
    }
}
