package P3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myStop implements Stop {
    private String name;
    private double latitude, longitude;
    private Map<String, List<Double>> routes = new HashMap<>();

    myStop(String name, double latitude, double lontitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = lontitude;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public boolean setRoute(String routeName, double time) {

        return true;
    }
}

