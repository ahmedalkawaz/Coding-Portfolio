import java.util.Arrays;

public class Monopoly {

	private final static String[] typeProperty = { "Go", "Brown", "Chance", "Brown", "Tax", "Railroad", "LightBlue",
			"Chance", "LightBlue", "LightBlue", "Jail", "Pink", "Utility", "Pink", "Pink", "Railroad", "Orange",
			"Chance", "Orange", "Orange", "Parking", "Red", "Chance", "Red", "Red", "Railroad", "Yellow", "Yellow",
			"Utility", "Yellow", "Go to Jail", "Green", "Green", "Chance", "Green", "Railroad", "Chance", "Blue", "Tax",
			"Blue" };
	private int position;

	private int[] stats;

	public Monopoly() {
		position = 0;
		stats = new int[10];
	}

	public int getPosition() {
		return position;
	}

	public int[] getStatsList() {
		return stats;
	}

	public String percent(Monopoly m, String name, int denom, int index) {
		return name + ": " + Math.round(1.0 * m.getStatsList()[index] / denom * 10000) / ((double) 100) + "% " + "on "
				+ m.getStatsList()[index] + " times landed";
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getStats(int denom) {
		String percents = "";
		percents += (percent(this, "Brown", denom, 0) + "\n");
		percents += (percent(this, "RailRoad", denom, 1) + "\n");
		percents += (percent(this, "LightBlue", denom, 2) + "\n");
		percents += (percent(this, "Utilites", denom, 3) + "\n");
		percents += (percent(this, "Pink", denom, 4) + "\n");
		percents += (percent(this, "Orange", denom, 5) + "\n");
		percents += (percent(this, "Red", denom, 6) + "\n");
		percents += (percent(this, "Yellow", denom, 7) + "\n");
		percents += (percent(this, "Green", denom, 8) + "\n");
		percents += (percent(this, "Blue", denom, 9) + "\n");
		return percents;
	}

	public void setStats(int[] stats) {
		this.stats = stats;
	}

	public static String[] getTypeproperty() {
		return typeProperty;
	}

	private String fillers(int span, String print) {
		String str = "";
		for (int i = 0; i < span; i++) {
			str += print;
		}
		return str;
	}

	private String positionMarker(int propPosition) {
		if (propPosition == position) {
			return typeProperty[propPosition].toUpperCase();
		} else {
			return typeProperty[propPosition];
		}
	}

	private String vertSpots(int constant, int temp) {
		String board = "";

		for (int i = 1; i <= 9; i++) {
			board += "\n" + positionMarker(constant - i);
			board += fillers(temp - typeProperty[constant - i].length() - 9, " ");
			board += positionMarker(constant + 10 + i);
			board += "\n";
			if (i == 5) {
				board += fillers(8, "_");
				board += fillers(temp / 2 - 8 - 9, " ");
				board += "MONOPOLY";
				board += fillers(temp / 2 - 8, " ");
				board += fillers(8, "_") + "\n";
			} else if (i == 9) {
				board += fillers(temp - 1, "_") + "\n";
			} else {
				board += fillers(8, "_");
				board += fillers(temp - 8 - 9, " ");
				board += fillers(8, "_") + "\n";
			}
		}
		return board;
	}

	public String toString() {
		String board = "\n|  ";
		for (int count = 20; count <= 30; count++) {
			if (count == position) {
				board += typeProperty[count].toUpperCase() + "  |   ";
			} else {
				board += typeProperty[count] + "  |   ";
			}
		}
		board += "\n";
		int constant = 20;
		int temp = board.length();
		board += fillers(temp - 1, "_") + "\n";

		board += vertSpots(constant, temp);

		board += "\n|  ";

		for (int count = 10; count >= 0; count--) {
			if (count == position) {
				board += typeProperty[count].toUpperCase() + "  |  ";
			} else {
				board += typeProperty[count] + "  |  ";
			}
		}
		board += "\n";

		return board;
	}

	private void countUpdater() {
		switch (typeProperty[position]) {
		case "Brown":
			stats[0]++;
			break;
		case "Railroad":
			stats[1]++;
			break;
		case "LightBlue":
			stats[2]++;
			break;
		case "Utility":
			stats[3]++;
			break;
		case "Pink":
			stats[4]++;
			break;
		case "Orange":
			stats[5]++;
			break;
		case "Red":
			stats[6]++;
			break;
		case "Yellow":
			stats[7]++;
			break;
		case "Green":
			stats[8]++;
			break;
		case "Blue":
			stats[9]++;
			break;
		}
	}

	private int nextProperty(String property) {
		int index = 0;
		while (true) {
			if (position + index > 39) {
				index = position * -1;
			}
			if (typeProperty[position + index].equals(property)) {
				return position + index;
			}
			index++;
		}

	}

	private void chance() {
		int randNum = (int) (Math.random() * 32 + 1);
		switch (randNum) {
		case 1:
			position = 0; // back to go
			break;
		case 2:
			position = 39; // last Blue
			break;
		case 3:
			position = 24; // red
			break;
		case 4:
			position = 11; // pink
			break;
		case 5:
			position = nextProperty("Railroad");
			break;
		case 6:
			position = nextProperty("Railroad");
			break;
		case 7:
			position = nextProperty("Utility");
			break;
		case 8:
			if (position - 3 < 0) {
				position = 39;
			} else {
				position -= 3;
			}
			break;
		case 9:
			position = 10;
			break;
		case 10:
			position = 5;
			break;
		case 11:
			position = 0;
			break;
		case 12:
			position = 10;
			break;

		}
	}

	public String move() {
		boolean chance = false;
		boolean jail = false;
		int chanceIndex = 0;
		int die1 = (int) (Math.random() * 6 + 1);
		int die2 = (int) (Math.random() * 6 + 1);
		int sum = die1 + die2;
		if (position + sum > 39) {
			position = (position + sum) - 40;
		} else {
			position += sum;
		}
		if (typeProperty[position].equals("Chance")) {
			chanceIndex = position;
			chance();
			chance = true;
		}
		if (typeProperty[position].equals("Go to Jail")) {
			position = 10;
			jail = true;
		}
		countUpdater();
		if (chance && !typeProperty[position].equals("Chance")) {
			return "chance" + ";" + chanceIndex + " and went to " + typeProperty[position] + ";" + position;
		}
		if (jail) {
			return "GO TO JAIL, " + typeProperty[position] + ";" + position;
		}
		return typeProperty[position] + ";" + position;
	}

	public String move(int moves) {
		boolean chance = false;
		boolean jail = false;
		if (position + moves > 39) {
			position = (position + moves) - 40;
		} else {
			position += moves;
		}
		if (typeProperty[position].equals("Chance")) {
			chance();
			chance = true;
		}
		if (typeProperty[position].equals("Go to Jail")) {
			position = 10;
			jail = true;
		}
		countUpdater();
		if (chance && !typeProperty[position].equals("Chance")) {
			return "chance and went to " + typeProperty[position] + ";" + position;
		}
		if (jail) {
			return "GO TO JAIL, " + typeProperty[position] + ";" + position;
		}
		return typeProperty[position] + ";" + position;
	}

	public void reset() {
		stats = new int[10];
	}

}
