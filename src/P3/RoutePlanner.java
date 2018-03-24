package P3;

import java.util.List;

public interface RoutePlanner {
    /**
     * find the stops if their names contain the search content
     *
     * @param search the String content user input
     * @return the list of stops which name contain the search content
     */
    List<Stop> findStopsBySubstring(String search);

    /**
     * @param src
     * @param dest
     * @param time
     * @return
     */
    Itinerary computeRoute(Stop src, Stop dest, int time);

}
