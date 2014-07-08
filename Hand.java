/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author rajprasad
 */
public class Hand {
    protected ArrayList<Card> cards;
    protected Random gen;
    
    Hand(Card[] cards){
        this.cards = new ArrayList<Card>();
        for(int i = 0; i < cards.length; ++i)
            AddToHand((cards[i]));
        gen = new Random(System.currentTimeMillis());
    }
    
    Hand(){
        cards = new ArrayList<Card>();
    }
    public void AddToHand(Card c){
        cards.add(c);
    }
    
    public Card DrawCard(){
        if(cards.size() == 0) return null;
        int rNum = gen.nextInt(cards.size());
        return cards.remove(rNum);
    }
    
    public Card DrawTopCard(){
        if(cards.size() == 0) return null;
        Card c = cards.remove(0);  
        return c;
    }
    
    
    public ArrayList<Card> SortBySuit (){
    //    Collections.sort(cards, new suitComparator());
        return cards;
    }
    
    public ArrayList<Card> SortByRank (){
        Collections.sort(cards);
        return cards;
    }
    
    public boolean hasSameLength (Card[] c1, Card[] c2){
        return c1.length == c2.length;
    }
    
    public ArrayList<Card> compare(ArrayList<Card> cs1, ArrayList<Card> cs2){
        Card c1 =  Collections.max(cs1);
        Card c2 =  Collections.max(cs2);
        
        while(c1.compareTo(c2) == 0){
            cs1.remove(c1);
            cs2.remove(c2);
            c1 =  Collections.max(cs1);
            c2 =  Collections.max(cs2);
        }
        
        if(c1.compareTo(c2) > 0) return cs1;
        else return cs2;
        
    }

    public ArrayList<Card> Shuffle(){
        int[] arr = new int[cards.size()];
        for(int i = 0; i < arr.length; ++i)
            arr[i] = -1;
        
        arr = FillRandNums(arr);
        CreateShuffledDeck(arr);
        return cards;
    }
    
    private void CreateShuffledDeck(int[] randNums){
        ArrayList<Card> cs = new ArrayList<Card>();
        for(int i = 0; i < cards.size(); ++i){
            cs.add(cards.get(randNums[i]));
        }
        cards = cs;
    }
    
    private int[] FillRandNums(int[] emptyArr){
        for(int i = 0; i < emptyArr.length; ++i){
            int p = gen.nextInt(cards.size());
            while(emptyArr[p] != -1){
                p = gen.nextInt(cards.size());
            }
            emptyArr[p] = i;
        }
        return emptyArr;
    }
    
}
