
// Apologies in advance for how messy the method placement is

public class Hand {
	private Card c1, c2, c3;

	public Hand(Card c1, Card c2, Card c3) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;

		order();
	}

	public Hand() {
		c1 = null;
		c2 = null;
		c3 = null;
	}

	public void deal() {
		randCards();
		notEqual();
		order();
	}

	private boolean straight() {
		return c2.getRank() == c1.getRank() + 1 && c3.getRank() == c1.getRank() + 2;
	}

	private boolean flush() {
		return c1.getSuit() == c2.getSuit() && c2.getSuit() == c3.getSuit();
	}

	private boolean pair() {
		return c1.getRank() == c2.getRank() || c1.getRank() == c3.getRank() || c2.getRank() == c3.getRank();
	}

	private boolean prial() {
		return c1.getRank() == c2.getRank() && c2.getRank() == c3.getRank();
	}

	private boolean straightFlush() {
		return straight() && flush();
	}

	private boolean handRank(String rank) {
		return this.value().substring(0, this.value().length() - 2).equals(rank);
	}

	private int valueNum(Hand h1) {
		if (h1.handRank("Straight Flush")) {
			return 6;
		}
		if (h1.handRank("Prial")) {
			return 5;
		}
		if (h1.handRank("Straight")) {
			return 4;
		}
		if (h1.handRank("Flush")) {
			return 3;
		}
		if (h1.handRank("Pair")) {
			return 2;
		}
		return 1;
	}

	private boolean allSameColor(Hand h1) {
		return h1.getC1().getColor() == h1.getC2().getColor() && h1.getC2().getColor() == h1.getC3().getColor();
	}

	public String value() {
		if (straightFlush()) {
			return "Straight Flush;" + c3.getChar();
		}
		if (prial()) {
			return "Prial;" + c3.getChar();
		}
		if (straight()) {
			return "Straight;" + c3.getChar();
		}
		if (flush()) {
			return "Flush;" + c3.getChar();
		}
		if (pair()) {
			return "Pair;" + c3.getChar();
		}
		return "High card;" + c3.getChar();
	}

	public void deal(Card c1, Card c2, Card c3) {
		if (!anyCardsEqual(c1, c2, c3)) {
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
		}
	}

	private void randCards() {
		int rank = (int) (Math.random() * 13 + 2);
		int suitnum = (int) (Math.random() * 4 + 1);
		c1 = new Card(rank, suitChar(suitnum));

		rank = (int) (Math.random() * 13 + 2);
		suitnum = (int) (Math.random() * 4 + 1);
		c2 = new Card(rank, suitChar(suitnum));

		rank = (int) (Math.random() * 13 + 2);
		suitnum = (int) (Math.random() * 4 + 1);
		c3 = new Card(rank, suitChar(suitnum));

	}

	private void order() {

		if (c1.getRank() > c2.getRank()) {
			Card temp = c1;
			c1 = c2;
			c2 = temp;
		}
		if (c2.getRank() > c3.getRank()) {
			Card temp = c2;
			c2 = c3;
			c3 = temp;
		}
		if (c1.getRank() > c2.getRank()) {
			Card temp = c1;
			c1 = c2;
			c2 = temp;
		}
	}

	private boolean isCardEqual(Card c1, Card c2) {
		return (c1.getRank() == c2.getRank() && c1.getSuit() == c2.getSuit());
	}

	private boolean anyCardsEqual(Card c1, Card c2, Card c3) {
		if (isCardEqual(c1, c2) || isCardEqual(c2, c3) || isCardEqual(c1, c3)) {
			return true;
		}
		return false;
	}

	private void notEqual() {
		if (anyCardsEqual(c1, c2, c3)) {
			while (anyCardsEqual(c1, c2, c3)) {
				randCards();
			}

		}
	}

	public boolean equal(Hand h1) {
		return this.compareTo(h1) == 0;
	}

	public int compareTo(Hand h1) {
		if (valueNum(this) != valueNum(h1)) {
			return valueNum(this) - valueNum(h1);
		} else if (this.getC3().getRank() > h1.getC3().getRank()) {
			return 10;
		} else if (h1.getC3().getRank() > this.getC3().getRank()) {
			return -10;
		} else if (allSameColor(this) && !allSameColor(h1)) {
			return 10;
		} else if (allSameColor(h1) && !allSameColor(this)) {
			return -10;
		}
		return 0;
	}

	public void fold() {
		c1 = null;
		c2 = null;
		c3 = null;
	}

	private char suitChar(int num) {
		if (num == 1) {
			return 'd';

		}
		if (num == 2) {
			return 'h';
		}
		if (num == 3) {
			return 's';
		}
		return 'c';

	}

	public Card getC1() {
		return c1;
	}

	public void setC1(Card c1) {
		this.c1 = c1;
	}

	public Card getC2() {
		return c2;
	}

	public void setC2(Card c2) {
		this.c2 = c2;
	}

	public Card getC3() {
		return c3;
	}

	public void setC3(Card c3) {
		this.c3 = c3;
	}

	@Override
	public String toString() {
		return "Hand [c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + "]";
	}

}