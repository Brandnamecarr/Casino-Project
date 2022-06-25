package casino;

import blackjack.Card;
import constants.Constants;
import java.util.ArrayList;
import java.util.Scanner;
import constants.Constants.Face;

public class Player implements IPlayer
{
    // member variables
    private String name;
    private int cash;
    private Scanner scan;
    private ArrayList<Card> hand;
    private int score;
    private boolean busted;
    private boolean play;
    
    public Player()
    {
//        scan = new Scanner(System.in);
//        
//        String player;
//        int money;
//        
//        System.out.println("Enter player name");
//        player = scan.next();
//        
//        System.out.println("Enter amount of money to play (minimum $10)");
//        money = scan.nextInt();
//                
//        setName(player);
//        setCash(money);
    }
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the cash
     */
    public int getCash()
    {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(int cash)
    {
        this.cash = cash;
    }

    /**
     * @return the hand
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }
    
    public boolean playAgain()
    {
        int choice;
        System.out.println("Player, play another hand? (0 = No, 1 = Yes)");
        choice = scan.nextInt();
//        System.out.println("Player's choice " + choice);
        
        if(choice == Constants.HIT)
            return true;
        else
            return false;        
    }
    
    public int hitOrStand()
    {
        int choice;
        
        System.out.println("Player, another card? (0 = STAND, 1 = HIT)");
        choice = scan.nextInt();
//        System.out.println("Player's choice " + choice);
        if(choice == Constants.HIT)
            return Constants.HIT;
        else 
            return Constants.STAND;
    }

    /**
     * @return the score
     */
    public int getScore()
    {
        score = 0;
        
        for(Card c : hand)
        {
//            System.out.println(c.toString());
            score += c.getValue();
        }
        
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * @return the busted
     */
    public boolean isBusted()
    {
        return busted;
    }

    /**
     * @param busted the busted to set
     */
    public void setBusted(boolean busted)
    {
        this.busted = busted;
    }
}
