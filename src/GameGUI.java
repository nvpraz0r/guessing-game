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
 * -flush out guessButtonClicked
 * 
 * 
 * 
 */


/**
 * This class implements the UI for the game
 */
public class GameGUI extends JFrame{
    
    //private variables
    public int target;
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

        playerGuessField = new JTextField();
        guessesRemainingField = new JTextField();

        guessesRemainingField.setEnabled(false);

        //set text field dimensions
        Dimension dim = new Dimension(150, 20);

        //set preferred sizes
        playerGuessField.setPreferredSize(dim);
        guessesRemainingField.setPreferredSize(dim);

        //set minimum sizes
        playerGuessField.setMinimumSize(dim);
        guessesRemainingField.setMinimumSize(dim);
        
        //declare JPanel
        JPanel panel = new JPanel();

        //set panels
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("Guesses Remaining:"), getConstraints(0, 0));
        panel.add(guessesRemainingField, getConstraints(1, 0));
        panel.add(new JLabel("Guess (1 - 10):"), getConstraints(0, 1));
        panel.add(playerGuessField, getConstraints(1, 1));
        
        //action listeners for buttons
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(e -> {
            guessButtonClicked();
        });

        JButton resetGameVariables = new JButton("Concede");
        resetGameVariables.addActionListener(e -> {
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
        setUpGame();

        setVisible(true);
        setSize(400, 280);
    }

    /**
     * This method validates input
     */
    private void guessButtonClicked(){
        //
        Validators validGuess = new Validators(this);
        int playerGuess = Integer.parseInt(playerGuessField.getText());
        int guessesRemaining = Integer.parseInt(guessesRemainingField.getText());

        if(validGuess.isPresent(playerGuessField, "guess") && validGuess.isValidGuess(playerGuessField, "guess")){
            if(guessesRemaining == 0){
                //exit until better system
                System.exit(0);
            } else {
                if(playerGuess != target){
                    //decrement guesses remaining
                    guessesRemaining--;
                    //parse and set remaining guesses to applicable field
                    guessesRemainingField.setText(Integer.toString(guessesRemaining));
                } else if(playerGuess == target){
                    //win
                    //call setUpGame
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
     * This method resets key variables and text fields
    */
    public void setUpGame(){
        //set random number
        Random rand = new Random();
        target = rand.nextInt(10) + 1;

        guessesRemainingField.setText("5");
        playerGuessField.setText("");
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