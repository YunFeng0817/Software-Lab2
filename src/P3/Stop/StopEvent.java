package P3.Stop;

/* abstract function
 * route-> the name of the route name
 * time -> the specific time ,one event occur,such as the bus arrive this stop
 * location -> the location of the bus stop
 */

/* rep invariant
 * the location can't be null
 */

import P3.Itinerary;

/* safety from rep exposure
 * all rep are private , no return mutable rep methods
 */
// this class is immutable
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
        return obj != null && (this == obj || ((StopEvent) obj).getRoute().equals(route) && ((StopEvent) obj).getTime() == time && ((StopEvent) obj).getLocation().equals(location));
    }
}
