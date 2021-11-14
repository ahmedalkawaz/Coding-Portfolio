
public class Card {
	private int rank;
	private char suit;
	// 1 & 3 = black, 2 & 4 = red

	public Card(int rank, char suit) {
		super();
		this.rank = rank;
		this.suit = suit;

	}

	public boolean getColor() {
		if (suit % 2 == 0) {
			return true;
		}
		return false;
	}

	public char getChar() {
		switch (rank) {
		default:
			return '?';
		case 2:
			return '2';
		case 3:
			return '3';
		case 4:
			return '4';
		case 5:
			return '5';
		case 6:
			return '6';
		case 7:
			return '7';
		case 8:
			return '8';
		case 9:
			return '9';
		case 10:
			return 'T';
		case 11:
			return 'J';
		case 12:
			return 'Q';
		case 13:
			return 'K';
		case 14:
			return 'A';
		}
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return "Card [rank=" + rank + ", suit=" + suit + "]";
	}

}