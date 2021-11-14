

public class Driver {

	public static void main(String[] args) {
		Hand h1 = new Hand();
		Hand h2 = new Hand();
		h1.deal();
		h2.deal();
		System.out.println(h1 + " VS " + h2 + ": " + h1.compareTo(h2));

		System.out.println();

		h1.deal();
		h2.deal();
		System.out.println(h1 + " VS " + h2 + ": " + h1.compareTo(h2));

		System.out.println();

		h1 = new Hand(new Card(5, 's'), new Card(5, 's'), new Card(5, 's'));
		h2 = new Hand(new Card(5, 's'), new Card(5, 's'), new Card(5, 's'));
		eval(h1, h2);

		h1 = new Hand(new Card(5, 's'), new Card(5, 's'), new Card(1, 'd'));
		h2 = new Hand(new Card(5, 's'), new Card(5, 's'), new Card(6, 'd'));
		eval(h1, h2);

		h1 = new Hand(new Card(5, 's'), new Card(5, 'c'), new Card(5, 's'));
		h2 = new Hand(new Card(5, 'd'), new Card(5, 's'), new Card(5, 's'));
		eval(h1, h2);

		h1 = new Hand(new Card(5, 's'), new Card(5, 's'), new Card(6, 'd'));
		h2 = new Hand(new Card(5, 's'), new Card(5, 's'), new Card(1, 'd'));
		eval(h1, h2);

		h1 = new Hand(new Card(12, 'h'), new Card(13, 'h'), new Card(14, 'h'));
		h2 = new Hand(new Card(5, 'd'), new Card(7, 's'), new Card(1, 'h'));
		eval(h1, h2);

		h1 = new Hand(new Card(7, 'h'), new Card(5, 'h'), new Card(3, 'h'));
		h2 = new Hand(new Card(2, 'c'), new Card(3, 's'), new Card(4, 'c'));
		eval(h1, h2);

		h1 = new Hand(new Card(2, 'h'), new Card(2, 'h'), new Card(2, 'h'));
		h2 = new Hand(new Card(5, 'd'), new Card(5, 'h'), new Card(1, 'c'));
		eval(h1, h2);

		h1 = new Hand(new Card(2, 'h'), new Card(3, 'h'), new Card(4, 'h'));
		h2 = new Hand(new Card(4, 'd'), new Card(4, 's'), new Card(3, 'h'));
		eval(h1, h2);

		h1 = new Hand(new Card(12, 'h'), new Card(13, 'h'), new Card(14, 'h'));
		h2 = new Hand(new Card(12, 's'), new Card(13, 's'), new Card(14, 's'));
		eval(h1, h2);

		h1 = new Hand(new Card(12, 'h'), new Card(12, 'h'), new Card(12, 'h'));
		h2 = new Hand(new Card(5, 'h'), new Card(7, 'h'), new Card(1, 'h'));
		eval(h1, h2);

	}

	public static void eval(Hand h1, Hand h2) {
		if (h1.compareTo(h2) > 0) {
			System.out.print("Hand one's " + h1.value() + " beats " + "Hand two's " + h2.value() + " by ");
		} else if (h1.compareTo(h2) < 0) {
			System.out.print("Hand one's " + h1.value() + " loses to " + "Hand two's " + h2.value() + " by ");
		} else {
			System.out.print("Hand one's " + h1.value() + " ties " + "Hand two's " + h2.value() + ": ");
		}
		System.out.println(h1.compareTo(h2));
		System.out.println();

	}
}
