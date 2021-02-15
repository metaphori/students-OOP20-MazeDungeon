package model.common;

public class Vector2D implements java.io.Serializable {

        private static final long serialVersionUID = 4909854761347770884L;
        private final int x;
        private final int y;
        /**
         * 
         * @param x
         * @param y
         */
        public Vector2D(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public Vector2D(final Point2D to, final Point2D from) {
            this.x = to.getX() - from.getX();
            this.y = to.getY() - from.getY();
        }

        public Vector2D sum(final Vector2D v){
            return new Vector2D(x + v.getX(), y + v.getY());
        }

        public double module() {
            return (double) Math.sqrt(x * x + y * y);
        }

        public Vector2D mul(int fact) {
            return new Vector2D(x * fact, y * fact);
        }

        public int getY() {
            return this.y;
        }

        public int getX() {
            return this.x;
        }

        public String toString() {
            return "Vector [" + x + "," + y + "]";
        }
    }
