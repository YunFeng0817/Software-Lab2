package P3.RoutePlannerBuilder;

import P1.graph.Graph;
import P3.*;
import P3.RoutePlanner.RoutePlanner;
import P3.RoutePlanner.planner;
import P3.Stop.Stop;
import P3.Stop.StopEvent;
import P3.Stop.myStop;

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
                    data.setData(stop, stopEvent);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("the line of the records has wrong form!");
                    e.printStackTrace();
                }
            }
        }
        List<StopEvent> buses;
        for (Stop stop : stops) {
            buses = data.getBuses(stop);
            buses.sort(Comparator.comparingInt(StopEvent::getTime));
            ListIterator<StopEvent> iterator = buses.listIterator();
            while (iterator.hasNext()) {
                StopEvent bufferPre = iterator.next();
                if (iterator.hasNext()) {
                    StopEvent bufferNext = iterator.next();
                    iterator.previous();
                    if (bufferNext.getTime() - bufferPre.getTime() <= maxWaitLimit) {
                        graph.set(bufferPre, bufferNext, bufferNext.getTime() - bufferPre.getTime());
                    }
                }
            }
        }
        return new planner(graph, stops, data, maxWaitLimit);
    }
}
