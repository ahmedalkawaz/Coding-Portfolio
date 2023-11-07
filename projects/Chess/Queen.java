
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Queen extends Piece {
	private static ImageIcon queenW = new ImageIcon("QueenW.png");
	private static ImageIcon queenB = new ImageIcon("QueenB.png");
	
	public Queen(int x, int y, boolean isWhite) {
		super(isWhite, queenW, queenB, x, y);
		value = 9;
	}
	
	public boolean isValidMove(int newX, int newY) {
		Point newLoc = new Point(newX, newY);
		return validMoves().contains(newLoc);

	}

	public ArrayList<Point> validMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
		straightMoves(moves);
		diagonalMoves(moves);
		cleanMove(moves);
		return moves;

		
	}
	
	public ArrayList<Point> threatenMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
		straightThreatenMoves(moves);
		diagThreatenMoves(moves);
		return moves;
		
	}
	
	
	public void straightMoves(ArrayList<Point> moves) {
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
					if (tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
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
					if (tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
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
					if (tempX >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
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
					if (tempX <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}

		}
	}
	
	
	public void diagonalMoves(ArrayList<Point> moves) {
		int tempX;
		int tempY;
		for (int i = 0; i < 4; i++) {
			tempX = locX;
			tempY = locY;
			if (i % 4 == 0) {
				while (tempX >= 0 && tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX--;
					tempY--;
					if (tempX >= 0 && tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
						moves.add(new Point(tempX, tempY));
						}
						break;	
					}
				}
			}
			
			if (i % 4 == 1) {
				while (tempX <= 7 && tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX++;
					tempY--;
					if (tempX <= 7 && tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}
			
			if (i % 4 == 2) {
				while (tempX >= 0 && tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX--;
					tempY++;
					if (tempX >= 0 && tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}
			
			if (i % 4 == 3) {
				while (tempX <= 7 && tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX++;
					tempY++;
					if (tempX <= 7 && tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if(board[tempY][tempX].getPiece().isWhite()!=isWhite) {
							moves.add(new Point(tempX, tempY));
							}
						break;	
					}
				}
			}

		}
	}
	
	public void diagThreatenMoves(ArrayList<Point> moves) {
		int tempX;
		int tempY;
		for (int i = 0; i < 4; i++) {
			tempX = locX;
			tempY = locY;
			if (i % 4 == 0) {
				while (tempX >= 0 && tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX--;
					tempY--;
					if (tempX >= 0 && tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
						moves.add(new Point(tempX, tempY));
						
						break;	
					}
				}
			}
			
			if (i % 4 == 1) {
				while (tempX <= 7 && tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX++;
					tempY--;
					if (tempX <= 7 && tempY >= 0&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}
			
			if (i % 4 == 2) {
				while (tempX >= 0 && tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX--;
					tempY++;
					if (tempX >= 0 && tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}
			
			if (i % 4 == 3) {
				while (tempX <= 7 && tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						moves.add(new Point(tempX, tempY));
					}
					tempX++;
					tempY++;
					if (tempX <= 7 && tempY <= 7&&!board[tempY][tempX].getPiece().toString().equals("Piece")&&!(board[tempY][tempX].getPiece().getValue()==100)) {
						
							moves.add(new Point(tempX, tempY));
							
						break;	
					}
				}
			}

		}

	}
	
	public void straightThreatenMoves(ArrayList<Point> moves) {
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

	}

	
	
	public String toString() {
		return "Queen";
	}

}
