import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Piece extends JLabel {
	protected boolean check;
	protected int locX;
	protected int locY;
	protected int value = 0;
	/*
	 * key for value: pawns 1 bishop 3 knight 4 rook 5 queen 9 king 100
	 */

	protected ImageIcon img;
	protected boolean isWhite;
	public static Spot[][] board;
	private static Piece[][] logicBoard;

	// constructors
	public Piece() {

	}


	public Piece(boolean isWhite, ImageIcon white, ImageIcon black, int x, int y) {
		setBounds(0, 0, 100, 100);
		setOpaque(false);
		this.locX = x;
		this.locY = y;
		this.isWhite = isWhite;
		if (isWhite) {
			this.setIcon(white);
		} else {
			this.setIcon(black);
		}
		this.setHorizontalAlignment(JLabel.CENTER);

	}

	public Piece(int x, int y, int value, boolean color) {
		this.locX = x;
		this.locY = y;
		this.value = value;
		this.isWhite = color;
	}

	public Piece(int x, int y, boolean color) {
		this.locX = x;
		this.locY = y;
		this.isWhite = color;
	}

	public Piece(int x, int y) {
		setBounds(0, 0, 100, 100);
		setOpaque(false);
		setBackground(Color.red);
		this.locX = x;
		this.locY = y;

	}

	// checks to see if the inputted move is possible for the specific piece. each
	// subclass has a unique version of this method. A move is only possible when
	// not obstructed and within the limitations of the piece

	public boolean isValidMove(int newX, int newY) {
		return true;
	}

	// puts all of the validmoves into an arraylist of points on the chessboard

	public ArrayList<Point> validMoves() {
		ArrayList<Point> moves = new ArrayList<Point>();
		return moves;
	}

	protected void cleanMove(ArrayList<Point> points) { //checks if the move will put or keep the king in check, if it does, then it is removed
		Piece temp;
		int tempX = locX;
		int tempY = locY;
		int newX;
		int newY;
		for (int k = 0; k < points.size(); k++) {
			newX = (int) points.get(k).getX();
			newY = (int) points.get(k).getY();

			temp = board[newY][newX].getPiece();
			if (temp.getValue() == 0) {
				board[newY][newX].setPiece(this);
				board[locY][locX].setPiece(temp);

				this.setLocX(newX);
				this.setLocY(newY);

				temp.setLocX(tempX);
				temp.setLocY(tempY);
				setLogicBoard();

				if (Checkmate.inCheck(isWhite)) {
					points.remove(k);
					k--;
				}

				board[newY][newX].setPiece(temp);
				board[tempY][tempX].setPiece(this);
				temp.setLocX(newX);
				temp.setLocY(newY);

				this.setLocX(tempX);
				this.setLocY(tempY);
				setLogicBoard();
			}
			else {
				board[newY][newX].setPiece(this);
				board[locY][locX].setPiece(new Piece(locX,locY,0,false));

				this.setLocX(newX);
				this.setLocY(newY);

				setLogicBoard();

				if (Checkmate.inCheck(isWhite)) {
					points.remove(k);
					k--;
				}

				board[newY][newX].setPiece(temp);
				board[tempY][tempX].setPiece(this);

				this.setLocX(tempX);
				this.setLocY(tempY);
				setLogicBoard();
				
			}

		}

	}

	public void setLogicBoard() { // sets the array for the logic board
		Piece piece;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				piece = board[i][j].getPiece();
				if (piece.getValue() == 0) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 0, piece.isWhite()); // sets piece
																										// versions of
																										// the
																										// subclasses
				} else if (piece.getValue() == 1) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 1, piece.isWhite());
				} else if (piece.getValue() == 3) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 3, piece.isWhite());
				} else if (piece.getValue() == 4) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 4, piece.isWhite());
				} else if (piece.getValue() == 5) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 5, piece.isWhite());
				} else if (piece.getValue() == 9) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 9, piece.isWhite());
				} else if (piece.getValue() == 100) {
					logicBoard[i][j] = new Piece(piece.getLocY(), piece.getLocX(), 100, piece.isWhite());
				}
			}
		}

	}

	// checks if one piece is equal to another

	public boolean equal(Piece other) {

		return other.getValue() == this.getValue() && this.getX() == other.getX() && this.getY() == other.getY()
				&& other.isWhite() == this.isWhite();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return "Piece";
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public static Spot[][] getBoard() {
		return board;
	}

	public static void setBoard(Spot[][] board) {
		Piece.board = board;
	}

	public static Piece[][] getLogicBoard() {
		return logicBoard;
	}

	public static void setLogicBoard(Piece[][] logicBoard) {
		Piece.logicBoard = logicBoard;
	}

	public ArrayList<Point> threatenMoves() {
		ArrayList<Point> moves = new ArrayList<Point>(); //returns an arraylist of all of the coordinates of squares that are threatened by pieces
		return moves;
	}

}