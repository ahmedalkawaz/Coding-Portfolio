import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Pawn extends Piece {
	private static ImageIcon pawnW = new ImageIcon("PawnW.png");
	private static ImageIcon pawnB = new ImageIcon("PawnB.png");
	private boolean firstTurn = true;

	public Pawn(int x, int y, boolean isWhite) {
		super(isWhite, pawnW, pawnB, x, y);
		value = 1;
	}

	public boolean isValidMove(int newX, int newY) {
		Point newLoc = new Point(newX, newY);
		return validMoves().contains(newLoc);

	}
	
	protected void cleanMove( ArrayList<Point> points) {
		boolean tempFirstTurn = firstTurn;
		super.cleanMove(points);
		firstTurn = tempFirstTurn;
		
	}

	public ArrayList<Point> validMoves() { //checks which pawn movements are valid
		ArrayList<Point> moves = new ArrayList<Point>();
		if (firstTurn) {
			if (isWhite) {
				if (board[locY - 1][locX].getPiece().toString().equals("Piece")) {
					moves.add(new Point(locX, locY - 1));

				}
				if (board[locY - 1][locX].getPiece().toString().equals("Piece")
						&& board[locY - 2][locX].getPiece().toString().equals("Piece")) {
					moves.add(new Point(locX, locY - 2));

				}
			} else {
				if (board[locY + 1][locX].getPiece().toString().equals("Piece")) {
					moves.add(new Point(locX, locY + 1));

				}
				if (board[locY + 1][locX].getPiece().toString().equals("Piece")
						&& board[locY + 2][locX].getPiece().toString().equals("Piece")) {
					moves.add(new Point(locX, locY + 2));

				}
			}
		} else {
			if (super.isWhite) {
				if (locY != 0 && board[locY - 1][locX].getPiece().toString().equals("Piece")) {
					moves.add(new Point(locX, locY - 1));

				}
			} else {
				if (locY != 7 && board[locY + 1][locX].getPiece().toString().equals("Piece"))
					moves.add(new Point(locX, locY + 1));
			}

		}
		attackMoves(moves);
		cleanMove(moves);

		return moves;

	}
	
	public void attackMoves(ArrayList<Point> moves) { //adds valid pawn attack moves to the valid move arraylist
		if (isWhite) {
			if (locY != 0) {
				if (locX!= 7) {
					if(!board[locY-1][locX+1].getPiece().toString().equals("Piece")&&board[locY-1][locX+1].getPiece().isWhite!=isWhite) {
						moves.add(new Point(locX+1,locY-1));
					}
				}
				if(locX!=0) {
					if(!board[locY-1][locX-1].getPiece().toString().equals("Piece")&&board[locY-1][locX-1].getPiece().isWhite!=isWhite) {
						moves.add(new Point(locX-1,locY-1));
					}
				}
			}

		}
		
		else {
			if (locY != 7) {
				if (locX!= 7) {
					if(!board[locY+1][locX+1].getPiece().toString().equals("Piece")&&board[locY+1][locX+1].getPiece().isWhite!=isWhite) {
						moves.add(new Point(locX+1,locY+1));
					}
				}
				if(locX!=0) {
					if(!board[locY+1][locX-1].getPiece().toString().equals("Piece")&&board[locY+1][locX-1].getPiece().isWhite!=isWhite) {
						moves.add(new Point(locX-1,locY+1));
					}
				}
			}

		}
		
	}
	
	public ArrayList<Point> threatenMoves(){ //arraylist of the possible pawn threats
		ArrayList<Point> moves = new ArrayList<Point>();
		if (isWhite) {
			if (locY != 0) {
				if (locX!= 7) {

						moves.add(new Point(locX+1,locY-1));
					
				}
				if(locX!=0) {
					
						moves.add(new Point(locX-1,locY-1));
					
				}
			}

		}
		
		else {
			if (locY != 7) {
				if (locX!= 7) {
			
						moves.add(new Point(locX+1,locY+1));
					
				}
				if(locX!=0) {
				
						moves.add(new Point(locX-1,locY+1));
					
				}
			}

		}
		return moves;
		
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

	public String toString() {
		return "Pawn";
	}

}
