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
public class Test {
    public static void main(String[] args){
        
        
        try{
            Card c = new Card(1, Card.Suit.CLUB);
            System.out.println(c);
        }catch(Exception ex){}
        
    }
}
