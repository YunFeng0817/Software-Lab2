package P3;

import java.util.List;
import java.util.Map;
import java.util.Set;

import P1.graph.*;

public class planner implements RoutePlanner {
    private Graph<StopEvent> graph;
    private Set<Stop> stops;

    planner(Graph<StopEvent> graph, Set<Stop> stops) {
        this.graph = graph;
        this.stops = stops;
    }

    @Override
    public List<Stop> findStopsBySubstring(String search) {
        return null;
    }

    @Override
    public Itinerary computeRoute(Stop src, Stop dest, int time) {
        return null;
    }
}
