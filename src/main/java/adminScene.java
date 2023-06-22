import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
* The adminScene shows administrators teh quantity and location of each chemical
* Also allows the administrator to remove items from teh items_quantity hashmap
* 
* @author Nuha Fahad
* 
*/
public class adminScene extends Utilities{
    public String currentChem;
    public int currentChemQuantity;
    public String quantityToRemove;

    
    @FXML
    Button dashboard; //Button that calls teh method switchToDashboard
    @FXML
    /**
     * This method switches the screen to the dashbord.fxml file.
     * @throws IOException
     */
    private void switchToDashboard() throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    Button backAdmin; //
    @FXML
    /**
     * Thid method switches the screen to the initial user screen
     * @throws IOException
     */
    private void switchToPrevious() throws IOException {
        App.setRoot("UserScreen");
    }

    //FXML tags
    @FXML
    Button removeItem; //Button to call the removeAnItem method
    @FXML
    TextField enterQuantity; //user enters the quantity to remove into this field
    @FXML
    Label nullWarningLabel; //This is a label that shows an error message if teh field is empty
    @FXML
    /**
     * This method checks the enterQuantity text field and presents warning labels according to the error. 
     * This method also calls the removeChem method.
     * @param event is an event signalled due to the removeItem button pressed
     */
    public void removeAnItem(ActionEvent event){
        if (enterQuantity.getText().isEmpty()){
            nullWarningLabel.setVisible(true);
        }
        else {
            nullWarningLabel.setVisible(false);
        }
        removeChem(currentChem,Integer.parseInt(enterQuantity.getText()));
    }


    @FXML
    Label numOfItem; //The predefined quantity of the chemical from the hashmap Items_Quantity.
    @FXML
    Label warningLabel; //A label to show error messages 
    @FXML
    /**
     * This method takes input and uses that to remove the quantity of a chemical from the Items_Quanity hashmap.
     * The method also shows labels according to errors in userinput.
     * @param chemicalName the string that represents the chemical entered by the user.
     * @param removeNumber represents the amount  of chemical to remove from the Items_Quantity hashmap
     */
    public void removeChem(String chemicalName, int removeNumber) {
        try {
        //restrictions if removeNumber is greater than the amount of chemical that exists
        if (removeNumber> Item_Quantity.get(chemicalName)){
            warningLabel.setText("Quantity cannot exceed number of items of this type!");
            warningLabel.setVisible(true);
            enterQuantity.clear();
        }
        //method moves forward as long as there are no errors in text input
        else {
            int newValue = Item_Quantity.get(chemicalName) - removeNumber;
            Item_Quantity.replace(currentChem, Item_Quantity.get(chemicalName), newValue);
            numOfItem.setText(String.valueOf(newValue));
            warningLabel.setVisible(false);
        }
        //try-catch to display exact error
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
            
        }
    }


    @FXML
    Button refresh;
    @FXML
    /**
     * This method 'refreshes' the screen and clears all fields.
     * @param event is signalled to clear all labels and text fields 
     */
    public void refreshAdmin(ActionEvent event){
        System.out.println("refresh clicked");
        DataName.setText("");
        numOfItem.setText("");
        locationOfItems.setText("");
        adminChemEntry.clear();
    }

     

    //FXML Tags
    @FXML
    TextField adminChemEntry; 
    @FXML
    /**
     * This method gets text from the input field and saves it as teh variables currentchem.
     * @return currentchem The variable that stores the string referring to chemical entered by user.
     */
    public String chemEntry(){
        currentChem = adminChemEntry.getText();
        return currentChem;
    }


    @FXML
    public Button saveChemName;
    @FXML
    Label warningLabel2;
    @FXML
    /**
     * This method calls the method chemEntry and the method showData.
     * This method also sets restrictions on the entered text and displays warning labels according to the input. 
     * @param event signalled by the Button saveChemName 
     */
    public void saveCurrentChem(ActionEvent event){
        chemEntry();
        for (String i : Item_Quantity.keySet()) {
            if (currentChem.equals(i)){
                warningLabel2.setVisible(false);
                showData();
                break;
            }
            else {
                adminChemEntry.clear();
                warningLabel2.setVisible(true);
            }
        }

        
    }

    
    @FXML
    Label DataName; //Displays the current chemicals name
    @FXML
    Label locationOfItems; //Displays the location of teh current chemical
    @FXML
    /**
     * This method retrieves data form the Items_Quantity and Items_Location hashmap and displays that data in labels.
     */
    public void showData(){
        DataName.setText(currentChem);
        numOfItem.setText(String.valueOf(Item_Quantity.get(currentChem)));
        locationOfItems.setText(String.valueOf(Item_Location.get(currentChem)));
    }

    
    @FXML
    ToggleButton toggleMode; //toggle button
    @FXML
    Rectangle adminScreenRect; //backdrop rectangle that changes colour
    @FXML
    /**
     * This method uses a toggle button to change the colors on the screen.
     */
    public void switchMode(){
        if (toggleMode.isSelected()){
            adminScreenRect.setFill(Color.web("a3e2ff"));
            adminScreenRect.setStroke(Color.BLUE);
        }
        else {
            adminScreenRect.setFill(Color.web("09ebde"));
            adminScreenRect.setStroke(Color.web("160873"));
        }
    }


    
}