package codes;

import java.io.*;
import java.util.*;
import javax.script.*;

public class PlayerWithStrategy extends Player {

	private Integer[] mapping;
	private ArrayList<Integer> leftOvers;

	public PlayerWithStrategy(String name, String suit, String fileName)
			throws FileNotFoundException, IOException, ScriptException {
		super(name, suit);
		Parser parser = new Parser(fileName);
		mapping = parser.parseFile();
		leftOvers = new ArrayList<Integer>();
		populateLeftOvers();
	}

	private void populateLeftOvers() {
		List<Integer> mapping = Arrays.asList(this.mapping);
		for (int i = 2; i <= 14; i++) {
			Integer j = new Integer(i);
			if (!mapping.contains(j)) {
				leftOvers.add(j);
			}
		}
	}

	private Card getFreeCard() {
		Collections.sort(leftOvers);
		int value = leftOvers.remove(0);
		Card card = new Card(hand.getSuit(), value);
		hand.removeCard(card);
		return card;
	}

	@Override
	public Card bid(Card auctionedCard) {
		int m = auctionedCard.value();
		int bidCardValue = mapping[m];
		if (bidCardValue >= 2 && bidCardValue <= 14) {
			Card card = hand.getCard(bidCardValue);
			if(card == null) {
				return getFreeCard();
			}
			else {
				hand.removeCard(card);
				return card;
			}
		}
		return getFreeCard();
	}

	class Parser {
		String filename;

		Parser(String filename) {
			this.filename = filename;
		}

		public Integer[] parseFile() throws FileNotFoundException, IOException,
				ScriptException {
			Integer mappings[] = new Integer[15];
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				String word[] = line.split(":");
				String numbers[] = word[0].split(",");
				ScriptEngineManager mgr = new ScriptEngineManager();
				ScriptEngine engine = mgr.getEngineByName("JavaScript");
				for (String number : numbers) {
					String rule = word[1];
					String wildCard = "x";
					String finalRule = rule.replaceAll(wildCard, number);
					Double map = (Double) engine.eval(finalRule);
					int mapValue = map.intValue();
					mappings[Integer.parseInt(number)] = mapValue;
				}
			}
			br.close();
			return mappings;
		}
	}

}
