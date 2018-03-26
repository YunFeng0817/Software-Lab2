package P3;

import P3.RoutePlanner.RoutePlanner;
import P3.RoutePlannerBuilder.myRoutePlannerBuilder;
import P3.Stop.Stop;
import P3.Stop.myStop;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Itinerary Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>March 26, 2018</pre>
 */
public class ItineraryTest {
    myRoutePlannerBuilder builder = new myRoutePlannerBuilder();
    RoutePlanner planner;
    Itinerary itinerary;
    Stop stop1 = new myStop("1", 2.0, 1.0);
    Stop stop2 = new myStop("2", 3.0, 4.0);
    Stop stop3 = new myStop("3", 1.0, 5.0);
    Stop stop4 = new myStop("4", 2.0, 6.0);
    Stop stop5 = new myStop("5", 4.0, 3.0);
    Stop stop6 = new myStop("6", 5.0, 6.0);

    @Before
    public void before() throws Exception {
        planner = builder.build("./test/P3/transit.txt", 10);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getStartTime()
     */
    @Test
    public void testGetStartTime() throws Exception {
        // normal case
        itinerary = planner.computeRoute(stop1, stop4, 10);
        assertEquals(20, itinerary.getStartTime());
        itinerary = planner.computeRoute(stop2, stop6, 55);
        assertEquals(60, itinerary.getStartTime());
        // the rider can't ride a bus in max wait time case
        itinerary = planner.computeRoute(stop2, stop6, 10);
        assertEquals(-1, itinerary.getStartTime());
    }

    /**
     * Method: getEndTime()
     */
    @Test
    public void testGetEndTime() throws Exception {
        // normal case
        itinerary = planner.computeRoute(stop1, stop4, 10);
        assertEquals(100, itinerary.getEndTime());
        itinerary = planner.computeRoute(stop2, stop6, 55);
        assertEquals(80, itinerary.getEndTime());
        // the rider can't ride a bus in max wait time case
        itinerary = planner.computeRoute(stop2, stop6, 10);
        assertEquals(-1, itinerary.getEndTime());
    }

    /**
     * Method: getWaitTime()
     */
    @Test
    public void testGetWaitTime() throws Exception {
        // normal case
        itinerary = planner.computeRoute(stop1, stop4, 10);
        assertEquals(11, itinerary.getWaitTime());
        itinerary = planner.computeRoute(stop2, stop6, 55);
        assertEquals(10, itinerary.getWaitTime());
        // the rider can't ride a bus in max wait time case
        itinerary = planner.computeRoute(stop2, stop6, 10);
        assertEquals(-1, itinerary.getWaitTime());
    }

    /**
     * Method: getStartLocation()
     */
    @Test
    public void testGetStartLocation() throws Exception {
        // normal case
        itinerary = planner.computeRoute(stop1, stop4, 10);
        assertEquals(stop1, itinerary.getStartLocation());
        itinerary = planner.computeRoute(stop2, stop6, 55);
        assertEquals(stop2, itinerary.getStartLocation());
        // the rider can't ride a bus in max wait time case
        itinerary = planner.computeRoute(stop2, stop6, 10);
        assertEquals(null, itinerary.getStartLocation());
    }

    /**
     * Method: getEndLocation()
     */
    @Test
    public void testGetEndLocation() throws Exception {
        // normal case
        itinerary = planner.computeRoute(stop1, stop4, 10);
        assertEquals(stop4, itinerary.getEndLocation());
        itinerary = planner.computeRoute(stop2, stop6, 55);
        assertEquals(stop6, itinerary.getEndLocation());
        // the rider can't ride a bus in max wait time case
        itinerary = planner.computeRoute(stop2, stop6, 10);
        assertEquals(null, itinerary.getEndLocation());
    }
} 
