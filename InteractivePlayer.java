package codes;

import java.util.*;

public class InteractivePlayer extends Player {
	
	private ConsoleIO cio; 	
	
	public InteractivePlayer(String name, String suit) {
		super(name, suit);
		cio = new ConsoleIO();
	}

	@Override
	public Card bid(Card auctionedCard) {
		cio.printLine("Enter your bid : ");
		cio.printLine(hand.toString());
		int bid = cio.readInt();
		Card card = hand.getCard(bid);
		if(card == null) {
			cio.printLine("INVALID BID !");
			return bid(auctionedCard);
		}
		hand.removeCard(card);
		return card;
	}
	
}
