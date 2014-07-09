package codes;

public class Player {
	
	protected String name;
	protected DiamondAuctionHand hand;
	
	public Player(String name, String suit) {
		this.name = name;
		hand = new DiamondAuctionHand(suit);
	}
	
	public Card bid(Card auctionedCard) {
		hand.shuffle();
		return hand.draw();
	}
	
	public String getName() {
		return name;
	}
	
}
