package codes;

public class Dealer {
	
	private DiamondAuctionHand hand;
	private final int[] pointsForRank = {6, 12, 18, 24, 30, 36, 42, 48, 54, 60, 66, 72, 78};
	
	Dealer() {
		hand = new DiamondAuctionHand("Diamonds");
		hand.shuffle();
	}
	
	public Card auction() {
		return hand.draw();
	}
	
	private int highestValue(int numberOfCards, Card[] cardsBid) {
		int max = 0;
		for(int i = 0; i < numberOfCards; i++) {
			if(cardsBid[i].value() > max) {
				max = cardsBid[i].value();
			}
		}
		return max;
	}
	
	private int numberOfWinners(int numberOfPlayers, Card[] cardsBid, int highestValue) {
		int num = 0;
		for(int i = 0; i < numberOfPlayers; i++) {
			if(cardsBid[i].value() == highestValue) {
				num++;				
			}
		}
		return num;
	}
	
	private int pointsPerWinner(int highestValue, int numberOfWinners) {
		return pointsForRank[highestValue - 2] / numberOfWinners;
	}
	
	private int[] allocatePoints(int numberOfPlayers, Card[] cardsBid, int highestValue, int pointsPerWinner) {
		int points[] = new int[numberOfPlayers];
		for(int i = 0; i < numberOfPlayers; i++) {
			if(cardsBid[i].value() == highestValue) {
				points[i] = pointsPerWinner;				
			}
		}
		return points;
	}
	
	public int[] hammer(int numberOfPlayers, Card[] cardsBid) {
		int max = highestValue(numberOfPlayers, cardsBid);
		int num = numberOfWinners(numberOfPlayers, cardsBid, max);
		int ppw = pointsPerWinner(max, num); 
		int[] pts = allocatePoints(numberOfPlayers, cardsBid, max, ppw);
		return pts;
	}
	
}
