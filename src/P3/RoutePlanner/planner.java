package P3.RoutePlanner;

import java.util.*;
import java.util.regex.*;

import P1.graph.*;
import P3.*;
import P3.Stop.Stop;
import P3.Stop.StopEvent;
import P3.TripSegment.*;

/* abstract function
 * graph -> one map which store the stop status(stop event) ,and the relation between stop status(stop event)
 * stops -> the set of all bus stop
 * data-> the map relation between stop and route status
 * distance -> the map between stop status(stop event) to the  using time from the start stop status(stop event) to the rest stop status(stop event)
 * trace -> contain the fasted route from the start stopEvent to every other stopEvent
 */

/* rep invariant
 * true
 */

/* safety from rep exposure
 * all rep are private , no return rep methods
 */

public class planner implements RoutePlanner {
    private Graph<StopEvent> graph;
    private Set<Stop> stops;
    private store data = new store();
    private int maxWaitLimit;
    private Map<StopEvent, StopEvent> trace;
    private Map<StopEvent, Integer> distance;
    private StopEvent startEvent;
    private final int infinite = 0x00ffffff;

    public planner(Graph<StopEvent> graph, Set<Stop> stops, store data, int maxWaitLimit) {
        this.graph = graph;
        this.stops = stops;
        this.data = data;
        this.maxWaitLimit = maxWaitLimit;
    }

    @Override
    public List<Stop> findStopsBySubstring(String search) {
        List<Stop> foundStops = new ArrayList<>();
        // case insensitive
        Pattern rule = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);
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
        // search the latest stopEvent point to be  the searching start point
        startEvent = data.getBuses(src).stream().filter(item -> item.getTime() - time <= maxWaitLimit && item.getTime() - time >= 0).min(Comparator.comparingInt(StopEvent::getTime)).orElse(null);
        if (startEvent == null) {
            System.out.println("the rider can't ride a bus in max wait time (" + maxWaitLimit + "s)");
            return new Itinerary();
        }

        dijkstra();

        /*
         * generate the trip segment according to the dijkstra result
         */
        // the blow long line is to get the least time using stopEvent from destination stop
        Map.Entry<StopEvent, Integer> min = distance.entrySet().stream().filter(item -> item.getKey().getLocation().equals(dest)).min(Comparator.comparing(Map.Entry::getValue)).orElse(null);
        Itinerary trip = new Itinerary();
        if (min != null && min.getValue() != infinite) {
            StopEvent traceNode = min.getKey(), buffer = min.getKey();
            do {
                traceNode = trace.get(traceNode);
                if (buffer != null) {
                    if (buffer.equals(traceNode)) {
                        System.out.println("the rider can't ride a bus");
                        System.exit(0);
                    }
                    TripSegment segment;
                    if (buffer.getLocation().equals(traceNode.getLocation()))
                        segment = new WaitSegment(traceNode, buffer);
                    else
                        segment = new BusSegment(traceNode, buffer);
                    trip.add(segment);
                }
                buffer = traceNode;
            } while (!traceNode.equals(startEvent));
            trip.add(new WaitSegment(new StopEvent(src, time), startEvent));
        } else {
            System.out.println("the rider can't ride a bus to the destination within the max wait time" + maxWaitLimit + "s ");
        }
        return trip;
    }

    private void dijkstra() {
        int sum;
        Set<StopEvent> visited = new HashSet<>();
        trace = new HashMap<>();
        distance = new HashMap<>();
        visited.add(startEvent);
        Map<StopEvent, Integer> targets = graph.targets(startEvent);
        Set<StopEvent> stops = graph.vertices();
        for (StopEvent stopEvent : stops) {
            distance.put(stopEvent, targets.keySet().contains(stopEvent) ? targets.get(stopEvent) : infinite);
            trace.put(stopEvent, startEvent);
        }
        distance.put(startEvent, 0);
        for (int i = 1; i <= stops.size(); i++) {
            Map.Entry<StopEvent, Integer> min = distance.entrySet().stream().filter(item -> !visited.contains(item.getKey())).min(Comparator.comparing(Map.Entry::getValue)).orElse(null);
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
