/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;

import java.util.HashMap;

/**
 *
 * @author rajprasad
 */
public class Card implements Comparable{
    private Integer rank;
    private Suit suit;

    
    public static enum Suit {SPADE, HEART, DIAMOND, CLUB, JOKER};
    private static String[] ranks = {"Joker", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    
    @Override
    public int compareTo(Object o) {
        if(o.getClass() != Card.class)
            throw new ClassCastException("Argument not of type Card");
        Card c = (Card)o;
        
        if(c.getRank() < rank)
            return 1;
        else if (c.getRank() == rank)
            return 0;
        else return -1; 
    }
    
    /**
     *
     */
    public Card(){
        suit = Suit.JOKER;
        rank = 0;
    }
    
    public Card(int rank, Card.Suit suit) throws Exception{
        this.suit = suit;
        if(rank < 1 || rank > 14) throw new Exception("Invalid rank");
        this.rank = rank;
    }
    
    public String toString(){
        return ranks[rank] + " of " + suit;
    }
    public Integer getRank(){
        return rank;
    }
    public Card.Suit getSuit(){
        return suit;
    }
}