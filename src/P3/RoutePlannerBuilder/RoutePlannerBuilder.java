package P3.RoutePlannerBuilder;

import P3.RoutePlanner.RoutePlanner;

import java.io.IOException;

public interface RoutePlannerBuilder {
    /**
     * according to the file which has transit data, establish the route plan system
     *
     * @param filename     the file name which has transit data
     * @param maxWaitLimit the max wait limit time one rider wait at one bus stop
     * @return the object which can plan route
     * @throws IOException if the file IO wrong, will throw this exception
     */
    RoutePlanner build(String filename, int maxWaitLimit) throws IOException;
}