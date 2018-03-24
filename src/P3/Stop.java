package P3;

/**
 * this interface means
 * the location of one real bus station
 * it contains rep: stop name,the latitude and the longitude
 */
public interface Stop {

    /**
     * get the name of this bus stop
     *
     * @return the name of this bus stop
     */
    String getName();

    /**
     * get the latitude value of this bus stop
     *
     * @return the latitude value of this bus stop
     */
    double getLatitude();

    /**
     * get the longitude value of this bus stop
     *
     * @return the longitude value of this bus stop
     */
    double getLongitude();
}
