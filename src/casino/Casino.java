
package casino;

import blackjack.BlackJack;
import constants.Constants;
import java.util.Scanner;
import scratchoffs.ScratchOffs;
import slots.Slots;
import userInterface.CasinoUi;

public class Casino
{
    // member variables
    private static BlackJack blackJack;
    private static ScratchOffs scratchers;
    private static Slots slots;
    private static Player player;
    private static Scanner scan;
    private static CasinoUi ui;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int game = 0;  
        
        // instantiate the scanner object
        scan = new Scanner(System.in);

        // instantiate the player object
        player = new Player();
        
        ui = new CasinoUi(new Casino());
 
        
//        // display the game menu
//        game = displayMenu();
//
//        switch(game)
//        {
//            case Constants.BLACK_JACK:
//                blackJack = new BlackJack(getPlayer());
//                blackJack.play();
//                break;
//            case Constants.SCRATCH:
//                scratchers = new ScratchOffs(getPlayer());
//                scratchers.play();
//                break;
//            case Constants.SLOTS:
//                slots = new Slots(getPlayer());  
//                slots.play();
//                break;
//            default:
//                System.out.println("Invalid game selection, try again");
//        }
    }

    private static int displayMenu()
    {
        int select = 0;
        
        System.out.println("Welcome to Knights Casino!");
        System.out.println("");
        
        do
        {
            System.out.println("Select the game to play");
            System.out.println("1. Black Jack");
            System.out.println("2. Scratch Off Tickets");
            System.out.println("3. Slot Machines");

            System.out.println("Enter the number of your choice: ");
            select = scan.nextInt();
        
        } while(select < Constants.BLACK_JACK || select > Constants.SLOTS);
        
        return select;
    }

    /**
     * @return the player
     */
    public static Player getPlayer()
    {
        return player;
    }

    /**
     * @param aPlayer the player to set
     */
    public static void setPlayer(Player aPlayer)
    {
        player = aPlayer;
    }

    /**
     * @return the blackJack
     */
    public static BlackJack getBlackJack()
    {
        return blackJack;
    }

    /**
     * @param aBlackJack the blackJack to set
     */
    public static void setBlackJack(BlackJack aBlackJack)
    {
        blackJack = aBlackJack;
    }

    /**
     * @return the scratchers
     */
    public static ScratchOffs getScratchers()
    {
        return scratchers;
    }

    /**
     * @param aScratchers the scratchers to set
     */
    public static void setScratchers(ScratchOffs aScratchers)
    {
        scratchers = aScratchers;
    }

    /**
     * @return the slots
     */
    public static Slots getSlots()
    {
        return slots;
    }

    /**
     * @param aSlots the slots to set
     */
    public static void setSlots(Slots aSlots)
    {
        slots = aSlots;
    }
    
}
