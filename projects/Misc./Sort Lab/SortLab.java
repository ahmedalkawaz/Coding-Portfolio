import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int width = 1500;
		int height = 1000;

		JFrame myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(width, height);
		myFrame.setTitle("Tower of Hanoi");
		myFrame.setVisible(true);
		boolean playAgain = true;
		
		while(playAgain) {

		int input = Integer.valueOf(JOptionPane.showInputDialog("How many rings?"));

		Ring[] leftPeg = new Ring[input];
		Ring[] middlePeg = new Ring[input];
		Ring[] rightPeg = new Ring[input];

		String[] choices = { "Reverse Order", "Random" };
		String input2 = (String) JOptionPane.showInputDialog(myFrame, "How would you like the rings scrambled?", null,
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

		int num = 0;
		int colorNum;
		ArrayList<Integer> nums = new ArrayList<Integer>();

		Color[] colors = { new Color(237, 83, 20), new Color(255, 185, 42), new Color(254, 235, 81),
				new Color(155, 202, 62), new Color(58, 187, 201), new Color(102, 109, 203), new Color(78, 54, 134) };

		if (input2.equals("Random")) {
			for (int i = 0; i < input; i++) {
				do {
				num = (int) (Math.random() * input + 1);
				}
				while(nums.contains(num));
				nums.add(num);
				colorNum = (int) (Math.random() * colors.length);
				leftPeg[i] = new Ring(30 * num, colors[colorNum]);
				middlePeg[i] = new Ring(30 * num, colors[colorNum]);
				rightPeg[i] = new Ring(30 * num, colors[colorNum]);
			}
		} else {
			for (int i = 1; i <= input; i++) {
				colorNum = (int) (Math.random() * colors.length);
				leftPeg[i-1] = new Ring(30 * i, colors[colorNum]);
				middlePeg[i-1] = new Ring(30 * i, colors[colorNum]);
				rightPeg[i-1] = new Ring(30 * i, colors[colorNum]);

			}
		}

		ColorPanel myPanel = new ColorPanel(leftPeg, middlePeg, rightPeg, width, height);
		myPanel.setSize(width, height);
		myFrame.add(myPanel);
		myFrame.repaint();

		myPanel.selection(leftPeg);
		myPanel.bubble(middlePeg);
		myPanel.insertion(rightPeg);
		playAgain = Boolean.valueOf(JOptionPane.showInputDialog("Do you want to play again? (true or false)"));
		
		}
		

	}

}
