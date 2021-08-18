/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.HashSet;
import java.util.Set;
import constants.Constants.Color;
import constants.Constants.Face;
import constants.Constants.Suit;
import constants.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author kwhiting
 */

public class Deck
{
    private Set<Card> deck;
    
    public Deck()
    {
//        System.out.println("****************************");
//        System.out.println("Generating the deck of cards");
//        System.out.println("****************************");
        generateDeck();
        
//        System.out.println("****************************");
//        System.out.println("Displaying the deck of cards");
//        System.out.println("****************************");
//        displayDeck();
        
//        System.out.println("***************************");
//        System.out.println("Shuffling the deck of cards");
//        System.out.println("***************************");
        shuffleDeck();
        
//        System.out.println("*************************************");
//        System.out.println("Displaying the shuffled deck of cards");
//        System.out.println("*************************************");
//        displayDeck();
    }
    
    private void generateDeck()
    {
        deck = new HashSet(Constants.NUM_CARDS);
        
        // use an ehanced for loop to loop through the enumerations 
        for(Face face : Face.values())
        {
            for(Suit suit : Suit.values())
            {
                Card card = new Card();
                card.setFace(face);
                card.setSuit(suit);

                if(suit == Suit.DIAMONDS || suit == Suit.HEARTS)
                    card.setColor(Color.RED);
                else
                    card.setColor(Color.BLACK);

                if(!deck.contains(card))
                    deck.add(card);
            }
        }
    }
    
    private void displayDeck()
    {
        System.out.println("Deck size:" + deck.size() + " cards");
        System.out.println("Deck includes:");
        
        for(Card card : deck)
        {           
            System.out.println("Card: " + card.getFace() + " of " + 
                               card.getSuit() + " is color " + card.getColor());
        }
    }
    
    private void shuffleDeck()
    {
        // make an ArrayList out of the HashSet
        List<Card> cardList = new ArrayList(deck);
        // shuffle the deck of cards using the ArrayList
        Collections.shuffle(cardList);
        // convert it back to a HashSet
        deck = new HashSet(cardList);
    }
    
    public Set<Card> getDeck()
    {
        return deck;
    }
}
