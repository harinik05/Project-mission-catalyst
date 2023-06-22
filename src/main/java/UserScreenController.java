/**
 * UserScreenController will give the options between two interfaces: a general 
 * user or administration login. 
 * @author: Harini Karthik
*/
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UserScreenController extends Utilities {
    //Define the button variables
    @FXML
    Button userButton;
    @FXML
    Button adminButton;
    @FXML
    Button backButton;
    @FXML
    ToggleButton modeButton;
    @FXML
    Rectangle backgroundRect;

     //Define variables for mode
     protected static Color wRectColor;
     protected static String wButtonTxt;
     /**
     * Initialize method will call the background theme from the search controller and implement 
     * it when loading in the fxml file. 
     */
    @FXML
    public void initialize(){
        ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, WelcomeScreenController.wRectColor, WelcomeScreenController.wButtonTxt);
    }   
    /**
     * The method, changeMode will call ModeActivate to activate the toggle button for 
     * changing between dark and light mode
     */
    public void changeMode(){
        //Color setColor = (Color)WelcomeScreenController.backgroundRect.getFill();
        Utilities.Is_Selected = modeButton.isSelected();
        
        //ModeActivate(backgroundRect.getFill().toString(), modeButton.getText(), backgroundRect, modeButton);
        ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, WelcomeScreenController.wRectColor, WelcomeScreenController.wButtonTxt);
        //ModeActivate(Utilities.backgroundClr, Utilities.subTxt);
        //ModeActivate("#1e90ff", "N");
    }
    /**
     * When the user button is clicked, it will take to user login screen
     */
    @FXML
    private void switchToUserButton() throws IOException {
        App.setRoot("UserLoginScreen");
        UserScreenController.wRectColor = (Color) backgroundRect.getFill();
        UserScreenController.wButtonTxt = modeButton.getText();
    }
    /**
     * When admin button is clicked, it will take to admin login screen
     */
    @FXML
    private void switchToAdminButton() throws IOException {
        App.setRoot("adminLoginScreen");
    }
    /**
     * When the back button is clicked it will take to the welcome screen
     */
    @FXML
    private void switchToBackScreen() throws IOException{
        App.setRoot("WelcomeScreen");
    }
    
}
