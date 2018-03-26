package P3;

import P3.RoutePlanner.RoutePlanner;
import P3.RoutePlanner.planner;
import P3.RoutePlannerBuilder.myRoutePlannerBuilder;
import P3.Stop.Stop;
import P3.Stop.StopEvent;
import P3.Stop.myStop;
import P3.TripSegment.BusSegment;
import P3.TripSegment.WaitSegment;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * planner Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>March 26, 2018</pre>
 */
public class plannerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: findStopsBySubstring(String search)
     */
    @Test
    public void testFindStopsBySubstring() throws Exception {
        myRoutePlannerBuilder builder = new myRoutePlannerBuilder();
        RoutePlanner planner;
        planner = builder.build("./test/P3/transit.txt", 10);
        Stop stop1 = new myStop("1", 2.0, 1.0);
        Stop stop2 = new myStop("2", 3.0, 4.0);
        Stop stop3 = new myStop("3", 1.0, 5.0);
        Stop stop4 = new myStop("4", 2.0, 6.0);
        Stop stop5 = new myStop("5", 4.0, 3.0);
        Stop stop6 = new myStop("6", 5.0, 6.0);
        assertEquals(new ArrayList<>(Arrays.asList(stop1, stop2, stop3, stop4, stop5, stop6)), planner.findStopsBySubstring(""));
        assertEquals(new ArrayList<>(Collections.singletonList(stop3)), planner.findStopsBySubstring("3"));
    }

    /**
     * Method: computeRoute(Stop src, Stop dest, int time)
     */
    @Test
    public void testComputeRoute() throws Exception {
        myRoutePlannerBuilder builder = new myRoutePlannerBuilder();
        RoutePlanner planner;
        planner = builder.build("./test/P3/transit.txt", 10);
        Itinerary result = new Itinerary();
        Stop stop1 = new myStop("1", 2.0, 1.0);
        Stop stop2 = new myStop("2", 3.0, 4.0);
        Stop stop3 = new myStop("3", 1.0, 5.0);
        Stop stop4 = new myStop("4", 2.0, 6.0);
        Stop stop5 = new myStop("5", 4.0, 3.0);
        Stop stop6 = new myStop("6", 5.0, 6.0);
        result.add(new BusSegment(new StopEvent("84", stop3, 70), new StopEvent(stop4, 100)));
        result.add(new BusSegment(new StopEvent("84", stop2, 60), new StopEvent(stop3, 70)));
        result.add(new BusSegment(new StopEvent("84", stop6, 31), new StopEvent(stop2, 60)));
        result.add(new WaitSegment(new StopEvent("63", stop6, 30), new StopEvent("84", stop6, 31)));
        result.add(new BusSegment(new StopEvent("63", stop1, 20), new StopEvent(stop6, 30)));
        result.add(new WaitSegment(new StopEvent(stop1, 10), new StopEvent("63", stop1, 20)));
        // test normal input situation
        assertEquals(result, planner.computeRoute(stop1, stop4, 10));
        // test if the wait time > maxWaitTime
        assertEquals("the rider can't ride a bus in max wait time", planner.computeRoute(stop1, stop4, 9).getInstructions());
    }

} 
