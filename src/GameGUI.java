/**
 * @author Jon
 * 12/11/2021
 * Application 2: create an application that 
 * populates the combo box in the GUI display
 * from the player database
 */
import java.util.Random;
import javax.swing.*;
import java.awt.*;

/**
 * This class implements the UI for the game
 */
public class GameGUI extends JFrame{
    
    //private variables
    private JComboBox playersComboBox;
    private JTextField wagerAmount;
    private JTextField playerGuess;
    
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
        setTitle("Application 2");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        playersComboBox = new JComboBox();
        wagerAmount = new JTextField();
        playerGuess = new JTextField();

        //set text field dimensions
        Dimension dim = new Dimension(150, 20);
        //set preferred sizes
        wagerAmount.setPreferredSize(dim);
        playerGuess.setPreferredSize(dim);
        playersComboBox.setPreferredSize(dim);
        //set minimum sizes
        wagerAmount.setMinimumSize(dim);
        playerGuess.setMinimumSize(dim);
        playersComboBox.setMinimumSize(dim);
        
        //declare JPanel
        JPanel panel = new JPanel();
        //set panels
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("Players:"), getConstraints(0, 0));
        panel.add(playersComboBox, getConstraints(1, 0));
        panel.add(new JLabel("Wager Amount (0 - 100):"), getConstraints(0, 1));
        panel.add(wagerAmount, getConstraints(1, 1));
        panel.add(new JLabel("Guess a number between 0 and 3:"), getConstraints(0, 2));
        panel.add(playerGuess, getConstraints(1, 2));
        
        //action listeners for buttons
        JButton spinTheWheelButton = new JButton("Spin The Wheel");
        spinTheWheelButton.addActionListener(e -> {
            spinTheWheelClicked();
        });

        JButton clearFieldsButton = new JButton("Clear Fields");
        clearFieldsButton.addActionListener(e -> {
            clearFieldsButtonClicked();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            exitButtonClicked();
        });
        
        //add button elements to buttonPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(spinTheWheelButton);
        buttonPanel.add(clearFieldsButton);
        buttonPanel.add(exitButton);

        //add combobox and textfields to panel
        GridBagConstraints c = getConstraints(0,4);
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = 2;
        panel.add(buttonPanel,c);

        //add panel to the window
        add(panel, BorderLayout.CENTER);

        setVisible(true);
        setSize(400, 280);
    }
    
    /**
     * This method validates input
     */
    private void spinTheWheelClicked(){
        Validators v = new Validators(this);
        if(v.isValidWager(wagerAmount, "Wager") &&
            v.isValidGuess(playerGuess, "Invalid Guess")){
                Random r = new Random();
                int randomInt = 0;
                randomInt = r.nextInt(3);
                String convertPlayerGuess = playerGuess.getText();
                int parsePlayerGuess = Integer.parseInt(convertPlayerGuess);
                if (parsePlayerGuess == randomInt){
                    //add amount gambled
                } else {
                    //lose amount gambled
                }
            }
    }
    
    /**
     * This method clears
     * the text fields and ComboBox
     */
    private void clearFieldsButtonClicked() {
        wagerAmount.setText("");
        playerGuess.setText("");
    }
    
    /**
     * This method closes the program
     */
    private void exitButtonClicked() {
        System.exit(0);
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