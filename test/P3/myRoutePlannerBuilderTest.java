package P3;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * myRoutePlannerBuilder Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>March 22, 2018</pre>
 */
public class myRoutePlannerBuilderTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: build(String filename, int maxWaitLimit)
     */
    @Test
    public void testBuild() throws Exception {
        myRoutePlannerBuilder builder = new myRoutePlannerBuilder();
        RoutePlanner planner;
        Stop stop1 = new myStop("1", 0,1.0);
        Stop stop2 = new myStop("5", 0,3.0);
        planner = builder.build("./test/P3/transit.txt", 1200);
        System.out.println(planner.computeRoute(stop1, stop2, 0).getInstructions());
    }


} 
