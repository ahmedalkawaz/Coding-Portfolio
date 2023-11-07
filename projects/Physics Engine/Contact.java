import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact {
	ArrayList<Thing> before;
	ArrayList<Point> after = new ArrayList<Point>();
	private int factor = 100;

	public Contact(ArrayList<Thing> b) {
		before = b;
		for (int i = 0; i < b.size(); i++) {
			Thing temp = b.get(i);
			after.add(new Point((int) ((temp.getX() + temp.getxVelocity())),
					(int) ((temp.getY() + temp.getyVelocity()))));
		}
	}

	public void contactDetector() {
		double interval = 1.0 / factor;
		HashMap<Thing, Thing> map;
		boolean hit = false;

		for (int i = 0; i < factor; i++) {
			map = new HashMap<>();

			for (int j = 0; j < before.size(); j++) {

				Thing temp = before.get(j);
				// if (!temp.isDragEvent()) {
				double ixv = temp.getxVelocity() * interval;
				double iyv = temp.getyVelocity() * interval;
				temp.shadowLoc(temp.getArea().getX() + ixv, temp.getArea().getY() + iyv);
				// }
			}

			for (int j = 0; j < before.size(); j++) {
				for (int k = 0; k < before.size(); k++) {
					Thing temp1 = before.get(j);
					Thing temp2 = before.get(k);
					if (j != k && !(temp1.isGround() && temp2.isGround())
							&& overlap(temp1.getArea(), temp2.getArea())) {
						if (!map.containsKey(before.get(j)) || !map.get(before.get(j)).equals(before.get(k))) {
							if (!map.containsKey(before.get(k)) || !map.get(before.get(k)).equals(before.get(j))) {
								// if (!temp1.isDragEvent() && !temp2.isDragEvent()) {
								map.put(before.get(j), before.get(k));
								hit = true;
								// }
							}
						}

					}
				}
			}
			if (hit) {
				for (int j = 0; j < before.size(); j++) {
					Thing temp = before.get(j);
					double ixv = temp.getxVelocity() * interval;
					double iyv = temp.getyVelocity() * interval;

					temp.shadowLoc(temp.getArea().getX() - ixv, temp.getArea().getY() - iyv);

				}
				i--;
			}
			for (Map.Entry<Thing, Thing> entry : map.entrySet()) {
				if (isOnXA(entry.getKey(), entry.getValue(), i * interval)) {
					momentumAndForce.xMomentumChange(entry.getKey(), entry.getValue());
				} else {
					momentumAndForce.yMomentumChange(entry.getKey(), entry.getValue());
				}

			}
			hit = false;

		}
	}

	public boolean isOnXA(Thing a, Thing b, double t) {

		if (a.getxVelocity() == 0 && a.getyVelocity() == 0) {
			Thing temp = a;
			a = b;
			b = temp;
		}

		HashMap<Double, Boolean> p = new HashMap<>();
		Rectangle2D.Double ba = b.getArea();
		Rectangle2D.Double aa = a.getArea();
		double slopeAX = a.getxVelocity();
		double slopeBX = b.getxVelocity();

		double slopeAY = a.getyVelocity();
		double slopeBY = b.getyVelocity();

		double rightB = ba.getMaxX();
		double leftB = ba.getMinX();
		double upB = ba.getMinY();
		double downB = ba.getMaxY();

		double rightA = aa.getMaxX();
		double leftA = aa.getMinX();
		double upA = aa.getMinY();
		double downA = aa.getMaxY();

		if (slopeAX - slopeBX != 0) {
			directionalHelper(a, b, slopeAX, slopeBX, rightB, leftA, t, slopeBY, slopeAY, upA, upB, ba, aa, p);
			directionalHelper(a, b, slopeAX, slopeBX, leftB, rightA, t, slopeBY, slopeAY, upA, upB, ba, aa, p);
		}
		if (slopeAY - slopeBY != 0) {
			directionalHelperY(a, b, slopeAX, slopeBX, leftB, downB, leftA, t, slopeBY, slopeAY, upA, ba, aa, p);
			directionalHelperY(a, b, slopeAX, slopeBX, leftB, upB, leftA, t, slopeBY, slopeAY, downA, ba, aa, p);
		}

		double ld = 0;
		boolean first = true;
		for (Map.Entry<Double, Boolean> e : p.entrySet()) {
			if (first) {
				ld = e.getKey();
				first = false;
			}
			if (e.getKey() < ld) {
				ld = e.getKey();
			}

		}
		try {
		return p.get(ld);
		}
		catch(NullPointerException e) {
			return false;
		}
	}

	public void directionalHelper(Thing a, Thing b, double slopeAX, double slopeBX, double rightB, double leftA,
			double t, double slopeBY, double slopeAY, double upA, double upB, Rectangle2D.Double ba,
			Rectangle2D.Double aa, HashMap<Double, Boolean> p) {
		double rightCollisionTime = ((slopeAX * t) - (slopeBX * t) + rightB - leftA) / (slopeAX - slopeBX);
		double yCoordB = slopeBY * (rightCollisionTime - t) + upB;
		double yCoordA = slopeAY * (rightCollisionTime - t) + upA;
		double yCoordBDown = yCoordB + ba.getHeight();
		double yCoordADown = yCoordA + aa.getHeight();

		if (coincide(yCoordA, yCoordB, yCoordBDown, yCoordADown)) {
			p.put(rightCollisionTime, true);
		}
	}

	public void directionalHelperY(Thing a, Thing b, double slopeAX, double slopeBX, double leftB, double downB,
			double leftA, double t, double slopeBY, double slopeAY, double upA, Rectangle2D.Double ba,
			Rectangle2D.Double aa, HashMap<Double, Boolean> p) {
		double upCollisionTime = ((slopeAY * t) - (slopeBY * t) + downB - upA) / (slopeAY - slopeBY);
		double xCoordB = slopeBX * (upCollisionTime - t) + leftB;
		double xCoordA = slopeAX * (upCollisionTime - t) + leftA;
		double xCoordBRight = xCoordB + ba.getWidth();
		double xCoordARight = xCoordA + aa.getWidth();
		if (coincide(xCoordA, xCoordB, xCoordBRight, xCoordARight)) {
			p.put(upCollisionTime, false);
		}
	}

	public boolean coincide(double a, double b, double br, double ar) {
		boolean standard = (a >= b && a <= br) || (ar >= b && ar <= br);
		boolean weird = (b > a && br > a) && (b < ar && br < ar);
		return standard || weird;
	}

	public boolean overlap(Rectangle2D.Double a, Rectangle2D.Double b) {
		return a.intersects(b);
	}

	public ArrayList<Thing> getBefore() {
		return before;
	}

	public void setBefore(ArrayList<Thing> before) {
		this.before = before;
	}

	public ArrayList<Point> getAfter() {
		return after;
	}

	public void setAfter(ArrayList<Point> after) {
		this.after = after;
	}
}
