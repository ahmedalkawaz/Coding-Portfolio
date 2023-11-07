
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bishop extends Piece {

	private static ImageIcon bishopW = new ImageIcon("BishopW.png");
	private static ImageIcon bishopB = new ImageIcon("BishopB.png");

	public Bishop(int x, int y, boolean isWhite) {
		super(isWhite, bishopW, bishopB, x, y);
		value = 3;
	}

	public boolean isValidMove(int newX, int newY) {
		Point newLoc = new Point(newX, newY);
		return validMoves().contains(newLoc);

	}

	public ArrayList<Point> validMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
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
					if (tempX >= 0 && tempY >= 0 && !board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if (board[tempY][tempX].getPiece().isWhite() != isWhite) {
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
					if (tempX <= 7 && tempY >= 0 && !board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if (board[tempY][tempX].getPiece().isWhite() != isWhite) {
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
					if (tempX >= 0 && tempY <= 7 && !board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if (board[tempY][tempX].getPiece().isWhite() != isWhite) {
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
					if (tempX <= 7 && tempY <= 7 && !board[tempY][tempX].getPiece().toString().equals("Piece")) {
						if (board[tempY][tempX].getPiece().isWhite() != isWhite) {
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
				while (tempX >= 0 && tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						if (board[tempY][tempX].getPiece().getValue() == 100) {
							if (board[tempY][tempX].getPiece().isWhite() == isWhite) {
								moves.add(new Point(tempX, tempY));
								break;
							}
						}
						else {
							moves.add(new Point(tempX, tempY));
						}
					}
					tempX--;
					tempY--;
					if (tempX >= 0 && tempY >= 0 && !board[tempY][tempX].getPiece().toString().equals("Piece")
							&& !(board[tempY][tempX].getPiece().getValue() == 100)) {

						moves.add(new Point(tempX, tempY));

						break;
					}
				}
			}

			if (i % 4 == 1) {
				while (tempX <= 7 && tempY >= 0) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						if (board[tempY][tempX].getPiece().getValue() == 100) {
							if (board[tempY][tempX].getPiece().isWhite() == isWhite) {
								moves.add(new Point(tempX, tempY));
								break;
							}
						}
						else {
							moves.add(new Point(tempX, tempY));
						}
					}
					tempX++;
					tempY--;
					if (tempX <= 7 && tempY >= 0 && !board[tempY][tempX].getPiece().toString().equals("Piece")
							&& !(board[tempY][tempX].getPiece().getValue() == 100)) {

						moves.add(new Point(tempX, tempY));

						break;
					}
				}
			}

			if (i % 4 == 2) {
				while (tempX >= 0 && tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						if (board[tempY][tempX].getPiece().getValue() == 100) {
							if (board[tempY][tempX].getPiece().isWhite() == isWhite) {
								moves.add(new Point(tempX, tempY));
								break;
							}
						}
						else {
							moves.add(new Point(tempX, tempY));
						}
					}
					tempX--;
					tempY++;
					if (tempX >= 0 && tempY <= 7 && !board[tempY][tempX].getPiece().toString().equals("Piece")
							&& !(board[tempY][tempX].getPiece().getValue() == 100)) {

						moves.add(new Point(tempX, tempY));

						break;
					}
				}
			}

			if (i % 4 == 3) {
				while (tempX <= 7 && tempY <= 7) {
					if (!new Point(tempX, tempY).equals(new Point(locX, locY))) {
						if (board[tempY][tempX].getPiece().getValue() == 100) {
							if (board[tempY][tempX].getPiece().isWhite() == isWhite) {
								moves.add(new Point(tempX, tempY));
								break;
							}
						}
						else {
							moves.add(new Point(tempX, tempY));
						}
					}
					tempX++;
					tempY++;
					if (tempX <= 7 && tempY <= 7 && !board[tempY][tempX].getPiece().toString().equals("Piece")
							&& !(board[tempY][tempX].getPiece().getValue() == 100)) {

						moves.add(new Point(tempX, tempY));

						break;
					}
				}
			}

		}

		return moves;

	}

	public String toString() {
		return "Bishop";
	}

}
