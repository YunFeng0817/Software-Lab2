package P3;

import java.util.*;

class store {
    Map<Stop, List<StopEvent>> data = new HashMap<>();

    void setData(Stop stop, StopEvent stopEvent) {
        List<StopEvent> busBuffer;
        if ((busBuffer = data.get(stop)) != null)
            busBuffer.add(stopEvent);
        else
            data.put(stop, new ArrayList<>(Collections.singletonList(stopEvent)));
    }

    List<StopEvent> getBuses(Stop stop) {
        return data.get(stop);
    }
}
