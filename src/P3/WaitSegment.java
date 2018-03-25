package P3;

public class WaitSegment implements TripSegment {
    private final StopEvent start, end;
    private final int passTime;

    WaitSegment(StopEvent start, StopEvent end, int passTime) {
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
        return "wait at stop : " + start.getLocation().getName() + " from " + start.getTime() + "s , wait " + passTime + "s ,";
    }
}
