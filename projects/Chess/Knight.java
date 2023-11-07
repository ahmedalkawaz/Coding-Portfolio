import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Knight extends Piece {
	private static ImageIcon knightW = new ImageIcon("KnightW.png");
	private static ImageIcon knightB = new ImageIcon("KnightB.png");

	private static int yMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
	private static int xMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public Knight(int x, int y, boolean isWhite) {
		super(isWhite, knightW, knightB, x, y);
		value = 4;
	}

	public boolean isValidMove(int newX, int newY) {
		Point newLoc = new Point(newX, newY);
		return validMoves().contains(newLoc);

	}

	public ArrayList<Point> validMoves() { // checks the valid moves in the knight pattern
		ArrayList<Point> moves = new ArrayList<Point>();

		for (int i = 0; i < 8; i++) {
			if (locX + xMove[i] <= 7 && locX + xMove[i] >= 0 && locY + yMove[i] <= 7 && locY + yMove[i] >= 0) {
				if (!board[locY + yMove[i]][locX + xMove[i]].getPiece().toString().equals("Piece")) {
					if (board[locY + yMove[i]][locX + xMove[i]].getPiece().isWhite() != isWhite) {
						moves.add(new Point(locX + xMove[i], locY + yMove[i]));
					}
				} else {
					moves.add(new Point(locX + xMove[i], locY + yMove[i]));
				}

			}

		}
		cleanMove(moves);
		return moves;

	}

	public ArrayList<Point> threatenMoves() { //reutrns horse threats
		ArrayList<Point> moves = new ArrayList<Point>();

		for (int i = 0; i < 8; i++) {
			if (locX + xMove[i] <= 7 && locX + xMove[i] >= 0 && locY + yMove[i] <= 7 && locY + yMove[i] >= 0) {

					moves.add(new Point(locX + xMove[i], locY + yMove[i]));

			}

		}
		return moves;
	}

	public String toString() {
		return "Knight";
	}
}
