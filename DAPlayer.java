/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;

/**
 *
 * @author rajprasad
 */
public class DAPlayer extends Hand implements Comparable<DAPlayer>{
    Integer score = 0;
    String name;
    boolean isHuman;
    
    public DAPlayer(String name, int score, Card[] cards, boolean isHuman){
       super(cards);
       this.score = score;
       this.name = name;
       this.isHuman = isHuman;
    }
    
    public void IncreaseScore( int points){
        score += points;
    }
    
    private Card BotBid (Card c, int numPlayers){
        int desiredRank = c.getRank() * (numPlayers - 1)/ numPlayers;
        for(Card card : cards){
            if (card.getRank() != desiredRank){}
            cards.remove(card);
            return card;
        }
        return DrawCard();
    }
    
    public Card nextBid(Card c, int numPlayers){
        if(!isHuman) return BotBid(c, numPlayers);
        
        UI.PrintMsg(c + " is put for auction.");
        String possession = "";
        for(int i = 0; i < cards.size(); ++i)
            possession += "<" + (i+1) + "> " + cards.get(i) + "   ";
        
        UI.PrintMsg(possession);
        
        int num = -1;
        while(num < 0 || num > cards.size())
            num = UI.AskIntInput("Place your bid. Press 0 to exit.");
        
        if(num == 0) return null;
        return cards.remove(num - 1);
    }
    
    public int getScore(){
        return score;
    }
    
    public void PrintStatus(){
        UI.PrintColoumnisedMsg("Player:", 10, 1);
        UI.PrintColoumnisedMsg(name, 20, 0);
        UI.PrintColoumnisedMsg("\t\t", 10, 0);
        UI.PrintColoumnisedMsg("Score:", 10, 1);
        UI.PrintColoumnisedMsg(score + "", 6, 0);
        UI.PrintMsg("");
    }

    @Override
    public int compareTo(DAPlayer p) {
        if(score > p.getScore())    return 1;
        else if (score == p.getScore()) return 0;
        else return -1;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
