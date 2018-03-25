package P3;

import P1.graph.Graph;

import java.io.IOException;
import java.io.*;
import java.util.*;

public class myRoutePlannerBuilder implements RoutePlannerBuilder {

    @Override
    public RoutePlanner build(String filename, int maxWaitLimit) throws IOException {
        Set<Stop> stops = new HashSet<>();
        store data = new store();
        FileReader file = new FileReader(filename);
        BufferedReader content = new BufferedReader(file);
        String line;
        String[] words;
        Graph<StopEvent> graph = Graph.empty();
        while ((line = content.readLine()) != null) {
            words = line.split(",");
            String route = words[0];
            int lineNum = Integer.parseInt(words[1]);
            StopEvent buffer = null;
            for (int i = 0; i < lineNum; i++) {
                if ((line = content.readLine()) == null) {
                    throw new RuntimeException("the file form error,can't parse it");
                }
                words = line.split(",");
                try {
                    Stop stop = new myStop(words[0], Double.parseDouble(words[1]), Double.parseDouble(words[2]));
                    StopEvent stopEvent = new StopEvent(route, stop, Integer.parseInt(words[3]));
                    stops.add(stop);
                    graph.add(stopEvent);
                    if (buffer != null) {
                        graph.set(buffer, stopEvent, stopEvent.getTime() - buffer.getTime());
                    }
                    buffer = stopEvent;
                    data.setData(stop, route, Integer.parseInt(words[3]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("the line of the records has wrong form!");
                    e.printStackTrace();
                }
            }
        }
        Map<String, List<Integer>> buses;
        for (Stop stop : stops) {
            buses = data.getBuses(stop);
            for (Map.Entry<String, List<Integer>> entry : buses.entrySet()) {
                entry.setValue(entry.getValue());
                ListIterator<Integer> iterator = entry.getValue().listIterator();
                while (iterator.hasNext()) {
                    int bufferPre = iterator.next();
                    if (iterator.hasNext()) {
                        int bufferNext = iterator.next();
                        iterator.previous();
                        if (bufferNext - bufferPre <= maxWaitLimit) {
                            graph.set(new StopEvent(entry.getKey(), stop, bufferPre), new StopEvent(entry.getKey(), stop, bufferNext), bufferNext - bufferPre);
                        }
                    }
                }
            }
        }
        return new planner(graph, stops, data,maxWaitLimit);
    }
}
