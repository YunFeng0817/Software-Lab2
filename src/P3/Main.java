package P3;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        RoutePlannerBuilder routeBuilder = new myRoutePlannerBuilder();
        RoutePlanner planner = routeBuilder.build("./src/P3/largeData.txt", 1200);
        String choice = "";
        String content = "";
        while (!choice.equals("#")) {
            System.out.println("input the choice number to execute the specific instruction");
            System.out.println(">>>1.input the name you want to search, I will show the stops contain the string ");
            System.out.println(">>>2.input stop name now, the stop name at destination and time now ,I will return the instruction about how to get there");
            System.out.println(">>>if you want to exit the system,please input  #");
            Scanner in = new Scanner(System.in);
            choice = in.nextLine();
            if (choice.equals("1")) {
                System.out.println(">>>input the name you want to search, I will show the stops contain the string ");
                Scanner searchIn = new Scanner(System.in);
                content = searchIn.nextLine();
                for (Stop stop : planner.findStopsBySubstring(content))
                    System.out.println(stop.getName());
            } else if (choice.equals("2")) {
                System.out.println("input stop name now, the stop name at destination and time now ,I will return the instruction about how to get there");
                Scanner searchIn = new Scanner(System.in);
                content = searchIn.nextLine();
            } else if (!choice.equals("#")) {
                System.err.println("no such instruction");
            }
        }
    }
}
