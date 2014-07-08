/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rajprasad
 */
public class Game {
    
    public static void main(String[] args) {
        int numofplayers = 3;
        
        numofplayers = UI.AskIntInput("Enter number of players");
        
        DiamondAuctionGame game= new DiamondAuctionGame(numofplayers);
        game.Start();
        game.Run(); 
        game.End();
        //game.DeclareResults();
    }


}
