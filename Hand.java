package codes;

import java.util.*;

public class Hand {

	protected List<Card> hand;
	
	Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void clear() {
		hand.clear();
	}
	
	public boolean contains(Card card) {
		return hand.contains(card);
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public void removeCard(Card card) {
		hand.remove(card);
	}
	
	public void removeCard(int position) {
		hand.remove(position);
	}
	
	public int getCardCount() {
		return hand.size();
	}
	
	public Card getCard(int value) {
		Iterator<Card> it = hand.iterator();
		while(it.hasNext()) {
			Card card = (Card)it.next();
			if(card.value() == value) {
				return card;
			}
		}
		return null;
	}
	
	public void sortBySuit() {
		//TODO : implement this method
	}
	
	public void sortByRank() {
		Collections.sort(hand);
	}
	
	public String toString() {
		String description = "[" + hand.get(0).toString();
		for(int i = 1; i < hand.size(); i++) {
			description += "; " + hand.get(i).toString();
		}		
		description += "]";
		return description;
	}
	
}
