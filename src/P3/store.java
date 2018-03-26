package P3;

import P3.Stop.Stop;
import P3.Stop.StopEvent;

import java.util.*;
/* abstract function
 * AF(data)->the map relation between stop and route status
 */

/* rep invariant
 * true
 */

/* safety from rep exposure
 * all rep are private , the method return rep only return the clone rep
 */
public class store {
    private final Map<Stop, List<StopEvent>> data = new HashMap<>();

    /**
     * add the stop location and the stop event to the data
     *
     * @param stop      the location of the stop
     * @param stopEvent one record of one stop event
     */
    public void setData(Stop stop, StopEvent stopEvent) {
        List<StopEvent> busBuffer;
        if ((busBuffer = data.get(stop)) != null)
            busBuffer.add(stopEvent);
        else
            data.put(stop, new ArrayList<>(Collections.singletonList(stopEvent)));
    }

    /**
     * return the stop events belong to the input stop
     *
     * @param stop the stop which need to search
     * @return the stop events belong to the input stop
     */
    public List<StopEvent> getBuses(Stop stop) {
        return new ArrayList<>(data.get(stop));
    }
}
