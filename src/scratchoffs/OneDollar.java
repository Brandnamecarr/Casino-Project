/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchoffs;

import casino.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Karin Whiting UCF COP 3330
 */
public class OneDollar
{
    private char winningBonus;
    private int winningNumber;
    private ArrayList<Integer> playerNumbers;
    private ArrayList<Double> prizes;
    private ArrayList<Character> bonus;
    private Player player;
    private Random rand;
    private final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    private final int BASE = 20;
    private final int NUMS = 5;
    private final int SYMS = 2;
    private final double BONUS = 10.00;
    
    public OneDollar(Player player)
    {
        this.player = player;
        rand = new Random();       
        createTicket();
        displayTicket();
        cashTicket();
    }
    
    private void displayTicket()
    {
        System.out.printf("+------------------------------------------+\n");
        System.out.printf("| WINNING NUMBER        %2d%17s|\n", this.winningNumber, " ");
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| YOUR NUMBERS%29s|\n|", " ");

        for(Integer i : playerNumbers)
        {
            System.out.printf("%8d", i);
        }

        System.out.printf("  |\n");
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| PRIZES%35s|\n", " ");
        System.out.printf("|%42s|\n|", " ");

        for(Double i : prizes)
        {
            System.out.printf("%8.2f", i);
        }

        System.out.printf("  |\n|%42s|\n", " ");
        System.out.printf("| SYMBOLS%34s|\n", " ");
        System.out.printf("|%42s|\n|", " ");

        for(Character c : bonus)
        {
            System.out.printf("%8c", c);
        }

        System.out.printf("%26s|\n+------------------------------------------+\n", " ");    
    }
    
    private void cashTicket()
    {
        double cash = 0.0;

        System.out.println("Bonus symbol is " + winningBonus);

        for(int p = 0; p < playerNumbers.size(); p++)
        {
            if(playerNumbers.get(p) == winningNumber)
            {              
                cash += prizes.get(p);
            }
        }

        for(int b = 0; b < bonus.size(); b++)
        {
            if(bonus.get(b) == winningBonus)
            {
                cash += BONUS;
            }
        }
        
        System.out.printf("Bonus is worth $%.2f\n", BONUS);
        System.out.println("Your One Dollar Scratch Off won you $" + (int)cash);
        
        player.setCash(player.getCash() + (int)cash);
    }
    
    private void createTicket()
    {
        winningNumber = randomNumber();
        winningBonus = randomSymbol();
        
        playerNumbers = new ArrayList();
        prizes = new ArrayList();
        bonus = new ArrayList();
         
        for(int n = 0; n < NUMS; n++)
        {
            playerNumbers.add(randomNumber());
            prizes.add(randomPrize());
        }
        
        for(int s = 0; s < SYMS; s++)
        {
            bonus.add(randomSymbol());
        }
    }

    private int randomNumber()
    {
        int num;

        num = rand.nextInt(BASE) + 1;
        
        return num;
    }
    
    private double randomPrize()
    {
        double prize;
        
        prize = (double)(rand.nextInt(BASE) + 1);
        
        return prize;
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

