package input;
/**
 * 
 * A custom class to create an object consisting of three elements.
 *
 * @param <X>
 * @param <Y>
 * @param <Z>
 */
public class Trio<X, Y, Z> {

    private final X x;
    private  Y y;
    private final Z z;

    public Trio(final X x, final Y y, final Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * 
     * @return x
     */
    public X getX() {
        return this.x;
    }

    /**
     * 
     * @return y
     */
    public Y getY() {
        return this.y;
    }

    /**
     * 
     * @return z
     */
    public Z getZ() {
        return this.z;
    }

    /**
     * 
     * @param b
     */
    public void setY(final Y b) {
        this.y = b;

    }

    /**
     * @return the strings that represent the trio
     */
    @Override
    public String toString() {
        return "Trio [x=" + x + ", y=" + y + ", z=" + z + "]";
    }


}
