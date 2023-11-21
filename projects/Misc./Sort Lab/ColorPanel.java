import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {

	private Ring[] leftPeg;
	private Ring[] middlePeg;
	private Ring[] rightPeg;
	private int sizeX, sizeY;
	private int height = 25;
	private int lCount = 0,mCount = 0,rCount = 0;

	public ColorPanel(Ring[] l, Ring[] m, Ring[] r, int x, int y) {
		leftPeg = l;
		middlePeg = m;
		rightPeg = r;
		sizeX = x;
		sizeY = y;

		for (int i = 0; i < leftPeg.length; i++) {
			leftPeg[i].setX((sizeX / 6) - (leftPeg[i].getWidth() / 2) + 10);
			leftPeg[i].setY(sizeY - (sizeY / 10) - ((i + 1) * height));
			leftPeg[i].setHeight(height);
		}

		for (int i = 0; i < middlePeg.length; i++) {
			middlePeg[i].setX((sizeX / 2) - (middlePeg[i].getWidth() / 2) + 10);
			middlePeg[i].setY(sizeY - (sizeY / 10) - ((i + 1) * height));
			middlePeg[i].setHeight(height);
		}

		for (int i = 0; i < rightPeg.length; i++) {
			rightPeg[i].setX(((int) (sizeX * ((double) 5 / 6)) - (rightPeg[i].getWidth() / 2) + 10));
			rightPeg[i].setY(sizeY - (sizeY / 10) - ((i + 1) * height));
			rightPeg[i].setHeight(height);
		}

		setBackground(new Color(0,0,120));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// pegs
		g.setColor(new Color(149, 69, 53));
		g.fillRect(sizeX / 6, sizeY / 100, 20, sizeY);
		g.setColor(new Color(193, 154, 107));
		g.drawRect(sizeX / 6, sizeY / 100, 20, sizeY);
		g.setColor(Color.white);
		g.drawString("Count: " + lCount, sizeX / 6- 5, sizeY / 100);

		g.setColor(new Color(149, 69, 53));
		g.fillRect(sizeX / 2, sizeY / 100, 20, sizeY);
		g.setColor(new Color(193, 154, 107));
		g.drawRect(sizeX / 2, sizeY / 100, 20, sizeY);
		g.setColor(Color.white);
		g.drawString("Count: " + mCount, sizeX / 2- 5, sizeY / 100);

		g.setColor(new Color(149, 69, 53));
		g.fillRect((int) (sizeX * ((double) 5 / 6)), sizeY / 100, 20, sizeY);
		g.setColor(new Color(193, 154, 107));
		g.drawRect((int) (sizeX * ((double) 5 / 6)), sizeY / 100, 20, sizeY);
		g.setColor(Color.white);
		g.drawString("Count: " + rCount, (int) (sizeX * ((double) 5 / 6)) - 5, sizeY / 100);

		// base
		g.setColor(new Color(233, 70, 99));
		g.fillRect(sizeX / 25, sizeY - (sizeY / 10), sizeX - (2 * (sizeX / 25)), sizeY / 5);
		g.setColor(new Color(250, 160, 160));
		g.drawRect(sizeX / 25, sizeY - (sizeY / 10), sizeX - (2 * (sizeX / 25)), sizeY / 5);

		// rings
		for (int i = 0; i < leftPeg.length; i++) {
			g.setColor(leftPeg[i].getC());
			g.fillRect(leftPeg[i].getX(), leftPeg[i].getY(), leftPeg[i].getWidth(), leftPeg[i].getHeight());
			g.setColor(Color.white);
			g.drawRect(leftPeg[i].getX(), leftPeg[i].getY(), leftPeg[i].getWidth(), leftPeg[i].getHeight());
		}

		for (int i = 0; i < middlePeg.length; i++) {
			g.setColor(middlePeg[i].getC());
			g.fillRect(middlePeg[i].getX(), middlePeg[i].getY(), middlePeg[i].getWidth(), middlePeg[i].getHeight());
			g.setColor(Color.white);
			g.drawRect(middlePeg[i].getX(), middlePeg[i].getY(), middlePeg[i].getWidth(), middlePeg[i].getHeight());
		}

		for (int i = 0; i < rightPeg.length; i++) {
			g.setColor(rightPeg[i].getC());
			g.fillRect(rightPeg[i].getX(), rightPeg[i].getY(), rightPeg[i].getWidth(), rightPeg[i].getHeight());
			g.setColor(Color.white);
			g.drawRect(rightPeg[i].getX(), rightPeg[i].getY(), rightPeg[i].getWidth(), rightPeg[i].getHeight());
		}

	}

	public void selection(Ring[] peg) {
		int maxLoc;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < peg.length - 1; i++) {
			maxLoc = i;
			for (int j = i + 1; j < peg.length; j++) {
				if (peg[j].compareTo(peg[maxLoc]) > 0) {
					maxLoc = j;
				}
			}
			if(maxLoc!=i) {
			swap(peg, maxLoc, i);
			lCount++;
			repaint();
			}

		}

	}

	public void bubble(Ring[] peg) {
		boolean hasSwaps;
		int count = 0;
		do {
			hasSwaps = false;
			for (int i = 0; i < peg.length - count - 1; i++) {
				if (peg[i].compareTo(peg[i + 1]) < 0) {
					swap(peg, i, i + 1);
					mCount++;
					repaint();
					hasSwaps = true;
				}
			}
			count++;

		} while (hasSwaps);

	}

	public void insertion(Ring[] peg) {
		for (int i = 1; i < peg.length; i++) {
			int j = i;
			while (j > 0 && peg[j].compareTo(peg[j - 1])>0) {
				swap(peg, j, j - 1);
				rCount++;
				repaint();
				j--;
			}
		}
	}
	

	
	public void flash (Ring r) {
		int width = r.getWidth();
		int height = r.getHeight();
		int y = r.getY();
		r.setWidth(r.getWidth()/2 + (r.getWidth()/2));
		r.setHeight(r.getHeight()/2);
		r.setY(r.getY()+r.getHeight()/2);
		repaint();
		try {
			Thread.sleep(375);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.setWidth(width);
		r.setHeight(height);
		r.setY(y);
		repaint();
		try {
			Thread.sleep(375);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	public void swap(Ring[] arr, int x, int y) {
		try {
			Thread.sleep(375);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flash(arr[x]);
		flash(arr[y]);
		int temp = arr[x].getY();
		arr[x].setY(arr[y].getY());
		arr[y].setY(temp);
		Ring temp2 = arr[x];
		arr[x] = arr[y];
		arr[y] = temp2;
		repaint();

	}


}
