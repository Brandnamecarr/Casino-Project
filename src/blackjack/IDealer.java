/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.HashSet;

/**
 *
 * @author Karin Whiting UCF COP 3330
 */
public interface IDealer
{
    public Card deal(Deck deck);
    public int hitOrStand();
}
