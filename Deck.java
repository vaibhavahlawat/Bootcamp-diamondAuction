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
public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Pack> packs;
    private Random gen = new Random(System.currentTimeMillis());
    
    private void PutCardsTogether(){
        cards = new ArrayList<Card>();
        for(Pack p: packs){
            for(Card c : p.GetCards())
                cards.add(c);
        }
    }
    
    
    private void CreateShuffledDeck(int[] randNums){
        ArrayList<Card> cs = new ArrayList<Card>(cards.size());
        int ctr = 0;
        for(Card c: cs){
            c = cards.get(randNums[ctr]);
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
    
    public Deck(int n){
        packs = new ArrayList<Pack>();
        for(int i = 0 ; i < n ; ++i)
            packs.add(new Pack(Pack.Type.ACEFIRST));
        PutCardsTogether();
    }
    
    public Deck(Pack[] p){
        packs = new ArrayList<Pack>();
        for(int i = 0 ; i < p.length ; ++i)
            packs.add(p[i]);
        PutCardsTogether();
    }
    
    public Card DrawCard(){
        if(cards.size() == 0) return null;
        int rNum = gen.nextInt(cards.size());
        return cards.remove(rNum);
    }
    
    public Card DrawTopCard(){
        if(cards.size() == 0) return null;
        return cards.remove(0);
    }
    
    public ArrayList<Card> Shuffle(){
        int[] arr = new int[cards.size()];
        for(int i = 0; i < arr.length; ++i)
            arr[i] = -1;
        
        arr = FillRandNums(arr);
        CreateShuffledDeck(arr);
        return cards;
    }
    
     public String toString(){
        String str = "";
        for(Pack p : packs)
            str += p;
        return str;
    }
     
     public Hand[] Distribute(int numPlayers, int cardPerPlayer) throws Exception{
        if(numPlayers * cardPerPlayer > cards.size())
             throw new Exception("Too many players or cards per player");
        Hand[] h = new Hand[numPlayers];
        
        for(int i = 0 ; i < numPlayers; ++i)
            h[i] = new Hand();
        
        Shuffle();
        
        for(int i = 0; i < numPlayers * cardPerPlayer; ++i) {
            for(int j = 0; j < numPlayers; ++j) {
                if(i % numPlayers  == j)
                    h[j].AddToHand(cards.get(i));
            }   
        }
        return h;
        
     }
}
