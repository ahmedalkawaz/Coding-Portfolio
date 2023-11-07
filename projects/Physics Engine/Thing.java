import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Thing {
	private int x, y, w, h;
	private Color color;
	private Rectangle2D.Double area;
	private double mass;
	private double xAccel, yAccel, xVelocity = 0, yVelocity = 0;
	private double [] force = {0,0,0,0}; //N, E, S, W
	private boolean isGround = false;
	private HashMap<Thing,Double> friction = new HashMap<Thing,Double>();
	private boolean elastic = true;
	private boolean middle = false;
	private boolean dragEvent = false;
	
	
	public boolean isMiddle() {
		return middle;
	}

	public void setMiddle(boolean middle) {
		this.middle = middle;
	}

	public boolean isDragEvent() {
		return dragEvent;
	}

	public void setDragEvent(boolean dragEvent) {
		this.dragEvent = dragEvent;
	}

	public double[] getForces() {
		return force;
	}
	
	public double getForce(int i) {
		return force[i];
	}
	
	public void setForce(double[] force) {
		this.force = force;
		yAccel = (force[0] + force[2])/mass;
		xAccel = (force[1] + force[3])/mass;
	}
	
	public void changeForce(double f, int i) {
		force[i] = f;
		yAccel = (force[0] + force[2])/mass;
		xAccel = (force[1] + force[3])/mass;
	}
	public void addForce(double f, int i) {
		force[i] += f;
		yAccel = (force[0] + force[2])/mass;
		xAccel = (force[1] + force[3])/mass;
	}

	

	public HashMap<Thing, Double> getFriction() {
		return friction;
	}

	public void setFriction(HashMap<Thing, Double> friction) {
		this.friction = friction;
	}

	public boolean isElastic() {
		return elastic;
	}

	public void setElastic(boolean elastic) {
		this.elastic = elastic;
	}
	
	public String toString() {
		String c = "";
		if(color.equals(Color.red)) {
			c = "RED";
		}
		else if(color.equals(Color.gray)) {
			c = "GRAY";
		}
		else if(color.equals(Color.green)) {
			c = "GREEN";
		}
		else if(color.equals(Color.pink)) {
			c = "PINK";
		}
		return "BLOCK WITH AREA: " + w*h + ", COLOR: " + c;
	}


	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}



	public double getxAccel() {
		return xAccel;
	}

	public void setxAccel(double xAccel) {
		this.xAccel = xAccel;
	}

	public double getxVelocity() {
		return xVelocity;
	}
	

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}
	
	public void addxVelocity(double increment) {
		this.xVelocity += increment;
	}
	

	public boolean isGround() {
		return isGround;
	}

	public void setGround(boolean isGround) {
		this.isGround = isGround;
	}

	public java.awt.geom.Rectangle2D.Double getArea() {
		return area;
	}

	public void setArea(java.awt.geom.Rectangle2D.Double area) {
		this.area = area;
	}
	
	public void shadowLoc(double x, double y) {
		area.setRect(x, y, w, h);
		this.x = (int) (x);
		this.y = (int) (y);
	}
	
	public Thing() {
		
	}

	public Thing(int x, int y, int w, int h, Color color, double mass, boolean isGround) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.mass = mass;
		this.isGround = isGround;
		area = new Rectangle2D.Double(x, y, w, h);

		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		;
	}

	public int getX() {
		return x;
	}

	public double getyAccel() {
		return yAccel;
	}

	public void setyAccel(double accel) {
		this.yAccel = accel;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double velocity) {
		this.yVelocity = velocity;
		velocity = Math.sqrt((Math.pow(xVelocity, 2)) + Math.pow(yVelocity, 2));
	}
	
	public void addyVelocity(double increment) {
		this.yVelocity += increment;
	}

	public void setX(int x) {
		this.x = x;
		area.setRect(x, y,w,h);
	}
	public void addX(int a) {
		this.x+=a;
		area.setRect(x, y,w,h);
	}

	public int getY() {
		return y;
	}
	

	public void setY(int y) {
		this.y = y;
		area.setRect(x, y,w,h);
	}
	
	public void addY(int a) {
		this.y+=a;
		area.setRect(x, y,w,h);
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}