import java.awt.Color;

public class Ring {
	private int x, y, width, height;
	private Color c;
	
	public Ring(int width, Color c) {
		this.width = width;
		this.c = c;
	}
	
	public int compareTo(Object o) {
		return this.width-(((Ring) o).getWidth());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
	
	
