/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codes;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author rajprasad
 */
public class UI {
    private static Scanner scanner = new Scanner(System.in);
    private static PrintStream console = System.out;
    
    public static Integer AskIntInput(String msg){
        console.println(msg);
        int input = scanner.nextInt();
        return input;
    }
    
    public static String AskStringInput(String msg){
        console.println(msg);
        String input = scanner.next();
        return input;
    }
    
    public static void PrintMsg(String msg){
        console.println(msg);
    }
    
    public static void PrintColoumnisedMsg(String msg, int width, int justf){
        switch(justf){
            case 1:
                console.printf("%-" + width + "s", msg);
                break;
            case 0:
                    console.printf("%" + width + "s", msg);
                break;
            
        }
        
    }
    
}
