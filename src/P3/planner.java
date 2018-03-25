package P3;

import java.util.*;
import java.util.regex.*;

import P1.graph.*;

public class planner implements RoutePlanner {
    private Graph<StopEvent> graph;
    private Set<Stop> stops;
    private store data = new store();
    private int maxWaitLimit;

    planner(Graph<StopEvent> graph, Set<Stop> stops, store data, int maxWaitLimit) {
        this.graph = graph;
        this.stops = stops;
        this.data = data;
        this.maxWaitLimit = maxWaitLimit;
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
        // add  the user's location point to the graph ,and set the relative edge
        StopEvent startEvent = new StopEvent(src, time);
        for (Map.Entry<String, List<Integer>> entry : data.getBuses(src).entrySet()) {
            int latest = entry.getValue().stream().filter(item -> item - time <= maxWaitLimit && item - time >= 0).findFirst().orElse(-1);
            if (latest != -1) {
                StopEvent startBusEvent = new StopEvent(entry.getKey(), src, latest);
                graph.set(startEvent, startBusEvent, latest - time);
            }
        }


        return null;
    }

    private void dijkstra() {

    }
}
