package constants;


public class Constants
{
    public static final int BLACK_JACK = 1;
    public static final int SCRATCH = 2;
    public static final int SLOTS = 3;
    
    public static final int BET = 5;
    
    // constants for the Slots game
    public static final int PAIR_PAYOUT = 5;
    public static final int TRIPLE_PAYOUT = 50;
    
    // constants for the Black Jack game
    public static final int NUM_CARDS = 52;
    public static final int BJ_BET = 10;
    public static final int BJ_WIN = 20;
    public static final int HIT = 1;
    public static final int STAND = 0;
    public static final int DEALER_STAND = 17;
    public static final int DEALER_HIT = 16;    
    public static final int ZERO = 0;
    public static final int BUST = 21;
    
    // constants for Scratch Offs
    public static final int ONE_DOL = 1;
    public static final int FIVE_DOL = 5;
    public static final int TEN_DOL = 10;

    
    

    public enum Color
    {
        RED,
        BLACK
    }
    
    public enum Suit 
    {
        CLUBS, 
        DIAMONDS,
        HEARTS, 
        SPADES 
    }
    
    public enum Face
    {
       TWO,
       THREE,
       FOUR,
       FIVE,
       SIX,
       SEVEN,
       EIGHT,
       NINE,
       TEN,
       JACK,
       QUEEN,
       KING,
       ACE
    }
}
