import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

/**
* The dashboard displays lists and quantities of all chemicals, borrowed chemicals and reported chemicals. 
* 
* @author Nuha Fahad
* 
*/
public class dashboard  extends Utilities{
    @FXML
    Button returnAdmin;
    @FXML
    /**
     * This method switches the screen to teh admin.fxml screen.
     * @throws IOException
     */
    private void returnToAdmin() throws IOException {
        App.setRoot("admin");
    }



    @FXML
    Button displayAll;
    @FXML
    AnchorPane allChemPane;
    @FXML
    TilePane nameOfAllChem;
    @FXML
    TilePane numOfAllChem;
    @FXML
    /**
     * This method displays all chemicals from the Items_Quantity hashmap.
     * The method also displays number of each chemical from the above hashmap.
     */
    private void displayAllChemicals() {
        nameOfAllChem.getChildren().clear();
        numOfAllChem.getChildren().clear();
        allChemPane.setVisible(true);
        borrowedChemicalPane.setVisible(false);
        brokenChemicalPane.setVisible(false);
        for (String i : Item_Quantity.keySet()) { 
            Label r = new Label();
            r.setText(i);
            nameOfAllChem.getChildren().add(r);
        }
        for (String i : Item_Quantity.keySet()) { 
            Label r = new Label();
            r.setText(String.valueOf(Item_Quantity.get(i)));
            numOfAllChem.getChildren().add(r);
        }
    }


    @FXML
    Button brokenChemicals;
    @FXML
    AnchorPane brokenChemicalPane;
    @FXML
    AnchorPane borrowedChemicalPane;
    @FXML
    TilePane nameOfBroken;
    @FXML
    TilePane numberOfBroken;
    @FXML
     /**
     * This method displays all chemicals from the reported_items hashmap.
     * The method also displays number of each chemical from the above hashmap.
     */
    private void showBrokenChemicals() {
        nameOfBroken.getChildren().clear();
        numberOfBroken.getChildren().clear();
        brokenChemicalPane.setVisible(true);
        borrowedChemicalPane.setVisible(false);
        allChemPane.setVisible(false);
        nameOfBroken.setPrefRows(1);
        nameOfBroken.setPrefColumns(1);
        for (String i :reported_items.keySet()) { // Change "item quantity " to "broken_items")
            Label r = new Label();
            r.setText(i);
            nameOfBroken.getChildren().add(r);
        }
        for (String i : reported_items.keySet()) { // Change "item quantity " to "broken_items"
            Label r = new Label();
            r.setText(String.valueOf(reported_items.get(i)));
            numberOfBroken.getChildren().add(r);
        }

        
    }

    @FXML
    Button borrowedChemicals;
    @FXML
    Label nameLabel;
    @FXML
    TilePane nameOfBorrowed;
    @FXML
    TilePane numberOfBorrowed;
    @FXML
     /**
     * This method displays all chemicals from the borrowed_items hashmap.
     * The method also displays the number of each chemical from the above hashmap.
     */
    private void showBorrowedChemicals() {
        nameOfBorrowed.getChildren().clear();
        numberOfBorrowed.getChildren().clear();
        borrowedChemicalPane.setVisible(true);
        brokenChemicalPane.setVisible(false);
        allChemPane.setVisible(false);
        for (String i : borrowed_items.keySet()) {
            Label r = new Label();
            r.setText(i);
            nameOfBorrowed.getChildren().add(r);
        }

        for (String i : borrowed_items.keySet()) {
            Label r = new Label();
            r.setText(String.valueOf(borrowed_items.get(i)));
            numberOfBorrowed.getChildren().add(r);
        }
    }


}