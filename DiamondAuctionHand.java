package codes;

import java.util.*;

public class DiamondAuctionHand extends Hand {

	private final String suit;
	private final String[] order = { "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "Jack", "Queen", "King", "Ace" };

	public DiamondAuctionHand(String suit) {
		super();
		this.suit = suit;
		for (int i = 0; i < 13; i++) {
			hand.add(new Card(suit, order[i]));
		}
	}

	public void shuffle() {
		Collections.shuffle(hand);
	}

	public Card draw() {
		return hand.remove(0);
	}
	
	public String getSuit() {
		return suit;
	}

}
