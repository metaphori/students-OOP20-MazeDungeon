package test;

import model.common.BoundingBox;
import model.common.Point2D;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBoundingBox {

    private static final double X = 0;
    private static final double Y = 0;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    @org.junit.Test
    public void testIntersection() {
        final BoundingBox b1 = new BoundingBox(new Point2D(X, Y), WIDTH, HEIGHT);
        assertTrue(b1.intersectWith(b1));

        final BoundingBox b2 = new BoundingBox(new Point2D(X + WIDTH / 2, Y + WIDTH / 2), WIDTH, HEIGHT);
        assertTrue(b1.intersectWith(b2));
        assertTrue(b2.intersectWith(b1));

        final BoundingBox b3 = new BoundingBox(new Point2D(b1.getBRCorner().getX() + 1, b1.getBRCorner().getY() + 1), WIDTH, HEIGHT);
        assertFalse(b3.intersectWith(b1));
        assertFalse(b1.intersectWith(b3));
        assertTrue(b3.intersectWith(b2));
        assertTrue(b2.intersectWith(b3));
    }

}
