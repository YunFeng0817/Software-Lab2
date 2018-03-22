package P3;

public class Itinerary {
    int getStartTime() {
        return 0;
    }

    int getEndTime() {
        return 0;
    }

    int getWaitTime() {
        return 0;
    }

    Stop getStartLocation() {
        throw new RuntimeException("Implemented me!");
    }

    Stop getEndLocation() {
        throw new RuntimeException("Implemented me!");
    }

    String getInstructions() {
        return "";
    }
}
