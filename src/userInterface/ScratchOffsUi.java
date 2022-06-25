package userInterface;

import casino.Player;
import constants.Constants;
import java.util.Random;
import java.util.ArrayList;
import java.util.EventListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;

//Beginning of Class def.
public class ScratchOffsUi extends JPanel
{
    private scratcherTile[][] rows;
    private CasinoUi casinoUi;
    private Player player;
    private JPanel headerPanel, gamePanel;
    private JLabel data, bet, luckyNumber, doubleJeopardy;
    private JButton generateCard;
    private Random rand;
    private ArrayList<ImageIcon> constSquares, numberedSquares;
    private ArrayList<Integer> luckyNumbers;
    
    public ScratchOffsUi(Player player, CasinoUi casinoUi)
    {
        this.player = player;
        this.casinoUi = casinoUi;
        rand = new Random();

        initComponents();
        initImages();
    }
    
    private void initComponents()
    {
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3, 1));
        headerPanel.setBorder(BorderFactory.createTitledBorder(""));
        
        data = new JLabel("Welcome to Knights Casino Scratch Offs");
        bet = new JLabel("The bet is $" + Constants.BET);

        generateCard = new JButton("Generate Card ($5 bet)");
        generateCard.addActionListener(new CardListener());

        headerPanel.add(data);
        headerPanel.add(bet);
        headerPanel.add(generateCard);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 3));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(headerPanel);    
        this.add(gamePanel);
    }
    
    private void initImages()
    {
        constSquares = new ArrayList<>();
        numberedSquares = new ArrayList<>();

        constSquares.add(createImageIcon("../images/scratchers/blackSquare.jpg", "black square"));
        constSquares.add(createImageIcon("../images/scratchers/goldSquare.jpg", "gold square")); 
    }

    private ImageIcon createImageIcon(String path, String description) 
    {
        java.net.URL imgURL = getClass().getResource(path);
        
        ImageIcon imageIcon;
        
        if (imgURL != null) 
        {
            imageIcon = new ImageIcon(imgURL, description); 
            imageIcon.setDescription(description);
            imageIcon = imageResize(imageIcon);
            return imageIcon;
        } 
        else 
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(75, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    /* Private Class to listen to the 'generate' button
     * && Responds to the click
     */
    private class CardListener implements ActionListener
    {
        private int counter = 0;
        private boolean clicked = false;
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //filler text.
            counter++;
            //String msg = "You have pressed the button " + counter + " times.";
            String clickedMsg = "You already have a scratch card. Finish your current card.";
            if(clicked) 
            {
                JOptionPane.showMessageDialog(null, clickedMsg);
            }
            else
            {
                System.out.println("Clicked is " + clicked + " so we make the card.");
                makeScratchBet();
                //makeCardDisplay();
                testMakeBoard();
                clicked = true;
            }
        }
    }

    //Class for the tiles of the gamePanel.
    private class scratcherTile
    {
        /* Private Member Variables. */
        private int value;
        private JLabel label;
        
        //constructor for the object.
        public scratcherTile(int value, JLabel label)
        {
            this.value = value;
            this.label = label;
        }

        /* Getters & Setters for the Member Variables. */
        public JLabel getJlabel()
        {
            return this.label;
        }
        
        public void setJLabel(JLabel jl)
        {
            this.label = jl;
        }

        public int getValue()
        {
            return this.value;
        }
        
        public void setValue(int value)
        {
            this.value = value;
        }

        //Reveals the tile's value.
        //TODO: Make it reveal a nicer img layout for the value.
        public void revealTile()
        {
            System.out.println("Value: " + this.getValue());
        }
    }

    public void functionTest()
    {
        doubleJeopardy = new JLabel("why isn't this working?");
        gamePanel.add(doubleJeopardy);
        gamePanel.repaint();
        gamePanel.revalidate();
    }

    //need to update the playerCash when they press the generate button.
    private void makeScratchBet()
    {
        player.setCash(player.getCash() - 5);
        casinoUi.updatePlayerUi();
    }

    //helper function to get luckyNumber && doubleJeopardy.
    private int getLuckyNumbers(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //returns either 1 or 0 to decide if the tile will be black or gold to start.
    private int coinFlip()
    {
        int r = rand.nextInt(2);
        return r;
    }

    //tests the coinflip function.
    private void coinFlipTest()
    {
        int max = 5;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        while(max > 0) {
            temp.add(coinFlip());
            max--;
        }

        for(int i = 0; i < temp.size(); i++)
        {
            //if a value from coinflip is not 1 or 0, then there's an error.
            if((temp.get(i) != 1) || (temp.get(i) != 0))
            {
                System.exit(5);
                throw new IllegalArgumentException("The system has encountered a problem.");
            }
        }
    }

    //prints the lucky numbers.
    private void testLuckyNumGenerator(ArrayList<Integer> luckyNumbers)
    {
        //printing the lucky numbers.
        for(int i = 0; i < luckyNumbers.size(); i++)
        {
            System.out.print(luckyNumbers.get(i) + " ");
        }
        System.out.println();
    }

    //Unit test to make the board successfully.
    private void testMakeBoard()
    {
        int counter = 0;
        //getting a blank black square.
        ImageIcon blackTile = constSquares.get(0);

        //creating a testBoard of 4x3.
        scratcherTile[][] testBoard = new scratcherTile[4][3];

        //init and set each jlabel to the black square.
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                // int temp = i;
                // int temp2 = j;
                testBoard[i][j] = new scratcherTile(5+i+j, null);
                testBoard[i][j].setJLabel(new JLabel());
                testBoard[i][j].getJlabel().setIcon(blackTile);
                gamePanel.add(testBoard[i][j].getJlabel());
                testBoard[i][j].getJlabel().addMouseListener(new CustomMouseListener());
                counter++;
            }
        }
        gamePanel.repaint();
        gamePanel.revalidate();
        System.out.println("Counter: " + counter);
    }

    /* Private Class for the Click Actions on the Tile. */
    private class CustomMouseListener implements MouseListener
    {
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Mouse Clicked!: (" + e.getX()+", "+e.getY() +")");
            System.out.println(e.getSource());
            scratcherTile clicked = (scratcherTile) e.getSource();
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(clicked.getJlabel() == rows[i][j].getJlabel())
                    {
                        rows[i][j].revealTile();
                    }
                    else
                    {
                        System.out.println("Wasn't the correct tile.");
                    }
                }
            }
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }

    // private void boardRevealClick(int r, int c, scratcherTile[][] rows)
    // {
    //     rows[r][c].revealTile();
    // }

    //Makes the gameboard
    private void makeCardDisplay() 
    {
        //luckyNum is the 'winning' number. It's in a range from 1, 50 (inclusive).
        int luckyNum = getLuckyNumbers(1, 50);

        //generating the scratch numbers; this is our list of values for the scratchoff.
        luckyNumbers = new ArrayList<>();
        for(int i = 0; i < 9; i++)
        {
            luckyNumbers.add(getLuckyNumbers(1, 50));
        }
        
        testLuckyNumGenerator(luckyNumbers);

        int counter = 0; //tracks how many tiles successfully created. 

        //Creating a 2D-array of scratcher tiles 4 rows x 3 columns.
        rows = new scratcherTile[4][3];
        rows[0][0] = new scratcherTile(-5, new JLabel("Lucky Number: "));
        rows[0][1] = new scratcherTile(-5, new JLabel("" + luckyNum));
        rows[0][2] = new scratcherTile(-5, new JLabel(""));
        for(int i = 0; i < 3; i++)
        {
            gamePanel.add(rows[0][i].getJlabel());
        }

        //set the 3x3 matrix of images to initially the black/gold boxes.
        ImageIcon blackTile = constSquares.get(0);
        ImageIcon goldTile = constSquares.get(1);
        int coinFlip;
        int luckyNumbersIndex = 0;
        JLabel temp = new JLabel();

        for(int i = 1; i < 4; i++) 
        {
            for(int j = 0; j < 3; j++)
            {
                //coin flips decides if black or gold tile.
                coinFlip = coinFlip();

                //if heads, let's make the tile a golden tile.
                if(coinFlip == 0)
                {
                    //rows[i][j] = new JLabel();
                    rows[i][j] = new scratcherTile(luckyNumbers.get(luckyNumbersIndex), null);
                    rows[i][j].setJLabel(new JLabel());
                    rows[i][j].getJlabel().setIcon(goldTile);
                    gamePanel.add(rows[i][j].getJlabel());
                    luckyNumbersIndex++;
                    counter++;
                }
                //otherwise, make it a black tile.
                else
                {
                    rows[i][j] = new scratcherTile(luckyNumbers.get(luckyNumbersIndex), null);
                    rows[i][j].setJLabel(new JLabel());
                    rows[i][j].getJlabel().setIcon(blackTile);
                    gamePanel.add(rows[i][j].getJlabel());
                    luckyNumbersIndex++;
                    counter++;
                }
            }
        }  
        gamePanel.repaint();
        gamePanel.revalidate();
        System.out.println("Function created " + counter + " tiles.");
    }
}
