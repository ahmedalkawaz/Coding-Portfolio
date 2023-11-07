
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setResizable(false);
		ChessBoard chessBoard = new ChessBoard(); // Creating the instance of chess board
		frame.getContentPane().add(chessBoard);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	

}
