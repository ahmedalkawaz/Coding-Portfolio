import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Collisions {

	private static boolean moveUp(Point a, Point b) {
		return (a.getY() > b.getY());
	}

	private static boolean moveDown(Point a, Point b) {
		return a.getY() < b.getY();
	}

	private static boolean moveRight(Point a, Point b) {
		return a.getX() < b.getX();
	}

	private static boolean moveLeft(Point a, Point b) {
		return a.getX() > b.getX();
	}

	private static boolean within(int aOne, int bOne, int aTwo, int bTwo) { // Checks if an object is within another
		if ((aOne > bOne && aOne < bTwo) || (aTwo > bOne && aTwo < bTwo)) {
			return true;
		}
		if (aOne == bOne && aTwo == bTwo) {
			return true;
		}
		return false;

	}

	public static boolean contact(Rectangle2D.Double a, Rectangle2D.Double b) { // checks if an object is currently making contact with
																// another
		int[] aBounds = { (int) a.getMinX(), (int) a.getMaxX(), (int) a.getMinY(), (int) a.getMaxY() };
		int[] bBounds = { (int) b.getMinX(), (int) b.getMaxX(), (int) b.getMinY(), (int) b.getMaxY() };

		if (aBounds[0] == bBounds[1] || aBounds[1] == bBounds[0]) {
			if (within(aBounds[2], bBounds[2], aBounds[3], bBounds[3])) {
				return true;
			}
		}
		if (aBounds[2] == bBounds[3] || aBounds[3] == bBounds[2]) {
			if (within(aBounds[0], bBounds[0], aBounds[1], bBounds[1])) {
				return true;
			}
		}
		return false;
	}

	public static boolean contactDown(Rectangle2D.Double a, Rectangle2D.Double b) {
		int[] aBounds = { (int) a.getMinX(), (int) a.getMaxX(), (int) a.getMinY(), (int) a.getMaxY() };
		int[] bBounds = { (int) b.getMinX(), (int) b.getMaxX(), (int) b.getMinY(), (int) b.getMaxY() };
		if (aBounds[3] == bBounds[2]) {
			if (within(aBounds[0], bBounds[0], aBounds[1], bBounds[1])) {
				return true;
			}
		}
		return false;

	}

	public static boolean contactUp(Rectangle2D.Double a, Rectangle2D.Double b) {
		int[] aBounds = { (int) a.getMinX(), (int) a.getMaxX(), (int) a.getMinY(), (int) a.getMaxY() };
		int[] bBounds = { (int) b.getMinX(), (int) b.getMaxX(), (int) b.getMinY(), (int) b.getMaxY() };
		if (aBounds[2] == bBounds[3]) {
			if (within(aBounds[0], bBounds[0], aBounds[1], bBounds[1])) {
				return true;
			}
		}
		return false;
	}

	public static boolean contactLeft(Rectangle2D.Double a, Rectangle2D.Double b) {
		int[] aBounds = { (int) a.getMinX(), (int) a.getMaxX(), (int) a.getMinY(), (int) a.getMaxY() };
		int[] bBounds = { (int) b.getMinX(), (int) b.getMaxX(), (int) b.getMinY(), (int) b.getMaxY() };
		if (aBounds[0] == bBounds[1]) {
			if (within(aBounds[2], bBounds[2], aBounds[3], bBounds[3])) {
				return true;
			}
		}
		return false;
	}

	public static boolean contactRight(Rectangle2D.Double a, Rectangle2D.Double b) {
		int[] aBounds = { (int) a.getMinX(), (int) a.getMaxX(), (int) a.getMinY(), (int) a.getMaxY() };
		int[] bBounds = { (int) b.getMinX(), (int) b.getMaxX(), (int) b.getMinY(), (int) b.getMaxY() };
		if (aBounds[1] == bBounds[0]) {
			if (within(aBounds[2], bBounds[2], aBounds[3], bBounds[3])) {
				return true;
			}
		}
		return false;
	}


}