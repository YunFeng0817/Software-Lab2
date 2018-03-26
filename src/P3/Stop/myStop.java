package P3.Stop;

/* abstract function
 * AF(name)->the name of the bus stop
 * AF(latitude) -> the latitude value of the stop
 * AF(longitude) -> the longitude of the stop
 */

/* rep invariant
 * the name  can't be null
 */

/* safety from rep exposure
 * all rep are private , no return mutable rep methods
 */

/**
 * one implement of the interface Stop
 */
// this class is immutable
public class myStop implements Stop {
    private final String name;
    private final double latitude, longitude;

    /**
     * Constructor
     *
     * @param name      the name of this bus stop
     * @param latitude  the latitude value of this bus stop
     * @param longitude the longitude value of this bus stop
     */
    public myStop(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * if the rep in the instance are the same,the equals() should return true
     *
     * @param obj any one of object
     * @return if two object are point to the same instance or the rep in the instance are the same,return true, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof Stop && name.equals(((Stop) obj).getName());
    }

    @Override
    public String toString() {
        return "stop name: " + name + " ,Latitude:" + longitude + " latitude: " + latitude + " ";
    }
}

