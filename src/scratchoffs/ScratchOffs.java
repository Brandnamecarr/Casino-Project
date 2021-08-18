/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchoffs;

import casino.Player;
import constants.Constants;
import java.util.Scanner;

/**
 *
 * @author Karin Whiting UCF COP 3330
 */
public class ScratchOffs
{
    private Player player;
    private OneDollar one;
    private FiveDollar five;
    private TenDollar ten;
    private boolean play;
    private Scanner scan;

    public ScratchOffs(Player player)
    {
        this.player = player;
        play = true;
        scan = new Scanner(System.in);
    }
    
    public void play()
    {
        int type;
        int quantity;
        int input;
        
        System.out.println("Let's play scratch off tickets!");
        System.out.println("Players can select from One Dollar, Five Dollar and Ten Dollars tickets");
        System.out.println("Prizes are based on the ticket selected");
              
        if(player.getCash() < Constants.ONE_DOL)
        {
            System.out.println("You are out of cash, you cannot play");
            play = false;
        }
                
        while(play)
        {
            System.out.println("Which type of scratch off would you like (1 = One Dollar, 5 = Five Dollar, 10 = Ten Dollar)?");
            type = scan.nextInt(); 

            System.out.println("How many scratch offs would you like?");
            quantity = scan.nextInt(); 

            System.out.println("Getting your scratch offs...");            

            for(int q = 0; q < quantity; q++)
            {
                switch(type)
                {
                    case Constants.ONE_DOL:
                        player.setCash(player.getCash() - Constants.ONE_DOL);
                        one = new OneDollar(player);
                        break;
                    case Constants.FIVE_DOL:
                        player.setCash(player.getCash() - Constants.FIVE_DOL);
                        five = new FiveDollar(player);
                        break;
                    case Constants.TEN_DOL:
                        player.setCash(player.getCash() - Constants.TEN_DOL);
                        ten = new TenDollar(player);
                        break;
                }   
            }            
            
            System.out.println("CASH = $" + player.getCash());
            
            // only continue looping if the player has cash
            if(player.getCash() >= Constants.ONE_DOL)
            {
                System.out.println("Would you like to play again (Yes = 1, No = 0)? ");
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

        System.out.println("Thank you for playing scratch offs at Knights Casino! Your cash out is $" + player.getCash());                
    }
}
