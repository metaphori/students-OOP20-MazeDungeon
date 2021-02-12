package core.common;

public class Point2D implements java.io.Serializable {


    private static final long serialVersionUID = 4674087395785183468L;
    private final int x;
    private final int y;

    public Point2D(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D sum(final Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }

    public Vector2D sub(final Point2D v) {
        return new Vector2D(this.x - v.getX(), this.y - v.getY());
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return "Point2D[" + this.x + "," + this.y + "]";
    }
}
