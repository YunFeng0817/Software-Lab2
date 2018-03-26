package P3;

import P3.Stop.Stop;
import P3.Stop.StopEvent;

import java.util.*;

public class store {
    Map<Stop, List<StopEvent>> data = new HashMap<>();

    public void setData(Stop stop, StopEvent stopEvent) {
        List<StopEvent> busBuffer;
        if ((busBuffer = data.get(stop)) != null)
            busBuffer.add(stopEvent);
        else
            data.put(stop, new ArrayList<>(Collections.singletonList(stopEvent)));
    }

    public List<StopEvent> getBuses(Stop stop) {
        return data.get(stop);
    }
}
