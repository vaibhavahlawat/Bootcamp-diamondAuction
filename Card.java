package codes;

public class Card implements Comparable<Card> {
	
	private final String suit;
	private final String rank;
	private final String[] order = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Card(String suit, int value) {
		this.suit = suit;
		this.rank = order[value - 2];
	}
	
	public String toString() {
		return rank + " of " + suit;
	}
	
	public int value() {
		for(int i = 0; i < order.length; i++) {
			if(rank.equals(order[i])) {
				return i + 2;
			}
		}
		return -1;
	}
	
	public int compareTo(Card card) {
		return this.value() - card.value();
	}

}
