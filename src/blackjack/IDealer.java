package blackjack;

import java.util.HashSet;

public interface IDealer
{
    public Card deal(Deck deck);
    public int hitOrStand();
}
