package P3.RoutePlanner;

import P3.Itinerary;
import P3.Stop.Stop;

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
     * according to the source stop ,the destination stop ,the departure time, return the trip segment ,to make
     * rider spend the least time
     *
     * @param src  the source stop the rider departure
     * @param dest the destiny stop the rider want to get
     * @param time the rider's departure time
     * @return the trip segment that can guide the rider to spend least time to get destination
     */
    Itinerary computeRoute(Stop src, Stop dest, int time);

}
