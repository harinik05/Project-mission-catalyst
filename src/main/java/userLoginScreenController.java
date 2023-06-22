/**
 * The userLoginScreenController will check the authenticity of barcodeID 
 * of the users
 * @author: Harini Karthik
*/
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class userLoginScreenController extends Utilities{
    //Define the variables for all of the FXML components
    @FXML
    TextField inputUserID;
    @FXML
    Button enterUserButton;
    @FXML
    Label authenticationLabel;
    @FXML
    Button backButton;
    @FXML
    Rectangle backgroundRect;
    @FXML
    ToggleButton modeButton;

    //Define variables for mode
    protected static Color wRectColor;
    protected static String wButtonTxt;
    

    //protected  String sUID = inputUserID.getText();
    protected static String sUserID;
     /**
     * Initialize method will call the background theme from the search controller and implement 
     * it when loading in the fxml file. 
     */
    @FXML
    public void initialize(){
        ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, UserScreenController.wRectColor, UserScreenController.wButtonTxt);
    }   
     /**
     * The method, changeMode will call ModeActivate to activate the toggle button for 
     * changing between dark and light mode
     */
    public void changeMode(){
        //Color setColor = (Color)WelcomeScreenController.backgroundRect.getFill();
        Utilities.Is_Selected = modeButton.isSelected();
        
        //ModeActivate(backgroundRect.getFill().toString(), modeButton.getText(), backgroundRect, modeButton);
        ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, UserScreenController.wRectColor, UserScreenController.wButtonTxt);
        //ModeActivate(Utilities.backgroundClr, Utilities.subTxt);
        //ModeActivate("#1e90ff", "N");
    }
    
   
     /**
     * checkForAuthentication method will take in the userID input and check if matches
     * with the hashmap key values of number_username. If it doesn't then it will 
     * print out in the error label.If it matches, then it will go to the userInput screen
     * and set default color themes
     */
    @FXML
    private void checkForAuthentication() throws IOException{
        //App.setRoot("userInput");
        userLoginScreenController.sUserID = inputUserID.getText();
        int iInputUserID  = Integer.parseInt(inputUserID.getText());
        System.out.println(Utilities.number_username);
        try{
        for (int i = 1; i<5; i++){
            //String actualString = Utilities.number_username.get(i);
            String sI = Integer.toString(i);
            if (iInputUserID == Utilities.number_username.get(sI)){
                App.setRoot("userInput");
                userLoginScreenController.wRectColor = (Color) backgroundRect.getFill();
                userLoginScreenController.wButtonTxt = modeButton.getText();
            }else{
                authenticationLabel.setText("INVALID LOGIN. TRY AGAIN");
            }
        }
    } catch(Exception e){
        System.out.println("ERROR "+ e.getMessage());


    }
    }
     /**
     * When the backbutton is clicked, then it will go to the userScreen
     */
    @FXML
    private void switchToBackScreen() throws IOException{
        App.setRoot("UserScreen");

    }

}
