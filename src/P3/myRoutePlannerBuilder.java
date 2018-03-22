package P3;

import java.io.IOException;
import java.io.*;

public class myRoutePlannerBuilder implements RoutePlannerBuilder {
    private RoutePlanner planner = RoutePlanner.empty();

    @Override
    public RoutePlanner build(String filename, int maxWaitLimit) throws IOException {
        RoutePlanner planner = RoutePlanner();
        FileReader file = new FileReader(filename);
        BufferedReader content = new BufferedReader(file);
        String line;
        String[] words;
        while ((line = content.readLine()) != null) {
            words = line.split(",");
            String name = words[0];
            int lineNum = Integer.parseInt(words[1]);
            for (int i = 0; i < lineNum; i++) {
                if ((line = content.readLine()) == null) {
                    throw new RuntimeException("the file form error,can't parse it");
                }
                words = line.split(",");

            }
            Stop stop = new myStop("")
        }
    }
}
