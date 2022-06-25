package userInterface;      //package container.

//imports of various libraries, other files, utilities, etc.
import casino.Player;
import constants.Constants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import blackjack.BlackJack;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

//class definition.
public class BlackJackUi extends JPanel
{
    //member variables.
    private CasinoUi casinoUi;
    private Player player;
    private JLabel data;
    private JLabel bet;
    private JLabel playerUIScore;
    private JLabel dealerUIScore;
    private JPanel headerPanel;
    private JPanel blackJackPanel;
    private JButton hitOrStand;
    private JButton play;
    private ArrayList<ImageIcon> images;    //holds the imageicon cards.
    private ArrayList<ImageIcon> playerHand; //player's cards.
    private ArrayList<ImageIcon> dealerHand; //dealer's cards.
    private ArrayList<ImageIcon> constCards; //holds the faceless cards.
    private Random rand;
    private int playerScore;
    private int dealerScore;

    //constructor: what happens when we want to create a 'BlackJackUi'.
    public BlackJackUi(Player player, CasinoUi casinoUi)
    {
        this.player = player;
        this.casinoUi = casinoUi;

        rand = new Random();
        
        initComponents();   //initialize the UI
        initImages();       //initializes the arraylist of images.
        testImages();
        initHands();        //sets the first 2 cards of player and dealer's hand
        initCardDisplay();
    }
    
    //test function to test the descriptions of createImageIcon():
    private void testImages()
    {
        int counter = 0;
        String tempStr;
        ImageIcon tempImg;
        for(int i = 0; i < images.size(); i++)
        {
            tempImg = images.get(i);
            tempStr = tempImg.getDescription();
            //System.out.println("TempStr: " + tempStr);
            counter++;
        }
        //System.out.printf("Finished counting %d cards.\n", counter);
        if (counter != 52) 
        {
            System.out.println("~~~~ Card deck not at 52 cards ~~~~");
            System.exit(5);
        }
    }
    
    //initializing all the UI components && organizing the layout.
    private void initComponents()
    {
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(3,1));
        headerPanel.setBorder(BorderFactory.createTitledBorder(""));

        data = new JLabel("Welcome to Knights Casino Black Jack");
        bet = new JLabel("The bet is $" + Constants.BET);

        play = new JButton("Play Blackjack");
        play.addActionListener(new BJPlayListener());

        headerPanel.add(data);
        headerPanel.add(bet);
        headerPanel.add(play);

        blackJackPanel = new JPanel();
        blackJackPanel.setLayout(new GridLayout(1,3));
        blackJackPanel.setBorder(BorderFactory.createTitledBorder("Your hand"));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(headerPanel);
        this.add(blackJackPanel);
    }

    //fucntion creates images in the specified size and description.
    private void initImages() 
    {
        images = new ArrayList<ImageIcon>();

        images.add(createImageIcon("../images/blackjack/AceClubs.png", "Ace of Clubs"));
        images.add(createImageIcon("../images/blackjack/AceDiamonds.png", "Ace of Diamonds"));
        images.add(createImageIcon("../images/blackjack/AceHearts.png", "Ace of Hearts"));
        images.add(createImageIcon("../images/blackjack/AceSpades.png", "Ace of Spades"));
        images.add(createImageIcon("../images/blackjack/EightClubs.png", "Eight of Clubs"));
        images.add(createImageIcon("../images/blackjack/EightDiamonds.png", "Eight of Diamonds"));
        images.add(createImageIcon("../images/blackjack/EightHearts.png", "Eight of Hearts"));
        images.add(createImageIcon("../images/blackjack/EightSpades.png", "Eight of Spades"));
        images.add(createImageIcon("../images/blackjack/FiveClubs.png", "Five of Clubs"));
        images.add(createImageIcon("../images/blackjack/FiveDiamonds.png", "Five of Diamonds"));
        images.add(createImageIcon("../images/blackjack/FiveHearts.png", "Five of Hearts"));
        images.add(createImageIcon("../images/blackjack/FiveSpades.png", "Five of Spades"));
        images.add(createImageIcon("../images/blackjack/FourClubs.png", "Four of Clubs"));
        images.add(createImageIcon("../images/blackjack/FourDiamonds.png", "Four of Diamonds"));
        images.add(createImageIcon("../images/blackjack/FourHearts.png", "Four of Hearts"));
        images.add(createImageIcon("../images/blackjack/FourSpades.png", "Four of Spades"));
        images.add(createImageIcon("../images/blackjack/JackClubs.png", "Jack of Clubs"));
        images.add(createImageIcon("../images/blackjack/JackDiamonds.png", "Jack of Diamonds"));
        images.add(createImageIcon("../images/blackjack/JackHearts.png", "Jack of Hearts"));
        images.add(createImageIcon("../images/blackjack/JackSpades.png", "Jack of Spades"));
        images.add(createImageIcon("../images/blackjack/KingClubs.png", "King of Clubs"));
        images.add(createImageIcon("../images/blackjack/KingDiamonds.png", "King of Diamonds"));
        images.add(createImageIcon("../images/blackjack/KingHearts.png", "King of Hearts"));
        images.add(createImageIcon("../images/blackjack/KingSpades.png", "King of Spades"));
        images.add(createImageIcon("../images/blackjack/NineClubs.png", "Nine of Clubs"));
        images.add(createImageIcon("../images/blackjack/NineDiamonds.png", "Nine of Diamonds"));
        images.add(createImageIcon("../images/blackjack/NineHearts.png", "Nine of Hearts"));
        images.add(createImageIcon("../images/blackjack/NineSpades.png", "Nine of Spades"));
        images.add(createImageIcon("../images/blackjack/QueenClubs.png", "Queen of Clubs"));
        images.add(createImageIcon("../images/blackjack/QueenDiamonds.png", "Queen of Diamonds"));
        images.add(createImageIcon("../images/blackjack/QueenHearts.png", "Queen of Hearts"));
        images.add(createImageIcon("../images/blackjack/QueenSpades.png", "Queen of Spades"));
        images.add(createImageIcon("../images/blackjack/SevenClubs.png", "Seven of Clubs"));
        images.add(createImageIcon("../images/blackjack/SevenDiamonds.png", "Seven of Diamonds"));
        images.add(createImageIcon("../images/blackjack/SevenHearts.png", "Seven of Hearts"));
        images.add(createImageIcon("../images/blackjack/SevenSpades.png", "Seven of Spades"));
        images.add(createImageIcon("../images/blackjack/SixClubs.png", "Six of Clubs"));
        images.add(createImageIcon("../images/blackjack/SixDiamonds.png", "Six of Diamonds"));
        images.add(createImageIcon("../images/blackjack/SixHearts.png", "Six of Hearts"));
        images.add(createImageIcon("../images/blackjack/SixSpades.png", "Six of Spades"));
        images.add(createImageIcon("../images/blackjack/TenClubs.png", "Ten of Clubs"));
        images.add(createImageIcon("../images/blackjack/TenDiamonds.png", "Ten of Diamonds"));
        images.add(createImageIcon("../images/blackjack/TenHearts.png", "Ten of Hearts"));
        images.add(createImageIcon("../images/blackjack/TenSpades.png", "Ten of Spades"));
        images.add(createImageIcon("../images/blackjack/ThreeClubs.png", "Three of Clubs"));
        images.add(createImageIcon("../images/blackjack/ThreeDiamonds.png", "Three of Diamonds"));
        images.add(createImageIcon("../images/blackjack/ThreeHearts.png", "Three of Hearts"));
        images.add(createImageIcon("../images/blackjack/ThreeSpades.png", "Three of Spades"));
        images.add(createImageIcon("../images/blackjack/TwoClubs.png", "Two of Clubs"));
        images.add(createImageIcon("../images/blackjack/TwoDiamonds.png", "Two of Diamonds"));
        images.add(createImageIcon("../images/blackjack/TwoHearts.png", "Two of Hearts"));
        images.add(createImageIcon("../images/blackjack/TwoSpades.png", "Two of Spades"));

        //adding the constant arraylists of unrevealed cards.
        constCards = new ArrayList<ImageIcon>();

        constCards.add(createImageIcon("../images/blackjack/backHorizontal.jpg", "back horizontal"));
        constCards.add(createImageIcon("../images/blackjack/backVertical.jpg", "back vertical"));
    }

    private ImageIcon createImageIcon(String path, String description)
    {
        //System.out.println(description);
        java.net.URL imgURL = getClass().getResource(path);

        ImageIcon imageIcon;

        if(imgURL != null) 
        {
            imageIcon = new ImageIcon(imgURL, description);
            imageIcon.setDescription(description);
            //System.out.println("zzzzzzzzzz: " + imageIcon.getDescription());
            imageIcon = imageResize(imageIcon);
            //System.out.println("Returning image with description: " + imageIcon.getDescription());
            return imageIcon;
        }
        else
        {
            System.err.println("Could not find the file." + path);
            return null;
        }
    }

    private ImageIcon imageResize(ImageIcon icon) 
    {
        String tempStr = icon.getDescription();
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(50, 75, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        icon.setDescription(tempStr);
        return icon;
    }

    //dealing the first 2 cards to the player and dealer.
    //Adds them to the Blackjack panel.
    private void initHands()
    {
        int num = 0;
        Collections.shuffle(images);
        playerHand = new ArrayList<ImageIcon>();
        dealerHand = new ArrayList<ImageIcon>();

        for(int i = 0; i < 2; i++) 
        {
            num = rand.nextInt(images.size());
            ImageIcon playerCard1 = images.get(num);
            playerHand.add(playerCard1);
            images.remove(playerCard1);

            num = rand.nextInt(images.size());
            ImageIcon dealerCard1 = images.get(num);
            dealerHand.add(dealerCard1);
            images.remove(dealerCard1);
        }
    }

    //sets the first 2 cards the player sees as unrevealed cards (From constCards arrayList<>()).
    private void initCardDisplay() 
    {
        JLabel one = new JLabel();
        ImageIcon imageOne = constCards.get(0);
        one.setIcon(imageOne);

        JLabel two = new JLabel();
        ImageIcon imageTwo = constCards.get(0);
        two.setIcon(imageTwo);

        blackJackPanel.add(one);
        blackJackPanel.add(two);

        blackJackPanel.revalidate();
        blackJackPanel.repaint();
    }
    
    private int calculatePlayerScore() 
    {
        int tempPlayerScore = 0;
        int val = 0;
        for(int i = 0; i < playerHand.size(); i++)
        {
            ImageIcon temp = playerHand.get(i);
            //System.out.println("temp description: " + temp.getDescription());
            val = processDescriptionAndReturnValue(temp);
            tempPlayerScore += val;
        }
        return tempPlayerScore;
    }
    private int calculateDealerScore()
    {
        int tempDealerScore = 0;
        int val = 0;
        for(int i = 0; i < dealerHand.size(); i++)
        {
            ImageIcon temp = dealerHand.get(i);
            //System.out.println("temp description: " + temp.getDescription());
            val = processDescriptionAndReturnValue(temp);
            tempDealerScore += val;
        }
        if(checkPerfectHand("dealer"))
        {
            tempDealerScore = 21;
        }
        return tempDealerScore;
    }

    //Fixes bug where 2 aces makes a score of 21.
    // checks either the player or dealer hand to see if 2 aces are present.
    private boolean checkPerfectHand(String testHand)
    {
        ArrayList<String> temp = new ArrayList<String>();
        if(testHand == "player") 
        {
            for(int i = 0; i < playerHand.size(); i++)
            {
                String tempDescp = playerHand.get(i).getDescription();
                String[] tempStr = tempDescp.split(" ");
                tempDescp = tempStr[0];
                temp.add(tempDescp);
            }
        }
        else if(testHand == "dealer")
        {
            for(int i = 0; i < dealerHand.size(); i++)
            {
                String tempDescp = dealerHand.get(i).getDescription();
                String[] tempStr = tempDescp.split(" ");
                tempDescp = tempStr[0];
                temp.add(tempDescp);
            }  
        }

        //if both cards in the hand are aces, return true.
        if((temp.get(0) == "Ace") && (temp.get(1) == "Ace"))
        {
            if(testHand == "player")
            {
                playerScore = 21;
                playerUIScore.setText("Player's Score: " + playerScore);
            }
            else if(testHand == "dealer")
            {
                dealerScore = 21;
                dealerUIScore.setText("Dealer's SCore: " + dealerScore);
            }
            return true;
        }
        return false;
    }
    private int processDescriptionAndReturnValue(ImageIcon img) 
    {
        int val = 0;
        String tempDescp = img.getDescription();
        String[] tempStr = tempDescp.split(" ");
        tempDescp = tempStr[0];
        
        switch(tempDescp) {
            case "Ace":
            val = 11;
            break;
            case "Jack":
            val = 10;
            break;
            case "King":
            val = 10;
            break;
            case "Queen":
            val = 10;
            break;
            case "Two":
            val = 2;
            break;
            case "Three":
            val = 3;
            break;
            case "Four":
            val = 4;
            break;
            case "Five":
            val = 5;
            break;
            case "Six":
            val = 6;
            break;
            case "Seven":
            val = 7;
            break;
            case "Eight":
            val = 8;
            break;
            case "Nine":
            val = 9;
            break;
            case "Ten":
            val = 10;
            break;
            default:
            System.out.println("Error occurred.");
            val = 690000;
            break;
        }
        return val;
    }

    //when the play button is pressed from the blackjack panel, this happens:
    //the player's cash is adjusted.
    //the unrevealed cards are removed, then the player's cards 
    //changes the 'Play' button to a 'hitOrStand' button, then updates the UI with the changes.
    private class BJPlayListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            revealCards();
            revealScoreboard();
        }

        private void revealCards() 
        {
            //remove 'unrevealed' cards.
            blackJackPanel.removeAll();
            //player entered blackjack session, remove bet & update UI.
            player.setCash(player.getCash() - Constants.BET);
            casinoUi.updatePlayerUi();

            //adding the revealed cards to the BJ panel.
            JLabel one = new JLabel();
            ImageIcon imageOne = playerHand.get(0);
            one.setIcon(imageOne);
    
            JLabel two = new JLabel();
            ImageIcon imageTwo = playerHand.get(1);
            two.setIcon(imageTwo);

            //updating the 'play' button to say 'hitorstand' and have new functionality...
            hitOrStand = new JButton("Hit or Stand?");
            hitOrStand.addActionListener(new HitOrStandListener());
    
            blackJackPanel.add(one);
            blackJackPanel.add(two);

            headerPanel.remove(play);
            headerPanel.add(hitOrStand);
    
            blackJackPanel.revalidate();
            blackJackPanel.repaint();
        }

        //transfigures the top board to have a scoreboard.
        private void revealScoreboard()
        {
            playerScore = calculatePlayerScore();
            dealerScore = calculateDealerScore();
            playerUIScore = new JLabel("Player's Score: " + playerScore);
            dealerUIScore = new JLabel("Dealer's Score: " + dealerScore);

            //check to fix the 2 'perfect hand' bug:
            if(checkPerfectHand("dealer"))
            {
                dealerScore = 21;
                dealerUIScore.setText("Dealer's Score: " + dealerScore);
            }
            if(checkPerfectHand("player"))
            {
                playerScore = 21;
                playerUIScore.setText("Player's Score: " + playerScore);
            }

            headerPanel.remove(data);
            headerPanel.remove(bet);
            headerPanel.add(playerUIScore);
            headerPanel.add(dealerUIScore);

            headerPanel.revalidate();
            headerPanel.repaint();
        }
    }

    //private class for hitorstand button. What happens when you click the hitOrStand button?
    //prompt the user if they want to hit or stand; 'yes' == hit, deal another card. 'no' == stand, wait for dealer response.
    //handle the results of the game.
    public class HitOrStandListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            hitOrStand();
        }

        private void hitOrStand()
        {
            hitOrStand.addActionListener(new ActionListener() {

                //not sure if the yes/no dialog box is the one i want. kinda would prefer custom buttons, not sure how to make that happen.
                public void actionPerformed(ActionEvent e) {

                    String[] options = {"Hit", "Stand"};
                    //captures the user's entry.
                    //int output = JOptionPane.showConfirmDialog(null, "Hit", "Stand", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    int output = JOptionPane.showOptionDialog(null, "Hit or Stand?", "Hit or Stand",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                    //hit: player gets another card.
                    if(output == JOptionPane.YES_OPTION) {
                        System.out.println("Yes Selected");
                        int num;
                        //getting the 'next' card for the player & adds to their hand.
                        //images is the original arraylist with all the cards.
                        num = rand.nextInt(images.size());
                        ImageIcon temp = images.get(num);
                        playerHand.add(temp);
                        images.remove(temp);
                        playerScore = calculatePlayerScore();
                        playerUIScore.setText("Player's score: " + playerScore);
            
                        //setting the new card to players hand.
                        JLabel newCard = new JLabel();
                        int phSize = playerHand.size();
                        ImageIcon newPlayerCard = playerHand.get(phSize-1);
                        newCard.setIcon(newPlayerCard);
                        blackJackPanel.add(newCard);
            
                        blackJackPanel.revalidate();
                        blackJackPanel.repaint();
                        if(playerScore > 21)
                        {
                            JOptionPane.showMessageDialog(null, "Over 21 - Bust!");

                            blackJackPanel.removeAll();
                            results();
                        }
                    }
                    //player wants to stand.
                    else if(output == JOptionPane.NO_OPTION) {
                        results();
                        System.out.println("No Option");
                    }
                    //error.
                    else {
                        results();
                        System.out.println("Cancel option");
                    }
                }
            });
        }
    }
    private void dealerDecisions() 
        {
            int num;
            //dealer will hit if less than or equal to 16.
            while(dealerScore <= 16)
            {
                num = rand.nextInt(images.size());
                ImageIcon temp = images.get(num);
                dealerHand.add(temp);
                images.remove(temp);
                dealerScore = calculateDealerScore();
                dealerUIScore.setText("Dealer's score: " + dealerScore);

                if(dealerScore > 16)
                {
                    break;
                }
            }
        }
        private void asyncDealer() 
        {
            try {
                JOptionPane.showMessageDialog(null, "The Dealer is calculating... (Please wait 3 seconds)");
                //pauses program for 3 seconds while dealer is 'strategizing'.
                Thread.sleep(3000);
                dealerDecisions();
            } catch (InterruptedException e) {
                //don't think it will get here.
                System.out.println("Interrupted.");
            }
        }

        private void results() 
        {
            asyncDealer();
            boolean gameOver = false;
            while(gameOver != true) 
            {

                if((playerScore > dealerScore) && (playerScore <= 21)) 
                {
                    JOptionPane.showMessageDialog(null, "You win $10!");
                    player.setCash(player.getCash() + 10);
                    gameOver = true;
                    break;
                }

                if(playerScore > 21) 
                {
                    JOptionPane.showMessageDialog(null, "Bust! Over 21.");
                    gameOver=true;
                    break;
                }

                else if(dealerScore > 21)
                {
                    JOptionPane.showMessageDialog(null, "Dealer bust! Player wins $5!");
                    player.setCash(player.getCash() + 5);
                    gameOver=true;
                    break;
                }
            
                //dealer has to draw another card if player has 'stand' OR evaluate who is the winner...?
                else if((dealerScore <= 21) && (playerScore <= 21) && (dealerScore >= 16) && !gameOver)
                {
                    //get dealer another card in their hand.
                    int num;
                    num = rand.nextInt(images.size());
                    ImageIcon temp = images.get(num);
                    dealerHand.add(temp);
                    images.remove(temp);

                    dealerScore = calculateDealerScore();
                    dealerUIScore.setText("Dealer's Score: " + dealerScore);

                    //if checkBounds returns true, player wins.
                    if(checkBounds() == true) 
                    {
                        JOptionPane.showMessageDialog(null, "Player wins $5!");
                        player.setCash(player.getCash() + 5);
                        gameOver = true;
                        break;
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Dealer Wins. Player Forfeits bet.");
                        gameOver = true;
                        break;
                    }
                }
            }

            //Ask user if they want to play again:
            String[] options = { "Play Again", "Exit"};
            int output = JOptionPane.showOptionDialog(null, "Play Again?", "Blackjack Table",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if(output == JOptionPane.YES_OPTION)
            {
                //user wants to play again. call gameReset();
                gameReset();
            }
            else 
            {
                System.exit(69420);
            }
            //finally, update the UI/player's cash balance.
            casinoUi.updatePlayerUi();
        }

        //returns True if the player wins.
        private boolean checkBounds() 
        {
            boolean returnVal = false;
            if(dealerScore > 21) 
            {   
                //dealer busted, return false (out of bounds.)
                returnVal = true;
            }
            if((dealerScore < playerScore) && (dealerScore <= 21))
            {
                //player wins
                returnVal = false;
            }
            return returnVal;
        }

        // Once the game has been adjudicated, need to complete some cleanup actions...
        /*
         * 1) Need to reset the windows, cards, score.
         * 2) Redisplay the headerpanel.
         */
        private void gameReset()
        {
            //reset the images, playerhand, dealerhand.
            images.clear();
            playerHand.clear();
            dealerHand.clear();
            this.blackJackPanel.removeAll();
            this.headerPanel.removeAll();
            //call the initialize functions again.
            gameResetHelper();
            initImages();      
            initHands();        
            initCardDisplay();
        }

        private void gameResetHelper()
        {
            data = new JLabel("Welcome to Knights Casino Black Jack");
            bet = new JLabel("The bet is $" + Constants.BET);
    
            play = new JButton("Play Blackjack");
            play.addActionListener(new BJPlayListener());
    
            headerPanel.add(data);
            headerPanel.add(bet);
            headerPanel.add(play);
        }


        /* KNOW BUGS 
         * 1. Winning logic is fucked up. Need to fix.
        */
}
