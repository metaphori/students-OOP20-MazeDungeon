package model.common;

/**
 * A basic class that implements the concept of Vector in two dimensions.
 * 
 */
public class Vector2D implements java.io.Serializable {

        private static final long serialVersionUID = 4909854761347770884L;
        private final double x;
        private final double y;

        /**
         * @param x : the initial x of the vector.
         * @param y : the initial y of the vector.
         */
        public Vector2D(final double x, final double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * @param to : starting point of the vector
         * @param from : ending point of the vector
         */
        public Vector2D(final Point2D to, final Point2D from) {
            this.x = to.getX() - from.getX();
            this.y = to.getY() - from.getY();
        }

        /**
         * @param v : the vector to be added.
         * @return the resultant vector
         */
        public Vector2D sum(final Vector2D v) {
            return new Vector2D(x + v.getX(), y + v.getY());
        }

        /**
         * @return the module of the vector
         */
        public double module() {
            return (double) Math.sqrt(x * x + y * y);
        }

        /**
         * @param fact : the number to be multiplied.
         * @return  the resultant vector
         */
        public Vector2D mul(final double fact) {
            return new Vector2D(x * fact, y * fact);
        }

        /**
         * @return the normalized vector
         */
        public Vector2D getNormalized() {
            final double module = this.module();
            return new Vector2D(x / module, y / module);
        }

        /**
         * @return the y of the vector
         */
        public double getY() {
            return this.y;
        }

        /**
         * @return the x of the vector
         */
        public double getX() {
            return this.x;
        }
    }
