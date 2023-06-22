import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
* The instruction controller controlls the instruction screen fxml file
* 
* @author Nuha Fahad
* 
*/
public class instructionController {
    @FXML
    Button backButton;
    @FXML
    /**
     * This method switches the screen to the WelcomeScreen.fxml file
     */
    public void backToMain() throws IOException{
        App.setRoot("WelcomeScreen");
    }


    
}
