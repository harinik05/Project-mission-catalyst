/**
 * The WelcomeScreen will give a button to start the program. There are also instructions 
 * on the front screen developed by Mofe if anyone needs to refer to that. 
 * @author: Harini Karthik
*/
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;



public class WelcomeScreenController extends Utilities {
//Define the variables
    @FXML
    Text titleText;
    @FXML
    Button startButton;
    @FXML
    Button instructButton;
    @FXML
    protected ToggleButton modeButton;
    @FXML
    protected Rectangle backgroundRect;
    @FXML
    AnchorPane welcomePane;

    //Define variables for mode
    protected static Color wRectColor;
    protected static String wButtonTxt;
    

/**
     * The method, changeMode will call ModeActivate to activate the toggle button for 
     * changing between dark and light mode
     */
  public void changeMode(){
      Utilities.Is_Selected = modeButton.isSelected();
      System.out.println(Utilities.Is_Selected);
      ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, Color.web("#30d5c8"), "🌞" );
      //ModeActivate("#1e90ff", "N");
  }

    /**
     * When the start button is clicked, then it will go to UserScreen. Also, it will store the default values
     * of current screen
     */
      @FXML
    private void switchToUserMode() throws IOException {
        App.setRoot("UserScreen");
        WelcomeScreenController.wRectColor = (Color) backgroundRect.getFill();
        WelcomeScreenController.wButtonTxt = modeButton.getText();
        
       
     
    }

    /**
     * When the instructions button is clicked, the instructions screen will be showed
     */
    @FXML
    private void switchToInstructionScreen() throws IOException {
        App.setRoot("InstructionScreen");
    }
    
}
    
