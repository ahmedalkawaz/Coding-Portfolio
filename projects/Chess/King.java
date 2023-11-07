import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class King extends Piece {
	
	private static int yMove[] = { -1, 1, 0, 0, 1, 1, -1, -1 };
	private static int xMove[] = { 0, 0, 1, -1, 1, -1, 1, -1 };
	
	
	private static ImageIcon kingW = new ImageIcon("KingW.png");
	private static ImageIcon kingB = new ImageIcon("KingB.png");
	private boolean firstTurn = true;
	public King(int x, int y, boolean isWhite) {
		super(isWhite, kingW, kingB, x, y);
		value = 100;
	}
	protected void cleanMove( ArrayList<Point> points) {
		boolean tempFirstTurn = firstTurn;
		super.cleanMove(points);
		firstTurn = tempFirstTurn;
		
	}

	public boolean isValidMove(int newX, int newY) {
		Point newLoc = new Point(newX, newY);
		return validMoves().contains(newLoc);

	}

	public ArrayList<Point> validMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
		int newX;
		int newY;
		for(int i = 0; i < 8; i ++) {
			newX = locX+xMove[i];
			newY = locY+yMove[i];

			if(newX>=0&&newX<=7 && newY>=0&&newY<=7) {
				if(!Checkmate.restrictedSpots(isWhite).contains(new Point(newX,newY))) {
				if(board[newY][newX].getPiece().getValue()!=0) {
					if(board[newY][newX].getPiece().isWhite()!=isWhite) {
						moves.add(new Point(newX,newY));
					}
				}
				else {
					moves.add(new Point(newX,newY));
				}
				}
			}
		}
		
		return moves;
	}
	
	
	public ArrayList<Point> threatenMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
		int newX;
		int newY;
		for(int i = 0; i < 8; i ++) {
			newX = locX+xMove[i];
			newY = locY+yMove[i];

			if(newX>=0&&newX<=7 && newY>=0&&newY<=7) {

						moves.add(new Point(newX,newY));
					
				
			}
		}
		
		return moves;
	}
	
	public String toString() {
		return "King";
	}
	public void setLocX(int x) {
		if (firstTurn) {
			firstTurn = false;
		}
		locX = x;
	}

	public void setLocY(int y) {
		if (firstTurn) {
			firstTurn = false;
		}
		locY = y;

	}
}
