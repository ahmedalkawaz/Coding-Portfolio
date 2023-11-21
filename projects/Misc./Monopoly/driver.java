import java.util.Scanner;

public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Monopoly m1 = new Monopoly();
		int mode = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Input 1 for a game, 2 for a simulation, 3 if you want to exit.");
		mode = input.nextInt();
		while(mode!=3) {
			
		if(mode==1) {
			m1 = new Monopoly();
			while(mode==1) {
			System.out.println("You landed on " + m1.move());
			System.out.println(m1);
			System.out.println("Would you like to roll again? (input 1 for yes, input 2 for a simulation, and input 3 to exit the game): ");
			mode = input.nextInt();
			}
			
			
			}
		
		if(mode==2) {
		m1 = new Monopoly();
		System.out.println("How many times do you want to roll the dice?");
		int diceRolls = input.nextInt();
		Monopoly m2 = new Monopoly();
		for(int i = 0;i<diceRolls;i++) {
			m2.move();
		}
		System.out.println(m2.getStats(diceRolls));
		System.out.println("Would you like to play another simulation? (input 2 for yes, input 1 for a game, and input 3 to exit the game): ");
		mode = input.nextInt();
		}
	

		}
	}

	public static String percent(Monopoly m, String name, int denom, int index) {
		return name + " %: " + Math.round(1.0 * m.getStatsList()[index] / denom * 10000) / ((double) 100);
	}

}
