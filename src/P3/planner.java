package P3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.*;

import P1.graph.*;

public class planner implements RoutePlanner {
    private Graph<StopEvent> graph;
    private Set<Stop> stops;
    private store data = new store();

    planner(Graph<StopEvent> graph, Set<Stop> stops, store data) {
        this.graph = graph;
        this.stops = stops;
        this.data = data;
    }

    @Override
    public List<Stop> findStopsBySubstring(String search) {
        List<Stop> foundStops = new ArrayList<>();
        Pattern rule = Pattern.compile("*" + search + "*");
        Matcher matcher;
        for (Stop item : stops) {
            matcher = rule.matcher(item.getName());
            if (matcher.find())
                foundStops.add(item);
        }
        return foundStops;
    }

    @Override
    public Itinerary computeRoute(Stop src, Stop dest, int time) {
        return null;
    }
}
