import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Towers tower;
		int play;
		int rings;
		int move;
		int move2;
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("Game or Sim? (0 = sim, 1 = game, 2 = exit");
		play = input.nextInt();
		
		while(play!=2) {
			System.out.println("How many rings?");
			rings = input.nextInt();
			tower = new Towers(rings);
			if(play==0) {
				while(!tower.isSolved()) {
					if(rings%2==0) {
						print(tower,0,1);
						if(tower.isSolved()) {
							break;
							}
						print(tower,0,2);
						if(tower.isSolved()) {
							break;
							}
						print(tower,1,2);
						if(tower.isSolved()) {
							break;
							}
					}
					else {
						print(tower,0,2);
						if(tower.isSolved()) {
							break;
							}
						print(tower,0,1);
						if(tower.isSolved()) {
							break;
							}
						print(tower,1,2);
						if(tower.isSolved()) {
							break;
							}
					}
				}
				System.out.println("Woo it solved");
				System.out.println("Game or Sim? (0 = sim, 1 = game, 2 = exit");
				play = input.nextInt();
				
			}
			
			if(play==1) {
				System.out.println("How many rings?");
				rings = input.nextInt();
				tower = new Towers(rings);
				while(!tower.isSolved()) {
					System.out.println(tower);
					System.out.println("What is your first tower? (0=left,1=middle,2=right");
					move = input.nextInt();
					System.out.println("What is your second tower? (0=left,1=middle,2=right");
					move2 = input.nextInt();
					tower.makeMove(move, move2);
				}
				System.out.println(tower);
				System.out.println("you did it");
				System.out.println("Game or Sim? (0 = sim, 1 = game, 2 = exit");
				play = input.nextInt();
			}
		}
		
		
		
		
		

	}
	
	public static void print(Towers tower, int x, int y) {
		tower.makeMove(x,y);
		for(int i = 0; i<100000;i++) {
			System.out.println(tower);
		}
	}

}
