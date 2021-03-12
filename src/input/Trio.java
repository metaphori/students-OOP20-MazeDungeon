package input;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        result = prime * result + ((z == null) ? 0 : z.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trio other = (Trio) obj;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;
        if (y == null) {
            if (other.y != null)
                return false;
        } else if (!y.equals(other.y))
            return false;
        if (z == null) {
            if (other.z != null)
                return false;
        } else if (!z.equals(other.z))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TRIO [ " + this.x + "," + this.y + "," + this.z + "]";
    }

    /**
     * 
     * @param b
     */
    public void setY(final Y b) {
        this.y = b;

    }

}
