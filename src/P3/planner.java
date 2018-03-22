package P3;

import java.util.List;

import P1.graph.*;

public class planner implements RoutePlanner {
    private Graph<Stop> graph = Graph.empty();

    planner() {
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
