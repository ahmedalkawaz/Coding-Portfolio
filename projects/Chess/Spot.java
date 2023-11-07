import java.awt.Color;

import javax.swing.JLabel;

public class Spot extends JLabel{
	private int boardX;
	private int boardY;
	private Piece piece = new Piece();
	private Color color;
	
	public Spot(int x, int y,  Color c ) {
		this.boardX =x;
		this.boardY =y;
		color = c;
		setBackground(color);
	}
	
	public Spot(int x, int y, Color c ,Piece piece) {
		this.boardX = x;
		this.boardY = y;
		this.piece = piece;
		color = c;
		setBackground(color);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public int getBoardX() {
		return boardX;
	}

	public void setBoardX(int boardX) {
		this.boardX = boardX;
	}

	public int getBoardY() {
		return boardY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setBoardY(int boardY) {
		this.boardY = boardY;
	}
	
	public String toString() {
		return piece.toString();
	}

	
	
}
