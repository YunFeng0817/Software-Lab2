package P3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myStop implements Stop {
    private final String name;
    private final double latitude, longitude;

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
}

