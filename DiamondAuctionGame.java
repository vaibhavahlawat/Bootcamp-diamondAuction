/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajprasad
 */
public class DiamondAuctionGame implements IGame{
    DADealer dealer = null;
    DAPlayer[] players;
    Card.Suit[] suits; //= new Card.Suit[] {Card.Suit.CLUB, Card.Suit.SPADE, Card.Suit.HEART}; 
    
    private final int ROUNDS = 13; 
    private final int MAXHUMANPLAYERS = 2; 
    protected final int MAGNIFYBY = 6 ;
    protected final int EASY = 5 * MAGNIFYBY;
    protected final int MEDIUM = 3 * MAGNIFYBY;
    protected final int HARD = 1 * MAGNIFYBY;
    protected final int EXTREME = 0;
    
    public DiamondAuctionGame(int numP){
        if(numP > MAXHUMANPLAYERS) throw new IllegalArgumentException(numP + ": Too many players for the game");
        players = new DAPlayer[numP + 1];
        suits = new Card.Suit[] {Card.Suit.CLUB, Card.Suit.SPADE, Card.Suit.HEART}; 
    }
    
    private void AllotSuits(String[] names){
        Pack p = new Pack(Pack.Type.ACELAST);
        dealer = new DADealer(p.GetSuiteCards(Card.Suit.DIAMOND)); 
    
        players[0] =  new DAPlayer(names[0], EXTREME, p.GetSuiteCards(suits[0]), false);
        
        for(int i = 1; i < players.length; ++i)
            players[i] =  new DAPlayer(names[i], EXTREME, p.GetSuiteCards(suits[i]), true);
    }
    
    private String CheckGameStatus(Card[] cards, DAPlayer[] players){
        String str = "";
        for(int i = 0; i < cards.length; ++i)
            str += cards[i] + "\t" + players[i].getScore() + "\t";
        return str;
    }      
    
    private void AwardPoints(Integer[] playerInds, int points){
        for(int i = 0; i < playerInds.length; ++i)
            players[playerInds[i]].IncreaseScore((points * MAGNIFYBY)/playerInds.length);
    }
    
    private void Auction(){
        Card c = dealer.DrawTopCard();
        
        Card[] cards = new Card[players.length];
        
        for(int i = 0; i < cards.length; ++i){
            cards[i] = players[i].nextBid(c, players.length);
            if(cards[i] == null){
                End();
                System.exit(0);
            }
                
        }
        
        Integer[] pInds = dealer.ChooseWinners(cards);
        AwardPoints(pInds, c.getRank());
    }
    
    private void PrintScores() {
        DAPlayer[] sortedPlayers = Arrays.copyOf(players, players.length);
        Arrays.sort(sortedPlayers);
        
        UI.PrintColoumnisedMsg("Position", 10, 1);
        UI.PrintColoumnisedMsg("Name", 20, 1);
        UI.PrintColoumnisedMsg("Score", 6, 0);
        UI.PrintMsg( "" );
        for(int i = sortedPlayers.length - 1; i >= 0; --i){
            UI.PrintColoumnisedMsg((sortedPlayers.length - i) + "", 10, 1);
            UI.PrintColoumnisedMsg(sortedPlayers[i] + "", 20, 1);
            UI.PrintColoumnisedMsg(sortedPlayers[i].getScore() + "", 6, 0);

            UI.PrintMsg( "" );
        }
    }
    
    @Override
    public void Start() {
        String[] names = new String[players.length];
        
        names[0] = "Computer";
        for(int i = 1; i < names.length; ++i)
            names[i] = UI.AskStringInput("Enter name " + (i));
        
        AllotSuits(names);
    }

    @Override
    public void Run (){
        dealer.Shuffle();
        
        for(int i = 0; i < ROUNDS; ++i){
            Auction();
            for(int j = 0; j < players.length; ++j)
                players[j].PrintStatus();
    
        }
    }

    
    @Override
    public void End() {
        PrintScores();
    }
}
