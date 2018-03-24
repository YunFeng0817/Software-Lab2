package P3;

import P3.StopEvent;

public interface TripSegment {
    boolean wait = true;

    StopEvent getStart();

    StopEvent getEnd();
}
