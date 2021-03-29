package viewutilities;

/**
 * A basic class that realize Pair with generics.
 *
 * @param <X> The first coordinate of the Pair.
 * @param <Y> The second coordinate of the Pair.
 */
public class Pair<X, Y> {
    private final X x;
    private final Y y;

    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the value of the x.
     */
    public X getX() {
        return x;
    }

    /**
     * @return the value of the y.
     */
    public Y getY() {
        return y;
    }
}
