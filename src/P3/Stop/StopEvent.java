package P3.Stop;

public class StopEvent {
    private final String route;
    private final int time;
    private final Stop location;

    public StopEvent(String route, Stop location, int time) {
        this.route = route;
        this.location = location;
        this.time = time;
    }

    public StopEvent(Stop location, int time) {
        this.route = "";
        this.location = location;
        this.time = time;
    }

    public String getRoute() {
        return route;
    }

    public int getTime() {
        return time;
    }

    public Stop getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || ((StopEvent) obj).getRoute().equals(route) && ((StopEvent) obj).getTime() == time && ((StopEvent) obj).getLocation().equals(location);
    }
}
