import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 * @author Jon/nvpraz0r
 * @version 0.4
 * --------------------
 */


/**
 *  This class validates text field input
 */
public class Validators {

    private final Component parentComponent;

    
    /**
     * This is a constructor method
     * @param parent
    */
    public Validators(Component parent) {
        this.parentComponent = parent;
    }
    
    /**
     * This method creates an error window
     * based on the message passed to the method
     * @param message text for the tool tip
    */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(parentComponent, message, 
                "Invalid Entry", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method accepts text field contents and specifies where
     * the error occurs
     * @param c text field contents
     * @param fieldName location where the user went wrong
     * @return boolean
    */
    public boolean isPresent(JTextComponent c, String fieldName) {
        if (c.getText().isEmpty()) {
            showErrorDialog("Don't forget to " + fieldName);
            c.requestFocusInWindow();
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method accepts text field contents and specifies where
     * the error occurs
     * @param c text field contents
     * @param fieldName location where the user went wrong
     * @return boolean
    */
    public boolean isValidGuess(JTextComponent c, String fieldName){
        try {
            int validGuess = Integer.parseInt(c.getText());
            if(validGuess >= 1 && validGuess <= 10){
                return true;
            } else {
                showErrorDialog("Your " + fieldName + " must be between 1 & 10");
                c.requestFocusInWindow();
                return false;
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Your " + fieldName + " must be between 1 & 10");
            c.requestFocusInWindow();
            return false;
        }
    }
}