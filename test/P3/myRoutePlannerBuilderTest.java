package P3;

import P3.RoutePlanner.RoutePlanner;
import P3.RoutePlannerBuilder.myRoutePlannerBuilder;
import P3.Stop.Stop;
import P3.Stop.myStop;
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
        builder.build("./test/P3/transit.txt", 1200);
    }


} 
