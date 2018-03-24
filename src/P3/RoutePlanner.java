package P3;

import java.util.List;

public interface RoutePlanner {
    List<Stop> findStopsBySubstring(String search);

    Itinerary computeRoute(Stop src, Stop dest, int time);

}
