/**
 * The locationController will use the hashmap ItemLocation to capture the location number and convert 
 * it to a shelved location. Then, for the particular typed data, it will find its location and highlight 
 * it in a visual manner of organization system. 
 * @author: Harini Karthik 
*/
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 

public class locationController extends Utilities {
    //Define variables
    public String sLocationID;
    @FXML
    AnchorPane cabinetPane;
    @FXML 
    Label itemName;
    @FXML
    Label itemQuantity;
    @FXML
    Button backButton;
    @FXML
    Rectangle backgroundRect;
    @FXML
    ToggleButton modeButton;

    //Define variables for mode
    protected static Color wRectColor;
    protected static String wButtonTxt;
    /**
     * Initialize method will call the background theme from the search controller and implement 
     * it when loading in the fxml file. 
     */
    @FXML
    public void initialize(){
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
     * When the backbutton is clicked, it will switch to the back screen. 
     */
    @FXML
    private void switchToBackScreen() throws IOException{
        App.setRoot("userInput");

    }
    /**
     * The method, showLoc, will call the getItemLocation of entered product, and take its ID to 
     * highlight the location within the rectangles in cabinetPane. 
     * Then, it will set the labels with the name of the entered product and available product 
     * grabbed from the hashmap directly
     */
    @FXML
    protected void showLoc(){
        System.out.println("showLoc called ");
        this.sLocationID = getItemLocation(Utilities.sEnteredProduct);
        System.out.println("Location ID "+this.sLocationID);
        highlightLocation(cabinetPane);
        setLabelText(itemName,Utilities.sEnteredProduct);
        setLabelText(itemQuantity, (Utilities.Item_Quantity.get(Utilities.sEnteredProduct)).toString());
    }
    
    /**
    * setLabelText will display the label with some text
    * @param l1 any label (there are two of them in this case one for item name and other for qty)
    * @param sTxtDisplay - specific text to display for these labels
    */
    protected void setLabelText(Label l1, String sTxtDisplay){
        System.out.println("Label set to value "+ sTxtDisplay);
        //l1.setTextFill(Color.ORANGE);
        l1.setText(sTxtDisplay);

        }
    /**
     * highlightLocation will get children of the anchor pane and obtain its ID. Then it will take 
     * it as a rectangle to fill it with green color for the typed product name in userInput Screen. 
     * @param a anchor pane which contains all of the rectangles
     */
    private void highlightLocation(AnchorPane a){
        System.out.println("Calling highlightLocation");
        for (Node n: a.getChildren()){
            if (n.getId().equalsIgnoreCase(this.sLocationID)){
                System.out.println(n.getId());
                Rectangle r = (Rectangle) n;
                r.setFill(Color.GREEN);
                return;
            } 
        }

    }
}