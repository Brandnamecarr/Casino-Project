/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slots;

import casino.Player;
import java.util.Random;
import java.util.Scanner;
import constants.Constants;

/**
 *
 * @author Karin Whiting UCF COP 3330
 */
public class Slots
{
    // member variables
    private static final int SLOTS = 3;
    private final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    private char[] spins;
    private Random rand;
    private Player player;
    private boolean play;
    private Scanner scan;
    private int cash;
    
    // custom constructor
    public Slots(Player player)
    {
        this.player = player;
        scan = new Scanner(System.in);
        play = true;
        rand = new Random();
        spins = new char[3];        
    }
    
    public void play()
    {
        int input;
        
        System.out.println("Let's play the slot machines!");
        System.out.println("Your cash balance is $" + player.getCash());
        System.out.println("The bet is $" + Constants.BET);
        System.out.println("Match two symbols to win $" + Constants.PAIR_PAYOUT);
        System.out.println("Match all three symbols to win $" + Constants.TRIPLE_PAYOUT); 
              
        if(player.getCash() < Constants.BET)
        {
            System.out.println("You are out of cash, you cannot play");
            play = false;
        }
                
        while(play)
        {
            // decrease player's cash by the bet amount
            player.setCash(player.getCash() - Constants.BET);
            
            System.out.println("Spinning...");
            
            // get the random symbols
            for(int s = 0; s < SLOTS; s++)
            {
                spins[s] = randomSymbol(); 
                System.out.print(spins[s] + " ");
            }

            System.out.println("");
            
            // three symbols matched
            if(spins[0] == spins[1] && spins[0] == spins[2])
            {
                System.out.println("Three symbols matched, you won $50!");
                player.setCash(player.getCash() + Constants.TRIPLE_PAYOUT);
            }
            // two symbols matched
            else if(spins[0] == spins[1] || spins[0] == spins[2] || spins[1] == spins[2])
            {
                System.out.println("Two symbols matched, you won $5!");
                player.setCash(player.getCash() + Constants.PAIR_PAYOUT);
            }
            else
            {
                System.out.println("No symbols matched");
            }

            System.out.println("CASH = $" + player.getCash());
            
            // only continue looping if the player has cash
            if(player.getCash() >= Constants.BET)
            {
                System.out.println("Would you like to spin again (Yes = 1, No = 0)? ");
                input = scan.nextInt();
                
                if(input == 1)
                    play = true;
                else
                    play = false;
            }
            else
            {
                break;
            }
        }

        System.out.println("Thank you for playing slots at Knights Casino! Your cash out is $" + player.getCash());            
    }
    
    private char randomSymbol()
    {
        int num;
        char symbol = ' ';
        
        num = rand.nextInt(SYMBOLS.length);
        
        switch(num)
        {
            case 0:
                symbol = SYMBOLS[0];
                break;
            case 1:
                symbol = SYMBOLS[1];
                break;
            case 2:
                symbol = SYMBOLS[2];
                break;
            case 3:
                symbol = SYMBOLS[3];
                break;
            case 4:
                symbol = SYMBOLS[4];
                break;
            case 5:
                symbol = SYMBOLS[5];
                break;
        }
        
        return symbol;
    }
}
