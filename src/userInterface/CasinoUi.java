/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import casino.Casino;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Karin Whiting UCF COP 3330
 */
public class CasinoUi implements ActionListener
{
    private JFrame frame;
    private Casino casino;
    private PlayerUi playerUi;
    private BlackJackUi blackJackUi;
    private SlotsUi slotsUi;
    private ScratchOffsUi scratchOffsUi;
    private JPanel casinoPanel;
    private JPanel buttonPanel;
    private JPanel gamePanel;
    private JPanel blackJackPanel;
    private JPanel scratchOffPanel;
    private JPanel slotsPanel;
    private JButton slots;
    private JButton blackJack;
    private JButton scratchers;
    final static String SLOTS = "Slots";
    final static String BLACK_JACK = "Black Jack";
    final static String SCRATCH_OFFS = "Scratch Offs";
    private CardLayout cardLayout;
    
    public CasinoUi(Casino casino)
    {
        this.casino = casino;
        
        initObjects();
        initComponents();
    }
    
    private void initObjects()
    {
        playerUi = new PlayerUi(casino.getPlayer());
        blackJackUi = new BlackJackUi(casino.getPlayer(), this);
        slotsUi = new SlotsUi(casino.getPlayer(), this);
        scratchOffsUi = new ScratchOffsUi(casino.getPlayer(), this);   
        cardLayout = new CardLayout();        
    }
    
    private void initComponents()
    {
        frame = new JFrame("Knights Casino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 500));        

        // set up the button panel
        buttonPanel = new JPanel();
        
        slots = new JButton(SLOTS);
        slots.addActionListener(this);
        
        blackJack = new JButton(BLACK_JACK);
        blackJack.addActionListener(this);
        
        scratchers = new JButton(SCRATCH_OFFS);
        scratchers.addActionListener(this);
        
        buttonPanel.add(blackJack, BLACK_JACK);
        buttonPanel.add(slots, SLOTS);
        buttonPanel.add(scratchers, SCRATCH_OFFS);
    
        // JPanel to hold the cards
        // set the layout manager to CardLayout
        gamePanel = new JPanel();
        gamePanel.setLayout(cardLayout);
        gamePanel.setPreferredSize(new Dimension(300, 300));
        gamePanel.setBorder(BorderFactory.createTitledBorder("Games"));  
        
        // add the cards to the gamePanel
        gamePanel.add(blackJackUi, BLACK_JACK);
        gamePanel.add(slotsUi, SLOTS);
        gamePanel.add(scratchOffsUi, SCRATCH_OFFS);
               
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(playerUi, BorderLayout.WEST);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        cardLayout.show(gamePanel, (String)ae.getActionCommand()); 
        frame.revalidate();
        frame.repaint();
    }
    
    public void updatePlayerUi()
    {
        playerUi.getCashBalance().setText(Integer.toString(playerUi.getPlayer().getCash()));
    }
}
