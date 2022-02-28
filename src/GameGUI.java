import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * 
 * @author Jon/nvpraz0r
 * @version 0.4
 * 
 * 
 * 
 * TO DO:
 * -create score field
 * -create two score variables
 *      -one for overall
 *      -one for number of guesses
 * -create method to calculate amount of current round score
 *      -send guessesRemaining to calculate score
 * -create "error message" to congratulate player
 */

/**
 * This class implements the UI for the game
 */
public class GameGUI extends JFrame{
    
    //class variables
    public int target;
    private int score;
    private JTextField playerScoreField;
    private JTextField playerGuessField;
    private JTextField guessesRemainingField;
    
    /**
     * This method sets the look and feel
    */
    public GameGUI(){
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }        
        initComponents();
    }
    
    /**
     * This method defines and creates UI elements
     */
    private void initComponents(){
        
        //set title
        setTitle("Guess The Number");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        playerScoreField = new JTextField();
        guessesRemainingField = new JTextField();
        playerGuessField = new JTextField();

        playerScoreField.setEnabled(false);
        guessesRemainingField.setEnabled(false);

        //set text field dimensions
        Dimension dim = new Dimension(150, 20);

        //set preferred sizes
        playerScoreField.setPreferredSize(dim);
        guessesRemainingField.setPreferredSize(dim);
        playerGuessField.setPreferredSize(dim);

        //set minimum sizes
        playerScoreField.setMinimumSize(dim);
        guessesRemainingField.setMinimumSize(dim);
        playerGuessField.setMinimumSize(dim);
        
        //declare JPanel
        JPanel panel = new JPanel();

        //set panels
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("Score:"), getConstraints(0, 0));
        panel.add(playerScoreField, getConstraints(1, 0));
        panel.add(new JLabel("Guesses Remaining:"), getConstraints(0, 1));
        panel.add(guessesRemainingField, getConstraints(1, 1));
        panel.add(new JLabel("Guess (1 - 10):"), getConstraints(0, 2));
        panel.add(playerGuessField, getConstraints(1, 2));
        
        //action listeners for buttons
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(e -> {
            guessButtonClicked();
        });

        JButton resetGameVariables = new JButton("Concede");
        resetGameVariables.addActionListener(e -> {
            score -= 10;
            resetGameVariablesClicked();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            exitButtonClicked();
        });
        
        //add button elements to buttonPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(guessButton);
        buttonPanel.add(resetGameVariables);
        buttonPanel.add(exitButton);

        //add combobox and textfields to panel
        GridBagConstraints c = getConstraints(0,4);
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = 2;
        panel.add(buttonPanel,c);

        //add panel to the window
        add(panel, BorderLayout.CENTER);

        //initial game variable setup
        playerScoreField.setText("0");
        setUpGame();

        setVisible(true);
        setSize(400, 280);
    }

    /**
     * This method validates input and
     * controls primary game functionality
     */
    private void guessButtonClicked(){
        //instantiate validation object
        Validators validGuess = new Validators(this);
        //declare primary game variables
        int guessesRemaining;
        int playerGuess;

        //validate input
        if(validGuess.isPresent(playerGuessField, "guess") && validGuess.isValidGuess(playerGuessField, "guess")){

            //assign primary game variables with new data
            playerGuess = Integer.parseInt(playerGuessField.getText());
            guessesRemaining = Integer.parseInt(guessesRemainingField.getText());

            //check guesses remaining
            if(guessesRemaining == 0){
                //exit for now
                System.exit(0);
            } else {

                //check if input matches target
                if(playerGuess != target){
                    //decrement guesses remaining
                    guessesRemaining--;
                    //parse and set remaining guesses to applicable field
                    guessesRemainingField.setText(Integer.toString(guessesRemaining));
                } else if(playerGuess == target){
                    //win
                    //call getScore
                    getScore(guessesRemaining);
                    //call setUpGame
                    setUpGame();
                } else{
                    //dunno maybe throw an error??
                }
            }
        }
    }
    
    /**
     * This method clears
     * the text fields and ComboBox
     */
    private void resetGameVariablesClicked() {
        setUpGame();
    }
    
    /**
     * This method closes the program
     */
    private void exitButtonClicked() {
        System.exit(0);
    }

    /**
     * This method calculates the amount of points awarded
     * to the player based on the number of guesses remaining
     * in the round via switch case
     * @param guessesRemaining
     * @return none
    */
    private void getScore(int guessesRemaining){
        //switch statement to calculate points based on number of attempts to guess target
        switch(guessesRemaining){
            case 1:
                score += 2;
                break;
            case 2:
                score += 4;
                break;
            case 3:
                score += 6;
                break;
            case 4:
                score += 8;
                break;
            case 5:
                score += 10;
                break;
            default:
                break;
        }
        //update score displayed on UI
        playerScoreField.setText(Integer.toString(score));
    }

    /**
     * This method resets key variables and text fields
    */
    public void setUpGame(){
        //set random number
        Random rand = new Random();
        target = rand.nextInt(10) + 1;

        //set game variables
        playerScoreField.setText(Integer.toString(score));
        guessesRemainingField.setText("5");
        playerGuessField.setText("");

        //REMOVE THIS
        System.out.println(target); //printing target to test scoring
        //REMOVE THIS
    }
    
    // Helper method to return GridBagConstraints objects
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }
    
    /**
     * This method starts the application
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new GameGUI().setVisible(true);            
        });
    }
}