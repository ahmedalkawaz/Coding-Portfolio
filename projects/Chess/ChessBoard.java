import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements MouseListener {

	// The overall box count in chess board

	private boolean whiteTurn = true;
	private Spot[][] squareCount = new Spot[8][8];
	private Piece[][] board = new Piece[8][8];
	private boolean gameOver = false;
	private Color blackColor = new Color(180, 139, 99);
	private Color whiteColor = new Color(244, 220, 180);
	private int width = 800;
	private boolean [] occurances = new boolean[5];
	private Piece chessButton = new Piece();
	private Piece[] whitePieces = { new Rook(0, 7, true), new Knight(1, 7, true), new Bishop(2, 7, true), //array of the white pieces
			new Queen(3, 7, true), new King(4, 7, true), new Bishop(5, 7, true), new Knight(6, 7, true),
			new Rook(7, 7, true) };

	private Piece[] blackPieces = { new Rook(0, 0, false), new Knight(1, 0, false), new Bishop(2, 0, false), //array of the black pieces
			new Queen(3, 0, false), new King(4, 0, false), new Bishop(5, 0, false), new Knight(6, 0, false),
			new Rook(7, 0, false) };

	private boolean firstClick = true; 
	private Spot temp;

	public ChessBoard() {
		paintBoard(); //sets the white and brown spots on the board
		setBoard(); // puts all of the pieces onto the spots
		setLogicBoard(); //copies the board onto an easier logic version of the board
		Checkmate.setBoard(board); //adds the board to checkmate class
		Checkmate.setPhysicalBoard(squareCount);
		Piece.setLogicBoard(board);
		Piece.setBoard(squareCount); //adds the board to the piece class
		
		
		 

	}

	public void paintBoard() { // Adds all of the spots on the board to the array and onto the JPanel
		for (int i = 0; i < squareCount.length; i++) {
			for (int j = 0; j < squareCount.length; j++) {
				Spot back;
				if (j % 2 == 0) { // Adding color based on the odd and even initially.
					back = new Spot(i, j, whiteColor);
					back.setBounds(0, 0, 100, 100);
					back.setOpaque(true);
					back.setLayout(new GridLayout(1, 1));
					add(back);
					squareCount[i][j] = back;

				} else { // Adding color based on the odd and even initially.

					back = new Spot(i, j, blackColor);
					back.setBounds(0, 0, 100, 100);
					back.setOpaque(true);
					back.setLayout(new GridLayout(1, 1));
					add(back);
					squareCount[i][j] = back;
				}
				squareCount[i][j].addMouseListener(this);
			}
			Color temp = blackColor;
			blackColor = whiteColor;
			whiteColor = temp;
		}
		this.setLayout(new GridLayout(8, 8)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(800, 800); // Size of the chess board
		this.setVisible(true);
	}

	public void setBoard() {

		for (int i = 0; i < squareCount.length; i++) {
			for (int j = 0; j < squareCount.length; j++) {
				chessButton = new Piece(j, i);
				squareCount[i][j].setPiece(chessButton); //adds all of the pieces to the array in chess structure
				if (i == 0) {
					chessButton = blackPieces[j];
					squareCount[i][j].setPiece(chessButton);
					squareCount[i][j].add(chessButton);
				} else if (i == 1) {
					chessButton = new Pawn(j, i, false);
					squareCount[i][j].setPiece(chessButton);
					squareCount[i][j].add(chessButton);
				}
				if (i == 6) {
					chessButton = new Pawn(j, i, true);
					squareCount[i][j].setPiece(chessButton);
					squareCount[i][j].add(chessButton);
				} else if (i == 7) {
					chessButton = whitePieces[j];
					squareCount[i][j].setPiece(chessButton);
					squareCount[i][j].add(chessButton);
				}
				board[i][j] = chessButton;
			}
		}
	}
	
	private void endBoard() {
		for(int i = 0; i < 8;i++) {
			for(int j =0;j<8;j++) {
				if(squareCount[i][j].getColor().equals(blackColor)) {
					squareCount[i][j].setBackground(new Color(194, 122, 116)); //sets all grid colors to a red version
				}
				else {
					squareCount[i][j].setBackground(new Color(231, 133, 135));
				}
			}
		}
	}
	
	public void setLogicBoard() { // sets the array for the logic board
		Piece piece;
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j<8; j ++) {
				piece = squareCount[i][j].getPiece();
				if(piece.getValue()==0) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 0, piece.isWhite()); //sets piece versions of the subclasses
				}
				else if(piece.getValue()==1) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 1, piece.isWhite());
				}
				else if(piece.getValue()==3) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 3, piece.isWhite());
				}
				else if(piece.getValue()==4) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 4, piece.isWhite());
				}
				else if(piece.getValue()==5) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 5, piece.isWhite());
				}
				else if(piece.getValue()==9) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 9, piece.isWhite());
				}
				else if(piece.getValue()==100) {
					board[i][j] = new Piece(piece.getLocY(),piece.getLocX(), 100, piece.isWhite());
				}
			}
		}
	
	}
	

	public void availableMoves(ArrayList<Point> spots, int ogX, int ogY) { //highlights all of the available moves that a piece can take in green
		Color lightGreen = new Color(174, 177, 135, 255);
		Color darkGreen = new Color(132, 121, 78, 255);
		
		if (squareCount[ogY][ogX].getColor().equals(whiteColor)) {	//sets color of first square
			squareCount[ogY][ogX].setBackground(lightGreen);
		} else {
			squareCount[ogY][ogX].setBackground(darkGreen);
		}

		for (int i = 0; i < spots.size(); i++) {
			if (squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()].getColor().equals(whiteColor)) {
				if (!squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()].getPiece().toString()		//goes through all coordinates of available moves, checks if they are dark or light color and colors accordingly
						.equals("Piece")) {
					squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()]
							.setBackground(new Color(231, 133, 135));
				} else {
					squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()].setBackground(lightGreen);
				}
			} else {
				if (!squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()].getPiece().toString()
						.equals("Piece")) {
					squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()]
							.setBackground(new Color(194, 122, 116));
				} else {
					squareCount[(int) spots.get(i).getY()][(int) spots.get(i).getX()].setBackground(darkGreen);
				}
			}
		}

	}

	public void resetColor() { //puts the board back to its regular color scheme
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squareCount[i][j].setBackground(squareCount[i][j].getColor());
			}
		}

	}
	
	public boolean noMoves(boolean isWhite) { //detects if no moves are possible
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				if(squareCount[i][j].getPiece().getValue()!=0 && squareCount[i][j].getPiece().isWhite()==isWhite) {
					if(squareCount[i][j].getPiece().validMoves().size()>=1) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean checkMatePossible() {
		int value;
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				value = board[i][j].getValue();
				if(value==1) {
					occurances[0] = true;
				}
				if(value==4) {
					occurances[1]=true;
				}
				if(value ==3) {
					occurances[2]=true;
				}
				if(value ==5) {
					occurances[3]=true;
				}
				if(value ==9) {
					occurances[4]=true;
				}
				
			}
		}
		
		if(!(occurances[0]||occurances[1]||occurances[2]||occurances[3]||occurances[4])) {
			return false;
		}
		if(!(occurances[0]||occurances[2]||occurances[3]||occurances[4])&&occurances[1]) {
			return false;
		}
		if(!(occurances[0]||occurances[1]||occurances[3]||occurances[4])&&occurances[2]) {
			return false;
		}
		return true;
		
	}
	
	public boolean staleMate(boolean isWhite) {
		return !checkMatePossible()||noMoves(isWhite);
	}
	
	
	
	public void promote(Spot p) { //detects a promotion
		Piece temp;
		if(p.getPiece().getValue()==1&&p.getPiece().isWhite()) {
			if(p.getPiece().getLocY()==0) {
				temp = p.getPiece();
				p.setPiece(new Queen(p.getPiece().getLocX(),p.getPiece().getLocY(),p.getPiece().isWhite())); //removes pawn
				p.remove(temp);
				p.add(p.getPiece());
			}
		}
		else if(p.getPiece().getValue()==1&&!p.getPiece().isWhite()) {
			if(p.getPiece().getLocY()==7) {
				temp = p.getPiece();
				p.setPiece(new Queen(p.getPiece().getLocX(),p.getPiece().getLocY(),p.getPiece().isWhite())); //adds queen
				p.remove(temp);
				p.add(p.getPiece());
			}
		}
	}
	
	public int gridSpot(int x) { //finds the corresponding spot on the grid based off location of mouse click
		int count = 0;
		double incr = (width/8.0)-5;
		for(double i = incr; i <= width; i+=incr) {
			if(x<i) {
				return count;
			}
			count++;
		}
		return 7;
		
	}

	public Piece getChessButton() {
		return chessButton;
	}


	public void setChessButton(Piece chessButton) {
		this.chessButton = chessButton;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(!gameOver) {
		Spot p = (Spot) e.getComponent();
		int numX = gridSpot(p.getX());; // while the game isn't over, activates all of the possible movements upon first clicking a piece
		int numY = gridSpot(p.getY());
		if ((firstClick || (!firstClick && squareCount[numY][numX].getPiece().isWhite() == temp.getPiece().isWhite()))
				&& !squareCount[numY][numX].getPiece().toString().equals("Piece")
				&& squareCount[numY][numX].getPiece().isWhite() == whiteTurn) {
			resetColor();
			temp = squareCount[numY][numX];

			Checkmate.inCheck(whiteTurn);
			availableMoves(temp.getPiece().validMoves(), numX, numY);
			firstClick = false;
		} 
		else if (firstClick == false && temp.getPiece().isValidMove(numX, numY)) { //once clicked again, will transfer whatever piece to the desired location. updates all arrays and checks if game is over
			
			int tempX = temp.getPiece().getLocX() ;
			int tempY = temp.getPiece().getLocY() ;

			if (!squareCount[numY][numX].getPiece().toString().equals("Piece")) {
				squareCount[numY][numX].remove(squareCount[numY][numX].getPiece());
			}
			squareCount[numY][numX].setPiece(temp.getPiece()); //sets piece to new location
			squareCount[numY][numX].getPiece().setLocX(numX);
			squareCount[numY][numX].getPiece().setLocY(numY);

			squareCount[numY][numX].add(temp.getPiece());
			temp.setPiece(new Piece(tempX, tempY));	//replaces old space with empty piece
			promote(squareCount[numY][numX]);
			setLogicBoard();
			if(Checkmate.isCheckMate(true) || Checkmate.isCheckMate(false) || staleMate(true)||staleMate(false)) { //if a stalemate or checkmate are detected, game is over
				gameOver = true;
				
			}
			
			Checkmate.restrictedSpots(true);
			firstClick = true;
			whiteTurn = !whiteTurn;
			Piece.setBoard(squareCount);
			
			resetColor(); //resets colors
			repaint();
			
			if(gameOver) {
				endBoard(); //checkmate board colors
			}

		}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public Piece[][] getBoard() { //standard getter for board
		return board;
	}



	public void setBoard(Piece[][] board) { //standard setter
		this.board = board;
	}



	
}