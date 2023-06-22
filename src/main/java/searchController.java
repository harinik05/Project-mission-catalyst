/**
 * The searchController has all of the methods that are implemented in the main userScreen.
 * This includes basic item search and seeing if its available to entering its quantity that
 * you want borrowed, returned, reported. Then you can view the item in a map of cabinet pane,
 * records and status, and recommended items list. 
 * @author: Harini Karthik and Mofe Soleye
*/
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class searchController extends Utilities {
    //Constants
    final int EMPTY = 0;
    final int INVALID = 1;
    final int ZERO_QTY = 2;
    final int AVAILABLE = 3;
    
    //Define the FXML variables for all of the components on the search controller screen
    @FXML
    TextField userInput;
    @FXML
    Label errorLabel;
    @FXML
    TextField quantityInput;
    @FXML
    Button returnButton;
    @FXML
    Button borrowButton;
    @FXML
    Button reportButton;
    @FXML
    Button locateButton;
    @FXML
    Button recordButton;
    @FXML
    Button recommendButton;
    @FXML
    Button backButton;
    @FXML
    ToggleButton modeButton;
    @FXML
    Rectangle backgroundRect;

     //Define variables for mode
     protected static Color wRectColor;
     protected static String wButtonTxt;
     

    //Mofe's Code
   public ArrayList<String> chemicals =new ArrayList<String>();  
  
   public int n;// = chemicals.size();
   public String input;// = ChemicalInput.getText();
   //Number of chemicals
  public String input2;// = Quantity.getText();
  public int numQuantity;// = Integer.parseInt(input2);
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
     * When the backbutton is clicked, then it will switch to the login screen for users
     */
    @FXML
    private void switchToBackScreen() throws IOException{
        App.setRoot("UserLoginScreen");

    }
     /**
     * When the word (product) is typed and you enter the search button, it will check for certain
     * conditions instead of printing out an error. For instance, it has conditions for null, no input
     * validity of product, and available products. Then, it will display labels for all of these options. 
     */
    @FXML
    protected void clickSearch(){
        try {
            String sProductIn = userInput.getText();
            //int nQty = Integer.parseInt(quantityInput.getText());
            /*
            Case 1: Null input
            */
            if(sProductIn == null){
                System.out.println("NULL OPTION");
                setLabelText(errorLabel, this.EMPTY);

            }
            /*
            Case 2: No Input/ Empty search
            */

            else if (sProductIn.trim().equalsIgnoreCase("")){
                setLabelText(errorLabel, this.EMPTY);
            }
            //Case 3: Check if product is valid or not
            else if(!checkItemExist(sProductIn)){
                setLabelText(errorLabel, this.INVALID);
            }

            
            //Case 4: If product exists, check for the quantity
            else if(isItemQtyZero(sProductIn)){
                setLabelText(errorLabel, this.ZERO_QTY);
            }

            

            //Case 5: Product exists and quantity is greater than 0
            else if (!isItemQtyZero(sProductIn)){
                setLabelText(errorLabel, this.AVAILABLE);
                Utilities.sEnteredProduct = sProductIn;
                //App.setRoot("showLocation");
            }
        }
        
        catch(Exception e){
            System.out.println("ERROR "+ e.getMessage());


        }
    }
     /**
     * setLabelText will set the label of each of the cases in clickSearch
     * @param l1 - Empty label where the text will be displayed
     * @param Type- Type of condition that is qualified or not qualified.
     */
    protected void setLabelText(Label l1, int Type){
        switch(Type){
            case EMPTY:
                System.out.println("EMPTY CASE");
                l1.setTextFill(Color.color(1, 0, 0));
                l1.setText("Value is Empty");
                break;
            case INVALID:
                System.out.println("INVALID CASE");
                l1.setTextFill(Color.color(1, 0, 0));
                l1.setText("Invalid Product");
                break;
            case ZERO_QTY:
                System.out.println("ZERO QTY CASE");
                l1.setTextFill(Color.color(1, 0, 0));
                l1.setText("Product Not Available");
                break;
            case AVAILABLE:
                 System.out.println("AVAILABLE QTY CASE");
                l1.setTextFill(Color.color(1, 0, 0));
                l1.setText("Product Available");
                break;
            
        }
    }
     /**
     * When the map button is clicked, it will switch to the map view. Then, it will also
     * gather all of the threme information from the current screen. 
     */
    @FXML
    private void switchToMapView() throws IOException {
        App.setRoot("showLocation");
        searchController.wRectColor = (Color) backgroundRect.getFill();
        searchController.wButtonTxt = modeButton.getText();
    }
    /**
     * When the recommend button is clicked, it will switch to the recommended screen  view. Then,
     * it will also gather all of the threme information from the current screen. 
     */
    @FXML
    private void switchToRecommendScreen() throws IOException{
        App.setRoot("recommendationsScreen");
        searchController.wRectColor = (Color) backgroundRect.getFill();
        searchController.wButtonTxt = modeButton.getText();
    }

    //Mofe's Methods Implemented
    //For returning items, in fxml its returnButton
     /**
     * @author Mofe
     * When returning items, then the returnButton will be clicked and this will update the values
     * on the hashmap. 
     */
    @FXML
    public void additem()
    {
        try{
        System.out.println("rest");
        //Name of chemical input
        String input = userInput.getText();
        //Number of chemicals
        String input2 = quantityInput.getText();
        int numQuantity = Integer.parseInt(input2);
        //Put item returned in returned_items hashmap
        Utilities.returned_items.put(input,numQuantity);
        //Check if item exists through harini's method and then update values for current hashmap
        Utilities.Item_Quantity.put(input,numQuantity+Utilities.Item_Quantity.get(input));
        //Adding to the item_status hashmap for Harini's method
        Utilities.linkItem_Status.add(input + "                                              "+ Utilities.returned_items.get(input)+ "                                              "+" returned");
        System.out.println(Utilities.Item_Quantity);
        System.out.println(Utilities.returned_items);
        System.out.println(Utilities.Item_Quantity);
        //Adding elements to the array list (chemicals)
        /*for(int j=0; j < numQuantity;j++)
        {
            System.out.println("chemical added");
            chemicals.add(input);
        }
     for(int i =0; i < chemicals.size();i++)
     {
     
    System.out.println(chemicals.get(i));
     }*/}catch (Exception e){
         System.out.println(e.getMessage());

     }
    
    }
      /**
     * This method will check if the product left is negative. Then there will be a different
     * message printed for this
     * @param nQty= current quantity of product
     * @param nQtyToRemove= number of products to remove
     * @return boolean = true or false. false when the product left is negative so it wont remove
     */
    public boolean checkNegativeProductQty(int nQty, int nQtyToRemove){
        int numLeft = nQty - nQtyToRemove;
        if (numLeft<0){
            return false;
        }
        else {
            return true;
        }

        
    }
     /**
     * @author Mofe
     * removeItem is for borrowing items. It will remove it from the overall quantity and 
     * add it to the removed items special hashmap to display in the admin
     */
    @FXML
    protected void removeItem() {
        try{

    System.out.println("Remove item start");
    String input = userInput.getText();
    //Number of chemicals
    String input2 = quantityInput.getText();

    int numQuantity = Integer.parseInt(input2);

    //case 5: check if the qty available
    if(checkNegativeProductQty(Utilities.Item_Quantity.get(input), numQuantity) == false){
        System.out.println(input+" : "+ numQuantity);
        errorLabel.setText("Exceeding Product Quantity");
        //setLabelText(errorLabel, this.ZERO_QTY);
        
    }else{
        //setLabelText(errorLabel, this.AVAILABLE);
            //Put item borrowed in item_borrowed hashmap
        errorLabel.setText("Product Available");
        Utilities.borrowed_items.put(input,numQuantity);
        Utilities.Item_Quantity.put(input,Utilities.Item_Quantity.get(input)- numQuantity);
        //Adding to the item_status hashmap for Harini's method
        Utilities.linkItem_Status.add(input+ "                                              "+Utilities.borrowed_items.get(input)+ "                                              "+" borrowed");
        
        System.out.println(Utilities.Item_Quantity);
        System.out.println(Utilities.borrowed_items);
        System.out.println(Utilities.Item_Quantity);

    }}catch(Exception e){
        System.out.println(e.getMessage());
    }
   
   /*  for(int j=0; j < numQuantity;j++)
     {
        chemicals.remove(input);
     }
     System.out.println(chemicals);*/

}
 /**
     * @author Mofe
     * When reported, the items will be removed. However, it won't be displayed on borrowed items
     * hashmap. It will be a function that is called when you click on report Item.
     * 
     */
protected void removeItemWhenReported() {
    try{

System.out.println("Remove item start");
String input = userInput.getText();
//Number of chemicals
String input2 = quantityInput.getText();

int numQuantity = Integer.parseInt(input2);

//case 5: check if the qty available
if(checkNegativeProductQty(Utilities.Item_Quantity.get(input), numQuantity) == false){
    System.out.println(input+" : "+ numQuantity);
    errorLabel.setText("Product Not Available");
    //setLabelText(errorLabel, this.ZERO_QTY);
    
}else{
    //setLabelText(errorLabel, this.AVAILABLE);
        //Put item borrowed in item_borrowed hashmap
    errorLabel.setText("Product Available");
    //Utilities.borrowed_items.put(input,numQuantity);
    Utilities.Item_Quantity.put(input,Utilities.Item_Quantity.get(input)- numQuantity);
    //Adding to the item_status hashmap for Harini's method
    //Utilities.linkItem_Status.add(input+ "                                              "+Utilities.borrowed_items.get(input)+ "                                              "+" borrowed");
    
    System.out.println(Utilities.Item_Quantity);
    System.out.println(Utilities.borrowed_items);
    System.out.println(Utilities.Item_Quantity);

}}catch(Exception e){
    System.out.println(e.getMessage());
}

/*  for(int j=0; j < numQuantity;j++)
 {
    chemicals.remove(input);
 }
 System.out.println(chemicals);*/

}

 /**
     * @author Mofe
     * When the item is reported, it will remove the item quantity from the list. Then, it will update 
     * its status. 
     */
@FXML
public void reportItem()
{
    try{
    String input = userInput.getText();
     // ArrayList<String> reporteditems=new ArrayList<String>();  
     String input2 = quantityInput.getText();
     int numQuantity = Integer.parseInt(input2);
     removeItemWhenReported();
    Utilities.reported_items.put(input,numQuantity);
    //Adding to the item_status hashmap for Harini's method
    Utilities.linkItem_Status.add(input +"                                              "+Utilities.reported_items.get(input)+ "                                              "+ " reported");
    System.out.println(Utilities.Item_Quantity);
    System.out.println(Utilities.reported_items);
    System.out.println(Utilities.Item_Quantity);
 /* if( chemicals.contains(input) == true)
  {
     if(numQuantity<chemicals.size())
     {
        removeItem();
      for(int j=0; j < numQuantity;j++)
      {
         reporteditems.add(input);
      }
     }else{
         System.out.println("cannot be reported");
     }
  } */
  //System.out.println(reported_items + "reported");
  //System.out.println(chemicals + "chemicals");
}
catch(Exception e){
    System.out.println(e.getMessage());
}
}
   /**
     * When the record button is clicked, it will switch to the records view. Then, it will also
     * gather all of the threme information from the current screen. 
     */
    @FXML
    private void switchToRecordButton() throws IOException{
        App.setRoot("individualUserRecords");
        searchController.wRectColor = (Color) backgroundRect.getFill();
        searchController.wButtonTxt = modeButton.getText();
    }


    

}