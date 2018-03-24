package P3;

public class StopEvent {
    private final String route;
    private final int time;
    private final Stop location;

    StopEvent(String route, Stop location, int time) {
        this.route = route;
        this.location = location;
        this.time = time;
    }

    String getRoute() {
        return route;
    }

    int getTime() {
        return time;
    }

    Stop getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || ((StopEvent) obj).getRoute().equals(route) && ((StopEvent) obj).getTime() == time && ((StopEvent) obj).getLocation().equals(location);
    }
}
