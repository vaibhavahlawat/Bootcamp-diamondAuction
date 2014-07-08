/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rajprasad
 */
public class Pack {
    protected Card[] cards;
    protected final int SUITSIZE = 13;
    protected final int NUMSUITS = 4;
    public static enum Type {ACEFIRST, ACELAST};
    
    protected Pack.Type type;
    
    Pack(Pack.Type t){
        type = t;
        cards = new Card[SUITSIZE * NUMSUITS];
        int off = 0;
        
        switch(t){
            case ACEFIRST: off = 1; break;
            case ACELAST: off = 2; break;
        }
        
        try{
            int ctr = 0;
            for(Card.Suit s : Card.Suit.values()){
                for(int i = 0; i < SUITSIZE; ++i){
                    cards[ctr * SUITSIZE + i] = new Card(i + off, s);
                //    UI.PrintMsg("Constructed " + (ctr * SUITSIZE + i));
                }
                    
                ctr++;
            }
        }catch(Exception ex){
            
            UI.PrintMsg(ex.getMessage());
            //ex.printStackTrace();
        }
    }
    
    
    public String toString(){
        String str = "";
        for(Card c: cards)
            str += cards;
        return str;
    }
    
    public void AddJokers(int num){
        Card[] c = new Card[cards.length + num];
        for(int i = 0; i < cards.length; ++i)
            c[i] = cards[i];
        
        for(int i = 0; i < num; ++i)
            c[SUITSIZE * NUMSUITS + i] = new Card();
        cards = c;
    }
    
    public Card[] GetCards(){
        return cards;
    }
    
    public Card[] GetSuiteCards(Card.Suit suit){
        Card[] suite = new Card[SUITSIZE];
        int ctr = 0;
        for(int i = 0; i < cards.length; ++i){
            //System.out.println(i + "\n");
            if(cards[i].getSuit() == suit)
                suite[ctr ++] = cards[i];
        }
        return suite;
    }
}