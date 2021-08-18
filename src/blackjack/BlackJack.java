/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import casino.Player;
import constants.Constants;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Karin Whiting UCF COP 3330
 */
public class BlackJack
{
    private Player player;
    private boolean play;
    private Scanner scan;    
    private Deck deck;
    private Dealer dealer;
    private Card card;
 
    public BlackJack(Player player)
    {
        this.player = player;
        scan = new Scanner(System.in);
        play = true;   
        deck = new Deck();
        dealer = new Dealer();
        card = new Card();
    }
    
    public void play()
    {
        displayRules();
        
        if(player.getCash() < Constants.BJ_BET)
        {
            System.out.println("You are out of cash, you cannot play");
            play = false;
        }
                
        while(play)
        {
            System.out.println("Player's cash $" + player.getCash());
            
            if(player.getCash() < Constants.BJ_BET)
            {
                System.out.println("You are out of cash, you cannot play");
                play = false;
                break;
            }

            // decrease player's cash by the bet amount
            player.setCash(player.getCash() - Constants.BJ_BET);
            
            System.out.println("Dealing...");
            
            // reset the player and dealer hand and score
            player.setHand(new ArrayList());
            player.setScore(Constants.ZERO);
            player.setBusted(false);
            dealer.setHand(new ArrayList());
            dealer.setScore(Constants.ZERO);
            
            // deal the player two cards
            for(int c = 0; c < 2; c++)
            {
                card = dealer.deal(deck);
                card.toString();
                player.getHand().add(card);
            }

            System.out.println("Player's score " + player.getScore());
            
            // deal the dealer two cards 
            for(int c = 0; c < 2; c++)
            {
                card = dealer.deal(deck);
                dealer.getHand().add(card);
            }

            while(player.hitOrStand() == Constants.HIT)
            {
                card = dealer.deal(deck);
                player.getHand().add(card);      
                
                System.out.println("Player's score " + player.getScore());
                
                if(player.getScore() > Constants.BUST)
                {
                    System.out.println("Player busted! You lost the hand!");
                    player.setBusted(true);
                    break;
                }
            }

            if(player.isBusted())
            {
                play = player.playAgain();
                continue;                
            }
            else
            {
                System.out.println("Dealer's score " + dealer.getScore()); 

                while(dealer.hitOrStand() == Constants.HIT)
                {
                    card = dealer.deal(deck);
                    dealer.getHand().add(card);      

                    System.out.println("Dealer's score " + dealer.getScore());

                    if(dealer.getScore() > Constants.BUST)
                    {
                        System.out.println("Dealer busted!");
                        break;
                    }
                }            
            }
            
            results();

            play = player.playAgain();
        }
    }
    
    private void results()
    {
        System.out.println("Player's score " + player.getScore());
        System.out.println("Dealer's score " + dealer.getScore()); 
        
        if(dealer.getScore() > Constants.BUST)
        {
            System.out.println("Dealer BUSTED! Player wins $" + Constants.BJ_BET);
            player.setCash(player.getCash() + Constants.BJ_BET);
        }
        // if it is PUSH, player receives their bet
        else if(dealer.getScore() == player.getScore())
        {
            System.out.println("It was a PUSH! Player wins $"  + Constants.BJ_BET);
            player.setCash(player.getCash() + Constants.BJ_BET);
        }
        // if the player WINS, they double their bet
        else if(player.getScore() > dealer.getScore())
        {
            System.out.println("Player WINS the hand! Player wins $" + Constants.BJ_WIN);
            player.setCash(player.getCash() + Constants.BJ_WIN);
        }
        // the dealer won, player gets nothing
        else
        {
            System.out.println("Dealer WINS the hand!");
        }
    }
    
    private void displayRules()
    {
        System.out.println("Let's play Black Jack!");
        System.out.println("Your cash balance is $" + player.getCash());
        System.out.println("The bet is $" + Constants.BJ_BET);        
        System.out.println("Dealer must HIT if their score is 16 or less");
        System.out.println("Dealer must STAND if their score is 17 or higher");
        System.out.println("If the player wins the hand, they receive $" + Constants.BJ_WIN);
        System.out.println("If the dealer wins the hand, the $" + Constants.BJ_BET + " bet is lost");
        System.out.println("If it is a PUSH, the player keeps their $" + Constants.BJ_BET + " bet");      
        
    }
}