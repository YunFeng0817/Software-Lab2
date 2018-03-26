package P3.RoutePlannerBuilder;

import P3.RoutePlanner.RoutePlanner;

import java.io.IOException;

public interface RoutePlannerBuilder {
    RoutePlanner build(String filename, int maxWaitLimit) throws IOException;
}