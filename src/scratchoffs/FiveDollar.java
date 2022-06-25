package scratchoffs;

import casino.Player;
import java.util.ArrayList;
import java.util.Random;

public class FiveDollar
{
    private char winningBonus;
    private ArrayList<Integer> winningNumbers;
    private ArrayList<Integer> playerNumbers;
    private ArrayList<Double> prizes;
    private ArrayList<Character> bonus;
    private Player player;
    private Random rand;
    
    private final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    private final int BASE = 40;
    private final int NUMS = 10;
    private final int SYMS = 2;
    private final double BONUS = 20.00;
    
    public FiveDollar(Player player)
    {
        this.player = player;
        rand = new Random();
        
        createTicket();
        displayTicket();
        cashTicket();
    }
    
    private void displayTicket()
    {    
        final int BREAK = 4;
        
        System.out.printf("+------------------------------------------+\n");
        System.out.printf("| WINNING NUMBER%6s", " ");

        for(Integer i : winningNumbers)
        {
            System.out.printf("%8d", i);
        }

        System.out.printf("%5s|\n|%42s|\n", " ", " ");
        System.out.printf("| YOUR NUMBERS%29s|\n|", " ");

        for(int p = 0; p < playerNumbers.size(); p++)
        {
            System.out.printf("%8d", playerNumbers.get(p));
            
            if(p == BREAK)
            {
                System.out.printf("%2s|\n|", " ");
            }
        }

        System.out.printf("  |\n");
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| PRIZES%35s|\n", " ");
        System.out.printf("|%42s|\n|", " ");

        for(int p = 0; p < prizes.size(); p++)
        {
            System.out.printf("%8.2f", prizes.get(p));
            
            if(p == BREAK)
            {
                System.out.printf("%2s|\n|", " ");
            }
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

        for(int w = 0; w < winningNumbers.size(); w++)
        {
            for(int p = 0; p < playerNumbers.size(); p++)
            {
                if(playerNumbers.get(p) == winningNumbers.get(w))
                {              
                    cash += prizes.get(p);
                }
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
        System.out.println("Your Five Dollar Scratch Off won you $" + (int)cash);
        
        player.setCash(player.getCash() + (int)cash);
    }
    
    private void createTicket()
    {
        final int WINS = 2;
        
        playerNumbers = new ArrayList();
        prizes = new ArrayList();
        bonus = new ArrayList();
        winningNumbers = new ArrayList();
        
        winningBonus = randomSymbol();
                 
        for(int w = 0; w < WINS; w++)
        {
            winningNumbers.add(randomNumber());
        }
        
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
