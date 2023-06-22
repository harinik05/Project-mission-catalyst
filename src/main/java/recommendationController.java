/**
 * The recommendationController will take in the input product name and generate some recommendations
 * if there are available. These can be other products, PPEs, or safety tools to carry 
 * along when using the product in the experiment. Some of this data is referred from WHIMIS website 
 * to comply with the rules of chemical handling. 
 * @author: Harini Karthik 
*/
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class recommendationController extends Utilities {
    @FXML
    Label recommendLabel;
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
     * it when loading in the fxml file. It will also display the product reccommended items list 
     */
     @FXML
    public void initialize(){
        displayRecommendedProduct();
        ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, searchController.wRectColor, searchController.wButtonTxt);
    }   
     /**
     * The method, changeMode will call ModeActivate to activate the toggle button for 
     * changing between dark and light mode
     */
    public void changeMode(){
        //Color setColor = (Color)WelcomeScreenController.backgroundRect.getFill();
        Utilities.Is_Selected = modeButton.isSelected();
        
        //ModeActivate(backgroundRect.getFill().toString(), modeButton.getText(), backgroundRect, modeButton);
        ModeActivate("#1e90ff", "🌙",backgroundRect, modeButton, searchController.wRectColor, searchController.wButtonTxt);
        //ModeActivate(Utilities.backgroundClr, Utilities.subTxt);
        //ModeActivate("#1e90ff", "N");
    }
    
    /**
     * The method, displayRecommended will find the product in the CSV files and put
     * its recommendations into an aray - arrRec. When its size is empty, then there are
     * no recommendations. If the size is greater than 0, then it will display all of the array
     * items in the label
     */
    public void displayRecommendedProduct(){
        ArrayList<String> arrRec = new ArrayList<String>();
        arrRec = ReadRecommendation(Utilities.sEnteredProduct);
        if (arrRec.size() == 0){
            recommendLabel.setText("No recommendations available for this now. Sorry :)");
        }
        else{
            String sArrRect = sortArrayListToString(arrRec);
            //recommendLabel.setText(properDisplayLabel(sArrRect));
            recommendLabel.setText(sArrRect);
        }
    }
     /**
     * sortArrayListToString will take in the values from the array and convert 
     * it to a well organized string to display in the label in the method above
     * displayRecommendedProduct
     * @param arr - an array which needs to be converted - list of recommended products 
     * for entered product. 
     * @return sTemp- temporary string converted from array
     */
    public String sortArrayListToString(ArrayList<String> arr){
        String sTemp ="";
        for(int i=0; i<arr.size(); i++){
            sTemp=sTemp+Integer.toString(i+1)+". "+arr.get(i)+"\n";
        }
        return sTemp;
    }
     /**
     * When the back button is clicked, it will point to the UserScreen
     */
    @FXML
    private void switchToBackScreen() throws IOException{
        App.setRoot("UserScreen");
    }

}
