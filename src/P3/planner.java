package P3;

import java.util.*;
import java.util.regex.*;

import P1.graph.*;

public class planner implements RoutePlanner {
    private Graph<StopEvent> graph;
    private Set<Stop> stops;
    private store data = new store();
    private int maxWaitLimit;
    private Set<StopEvent> visited = new HashSet<>();
    private Map<StopEvent, StopEvent> trace = new HashMap<>();
    private Map<StopEvent, Integer> distance = new HashMap<>();
    private StopEvent startEvent;
    final int infinite = 0x00ffffff;

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
        startEvent = new StopEvent(src, time);
        for (Map.Entry<String, List<Integer>> entry : data.getBuses(src).entrySet()) {
            int latest = entry.getValue().stream().filter(item -> item - time <= maxWaitLimit && item - time >= 0).findFirst().orElse(-1);
            if (latest != -1) {
                StopEvent startBusEvent = new StopEvent(entry.getKey(), src, latest);
                graph.set(startEvent, startBusEvent, latest - time);
            }
        }
        graph.set(startEvent, startEvent, 0);
        dijkstra();
        Map.Entry<StopEvent, Integer> min = distance.entrySet().stream().filter(item -> item.getKey().getLocation().equals(dest)).min(Comparator.comparing(Map.Entry::getValue)).orElse(null);
        Itinerary trip = new Itinerary();
        if (min != null && min.getValue() != infinite) {
            StopEvent traceNode, buffer = null;
            while (!(traceNode = trace.get(min.getKey())).equals(startEvent)) {
                if (buffer != null) {
                    TripSegment segment;
                    if (buffer.getLocation().equals(traceNode.getLocation()))
                        segment = new WaitSegment(buffer, traceNode, buffer.getTime() - traceNode.getTime());
                    else
                        segment = new BusSegment(buffer, traceNode, buffer.getTime() - traceNode.getTime());
                    trip.add(segment);
                }
                buffer = traceNode;
            }
        }
        return trip;
    }

    private void dijkstra() {
        int sum;
        visited.add(startEvent);
        Map<StopEvent, Integer> targets = graph.targets(startEvent);
        Set<StopEvent> stops = graph.vertices();
        for (StopEvent stopEvent : stops) {
            distance.put(stopEvent, targets.keySet().contains(stopEvent) ? targets.get(stopEvent) : infinite);
            trace.put(stopEvent, stopEvent);
        }
        for (int i = 1; i <= stops.size(); i++) {
            Map.Entry<StopEvent, Integer> min = distance.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).orElse(null);
            if (min != null) {
                visited.add(min.getKey());
                targets = graph.targets(min.getKey());
                for (StopEvent stop : stops) {
                    if (!visited.contains(stop)) {
                        if (targets.keySet().contains(stop)) {
                            sum = distance.get(min.getKey()) + targets.get(stop);
                            if (sum < distance.get(stop)) {
                                distance.put(stop, sum);
                                trace.put(stop, min.getKey());
                            }
                        }
                    }
                }
            }
        }
    }
}
