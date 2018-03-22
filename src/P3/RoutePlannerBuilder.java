package P3;

import java.io.IOException;

public interface RoutePlannerBuilder {
    RoutePlanner build(String filename, int maxWaitLimit) throws IOException;
}