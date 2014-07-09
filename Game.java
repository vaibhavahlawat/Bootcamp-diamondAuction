package codes;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.script.ScriptException;

public class Game {

	private Dealer dealer;
	private final int numberOfPlayers;
	private final boolean isInteractive;
	private final int numberOfPlayersWithStrategy;
	private Player[] players;
	private int[] pointsTable;
	private final String[] names = { "Tom", "Dick", "Harry" };
	private final String[] suits = { "Spades", "Hearts", "Clubs" };
	private ConsoleIO cio;

	public Game(int numberOfPlayers, boolean isInteractive, int numberOfPlayersWithStrategy, String[] fileNames)
			throws FileNotFoundException, IOException, ScriptException {
		assert (numberOfPlayers == 2 || numberOfPlayers == 3);
		dealer = new Dealer();
		this.numberOfPlayers = numberOfPlayers;
		this.isInteractive = isInteractive;
		this.numberOfPlayersWithStrategy = numberOfPlayersWithStrategy;
		players = new Player[numberOfPlayers];
		pointsTable = new int[numberOfPlayers];
		int counter = 0;
		if (isInteractive) {
			players[counter] = new InteractivePlayer(names[counter],
					suits[counter]);
		}
		counter++;
		for (int i = 0; i < numberOfPlayersWithStrategy; i++) {
			players[counter] = new PlayerWithStrategy(names[counter],
					suits[counter], fileNames[i]);
			counter++;
		}
		while (counter < numberOfPlayers) {
			players[counter] = new Player(names[counter], suits[counter]);
			counter++;
		}
		cio = new ConsoleIO();
	}


	public void play() {
		Card auctionedCard;
		Card[] cardsBid = new Card[numberOfPlayers];
		for (int round = 1; round <= 13; round++) {
			cio.printLine("ROUND " + round);
			auctionedCard = dealer.auction();
			cio.printLine(auctionedCard.toString()
					+ " is up for auction, place your bids.");
			for (int j = 0; j < numberOfPlayers; j++) {
				cardsBid[j] = players[j].bid(auctionedCard);
				cio.printLine(players[j].getName() + " bids "
						+ cardsBid[j].toString());
			}
			int[] points = dealer.hammer(numberOfPlayers, cardsBid);
			this.updatePointsTable(points);
			this.displayPointsTable();
		}
		this.stop();
	}

	public void stop() {
		cio.printLine("Game over.");
		this.declareResult();
	}

	private void updatePointsTable(int[] points) {
		for (int i = 0; i < numberOfPlayers; i++) {
			pointsTable[i] += points[i];
		}
	}

	private void displayPointsTable() {
		cio.printLine("-------------");
		cio.printLine("Points Table");
		for (int i = 0; i < numberOfPlayers; i++) {
			cio.printLine(players[i].getName() + " \t " + pointsTable[i]);
		}
		cio.printLine("-------------");
	}

	private void declareResult() {
		// TODO : implement this method		
	}
	

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ScriptException {
		Game game = new Game(2, true, 1, new String[]{"C:/Users/akanand/workspace/DiamondAuctionV2/src/codes/strategy.txt"});
		game.play();
	}

}
