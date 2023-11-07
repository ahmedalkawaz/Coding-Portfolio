import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	// Fix gravity and mouse drag stuff
	// Implement a force list

	private Color color;
	private Point mouseLoc;
	private Thing thing;
	private int length = 100;
	private double k = .03;
	private int time = 0;
	private int g;
	private double mouseXVelocity;
	private double mouseYVelocity;
	int dragEvent = -1;
	private int w, h;
	private ArrayList<Thing> things = new ArrayList<Thing>();
	private HashMap<Thing, ArrayList<Thing>> doneCollisions = new HashMap<Thing, ArrayList<Thing>>();
	JLabel entities = new JLabel("0");
	public ColorPanel(Color color, int w, int h, int g) {
		this.color = color;
		this.w = w;
		this.h = h;
		this.g = g;
		this.setBackground(color);
		this.setSize(w, h);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		entities.setText(things.size()-4 + "");
		entities.setForeground(Color.white);
	//	this.add(entities);
		System.out.println("# " + time);
		if (things.size() > 0) {
			
			for (int i = 0; i < things.size(); i++) {
				things.get(i).draw(g);
				// if (dragEvent != i) {
				variables(things.get(i));
				// }
			}
			collisionHandler();

		}
		time++;
	}

	// make these cycle through the whole list
	public int contactDownDetection(Thing thing) {
		for (int i = 0; i < things.size(); i++) {
			if (Collisions.contactDown(thing.getArea(), things.get(i).getArea())) {
				return i;
			}
		}
		return -1;
	}

	public int contactUpDetection(Thing thing) {
		for (int i = 0; i < things.size(); i++) {
			if (Collisions.contactUp(thing.getArea(), things.get(i).getArea())) {
				return i;
			}
		}
		return -1;
	}

	public int contactLeftDetection(Thing thing) {
		for (int i = 0; i < things.size(); i++) {
			if (Collisions.contactLeft(thing.getArea(), things.get(i).getArea())) {
				return i;
			}
		}
		return -1;
	}

	public int contactRightDetection(Thing thing) {
		for (int i = 0; i < things.size(); i++) {
			if (Collisions.contactRight(thing.getArea(), things.get(i).getArea())) {
				return i;
			}
		}
		return -1;
	}

	public void variables(Thing thing) {
		forces(thing, g);
		velocity(thing);
	}

	public void collisionHandler() {
		Contact handler = new Contact(things);
		handler.contactDetector();

	}

	public void forces(Thing thing, int g) {
		double gForce;
		if (!thing.isGround()) {
			gForce = g * thing.getMass();
		} else {
			gForce = 0;
		}
		double friction = 0;
		double yForce = gForce - thing.getForce(g);

		thing.changeForce(yForce, 2);

	}

	public void velocity(Thing thing) {

		double xVelocity = thing.getxVelocity();
		double yVelocity = thing.getyVelocity();
		double xAccel = thing.getxAccel();
		double yAccel = thing.getyAccel();
		int contactUp = contactUpDetection(thing);
		int contactRight = contactRightDetection(thing);
		int contactDown = contactDownDetection(thing);
		int contactLeft = contactLeftDetection(thing);
		/*
		 * if (xAccel>0&&contactRight==-1) { thing.setxVelocity(xVelocity + xAccel); }
		 * else if (xAccel<0&&contactLeft==-1) { thing.setxVelocity(xVelocity + xAccel);
		 * } else if (yAccel>0&&contactDown==-1) { thing.setyVelocity(yVelocity +
		 * yAccel); } else if (yAccel<0&&contactUp==-1) { thing.setyVelocity(yVelocity +
		 * yAccel); }
		 */
		thing.setyVelocity(yVelocity + yAccel);
		thing.setxVelocity(xVelocity + xAccel);
		// if(!thing.isGround()) {
		// System.out.println(thing.getY());
		// }
	}

	public void addThing(Thing t) {
		things.add(t);
		if (!t.isGround()) {
			// t.setxVelocity(300);
			// t.setyVelocity(-10);
		}
		doneCollisions.put(t, new ArrayList<Thing>());
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Thing getThing() {
		return thing;
	}

	public void setThing(Thing thing) {
		this.thing = thing;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (dragEvent == -1) {
			mouseLoc = e.getPoint();
			if (things.size() > 0) {
				for (int i = 0; i < things.size(); i++) {
					if (things.get(i).getArea().contains(mouseLoc) && !things.get(i).isGround()) {
						dragEvent = i;
						things.get(i).setDragEvent(true);
						break;
					}
				}
			}
		}

	}

	public ArrayList<Thing> getThings() {
		return things;
	}

	public void setThings(ArrayList<Thing> things) {
		this.things = things;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (dragEvent != -1) {
			Thing temp = things.get(dragEvent);
			mouseXVelocity = 0;
			mouseYVelocity = 0;
			dragEvent = -1;
			temp.setDragEvent(false);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragEvent != -1) {
			Thing temp = things.get(dragEvent);
			mouseXVelocity = e.getX() - mouseLoc.getX();
			mouseYVelocity = e.getY() - mouseLoc.getY();

			int newX = (int) (temp.getX() + mouseXVelocity);
			int newY = (int) (temp.getY() + mouseYVelocity);

			temp.setxVelocity(mouseXVelocity);
			temp.setyVelocity(mouseYVelocity);

			mouseLoc.setLocation(e.getX(), e.getY());

			// variables(temp);

		}

	}

	public double angle(double x, double y) {
		if (x < 0) {
			return Math.atan(y / x) + Math.PI;
		}
		return Math.atan(y / x);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hey");
		int m = (int) (Math.random() * 10 + 1);
		if (e.getKeyChar() == 'f') {
			Thing red = new Thing(w / 2, h / 2, w / 128, w / 128, color.red, 10, false);
			boolean allowed = true;
			for (int i = 0; i < things.size(); i++) {
				if (things.get(i).getArea().intersects(red.getArea())) {
					allowed = false;
				}
			}
			if (allowed) {
				int r = (int)(Math.random()*256);
				int g = (int)(Math.random()*256);
				int b = (int)(Math.random()*256);
				red.setColor(new Color(255,255,255));
				int vx = (int) (Math.random() * 10 - 5);
				int vy = (int) (Math.random() * 10 - 5);
				red.setxVelocity(vx);
				red.setyVelocity(vy);
				addThing(red);
			}
		}
		if(e.getKeyChar()=='r') {
			ArrayList<Thing> temp = new ArrayList<Thing>();
			for(int i = 0; i < 4; i ++) {
				temp.add(things.get(i));
			}
			things = temp;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}