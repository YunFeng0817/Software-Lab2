package P3.TripSegment;

import P3.Stop.StopEvent;

public interface TripSegment {
    /**
     * get the start stop status(stop event) of this trip segment
     *
     * @return the start stop status(stop event) of this trip segment
     */
    StopEvent getStart();

    /**
     * get the end stop status(stop event) of this trip segment
     *
     * @return the end stop status(stop event) of this trip segment
     */
    StopEvent getEnd();
}
