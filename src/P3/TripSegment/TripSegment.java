package P3.TripSegment;

import P3.Stop.StopEvent;

public interface TripSegment {
    boolean wait = true;

    StopEvent getStart();

    StopEvent getEnd();
}
