
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Rook extends Piece {
	
	private static ImageIcon rookW = new ImageIcon("RookW.png");
	private static ImageIcon rookB = new ImageIcon("RookB.png");
	
	
	public Rook(int x, int y, boolean isWhite) {
		super(isWhite, rookW, rookB, x, y);
		value = 5;
	}
	
	public boolean isValidMove(int newX, int newY) {
		Point newLoc = new Point(newX, newY);
		return validMoves().contains(newLoc);

	}

	public ArrayList<Point> validMoves() {
		ArrayList<Point> moves = new ArrayList<Point>(); //returns arraylist of all valid rook movements
		int tempX;
		int tempY;
		for (int i = 0; i < 4; i++) {
			tempX = locX;
			tempY = locY;
			if (i % 4 == 0) { //up
				while (tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempY--;
					if (tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}
			
			if (i % 4 == 1) { //down
				while (tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempY++;
					if (tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}
			//left
			if (i % 4 == 2) {
				while (tempX >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX--;
					if (tempX >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}
			//right
			if (i % 4 == 3) {
				while (tempX <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX++;
					if (tempX <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}

		}
		cleanMove(moves);
		return moves;

	}
	
	
	public ArrayList<Point> threatenMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
		int tempX;
		int tempY;
		for (int i = 0; i < 4; i++) {
			tempX = locX;
			tempY = locY;
			if (i % 4 == 0) {
				while (tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempY--;
					if (tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
	
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}
			
			if (i % 4 == 1) {
				while (tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempY++;
					if (tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}
			
			if (i % 4 == 2) {
				while (tempX >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX--;
					if (tempX >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}
			
			if (i % 4 == 3) {
				while (tempX <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX++;
					if (tempX <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}

		}

		return moves;

	}

	
	
	public String toString() {
		return "Rook";
	}

}
