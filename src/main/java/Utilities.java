/**
 * The utilities has a tool of helper functions that are called from different 
 * methods within the controllers. 
 * @author: Harini Karthik
*/
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.InputStreamReader;

public class Utilities {

    public static final String ITEM_QTY = "ItemQuantity.txt";
    public static final String ITEM_LOC = "ItemLocation.txt";
    public static final String NUM_USER = "NumberUsername.txt";
    public static final String USER_PASS = "UsernamePassword.txt";
    public final String REC_FILE = "RecommendedProductSheet.csv";
    public static boolean Is_Selected = false;
    /* *
    @FXML
    ToggleButton modeButton;
    @FXML
    Rectangle backgroundRect;
    */
    

     //mapping equipment name and its quantity
     public static Map<String, Integer>Item_Quantity = new HashMap<String,Integer>();
     //mapping equipment name and its location
     public static Map<String, Integer> Item_Location = new HashMap<String,Integer>();
     //Hashmap for reported items
    public static Map<String, Integer> reported_items = new HashMap<String, Integer>();
   //Hashmap for borrowed items
    public static Map<String, Integer> borrowed_items = new HashMap<String, Integer>();
   //Hashmap for returned items
    public static Map<String, Integer> returned_items = new HashMap<String, Integer>();
    //Hashmap for usernames for the customers only
    public static Map<String, Integer> number_username = new HashMap<String, Integer>();
    //Hashmap to store the usernames and password
    public static Map<String, Integer> Username_Password = new HashMap<String, Integer>();
    //Create an empty hashmap for storing the username and password entered
    public static Map<String, Integer> entered_username_password = new HashMap<String, Integer>();
    
    //Hashmap for status and item (individialized data)
    public static Map<String, String> item_status = new HashMap<String, String>();
    public static LinkedList<String> linkItem_Status = new LinkedList<String>();
     public static String sEnteredProduct;
     //public String sInputUserID = userLoginScreenController.inputUserID.getText();

    //Set two cases for background color
    /**
     * ModeActivate will take in the parameters of color and emoji text for day/
     * bright mode. Initially, it will set the text according to the previous screen theme value.
     * Then if the button is selected then it will make it dark mode. Or else its light mode.
     * @param backgroundClr - color of the background when if statement is valid
     * @param subTxt- text when if statement is valid
     * @param rbackground- rectangle's background which has a color which can be grabbed
     * @param tSwitchButton- toggle button for light and dark mode
     * @param preferredColor - initial setting of color from previous screen
     * @param subString - initial setting of label from previous screen
     */
  public void ModeActivate(String backgroundClr, String subTxt, Rectangle rbackground, ToggleButton tSwitchButton, Color preferredColor, String subString){

    tSwitchButton.setText(subString);
    rbackground.setFill(preferredColor);
    System.out.println(Is_Selected);
    //Dark Mode if Mode button is selected
      if (Is_Selected){
          rbackground.setFill(Color.valueOf(backgroundClr));
          tSwitchButton.setText(subTxt);
          //System.out.println("Value to set "+ subTxt);
      }
      else if(tSwitchButton.isSelected()){
        rbackground.setFill(Color.valueOf(backgroundClr));
        tSwitchButton.setText(subTxt);
        //System.out.println("Value to set "+ subTxt);
      }
  //Default light mode if the mode button is not selected
      else{
          subTxt = "🌞";
          //System.out.println("Value to set "+ "☀️");
          tSwitchButton.setText(subTxt);
          rbackground.setFill(Color.valueOf("#30d5c8"));
          
      }         
        
    }
    
      /**
     * loadAllDataIntoDictionary will open and read the dictionary file using BufferedReader and read it line by line. Then, 
     * Thenm it will store it as an array by splitting at the comma. Once it grabs the index of these arrays, then its
     * values are potentially stored in the hashmap.
     * @param mp- hashmap to store all the data from file into
     * @param sFileName- name of the txt file that needs to be read
     */
     protected void LoadAllDataIntoDictionary(Map<String, Integer> mp, String sFileName){
        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(sFileName);
            assert in != null;
            //BufferedReader - read characters per line
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line (null = end of doc)
            while ((strLine = reader.readLine()) != null) {
                String[] arrTemp;
                arrTemp = strLine.split(",");

                mp.put(arrTemp[0], Integer.parseInt(arrTemp[1].trim()));
            }
            //Close the input stream
            in.close();

        } 
        catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
     }

     

      /**
     * ReadRecommendation will read the files using Buffered Reader and split it at the comma. Once it 
     * grabs the values for each index, it will find the key value pair for the specific product name and store
     * those items into an array. 
     * @param productName- entered product that you need recommended items for 
     * @return arrProd - list of recommended items
     */
     protected ArrayList<String> ReadRecommendation(String productName){
         ArrayList<String> arrProd = new ArrayList<String>();
        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(this.REC_FILE);
            assert in != null;
            //BufferedReader - read characters per line
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line (null = end of doc)
            while ((strLine = reader.readLine()) != null) {
                String[] arrTemp;
                arrTemp = strLine.split(",");
                if (arrTemp[0].equalsIgnoreCase(productName)){
                    arrProd.add(arrTemp[1]);
                }               
               
            }
            //Close the input stream
            in.close();

        } 
        catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return arrProd;
     }


     //displaying all the elements of hashmap
     
    //display key-value of Hashmap (length/ycoordinate and ID)
     /**
     * To print the hashmap in an organized manner, you will get the key and value and 
     * seperate it using for loop
     * @param mp- hashmap needed to print
     */
    protected void displayHashMap(Map<String,Integer> mp){
        for(Map.Entry<String, Integer> entry: mp.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

     /**
     * getItemLocation will take in the ItemLocation Hashmap and find the item and its 
     * numbered location from 1-20, then it will convert to the ID location in the 
     * shelf display - A or B
     * @param sItem- product name
     * @return sLocation- location of the product
     */
    protected String getItemLocation(String sItem){
        String sLocation = "";
        int nPos;

        if (Utilities.Item_Location.containsKey(sItem)){
            nPos = Utilities.Item_Location.get(sItem);
            if (nPos > 10) {
                sLocation = "B"+String.valueOf(nPos - 10);
            } else {
                sLocation = "A"+String.valueOf(nPos);
            }

        }
        return sLocation;
    }
    
     /**
     * If there is a key in the hashmap that is equal to the product typed, then 
     * it will return true.
     * @param sItem- name of product
     * @return sReturn - boolean of whether it exists or not
     */
    protected boolean checkItemExist(String sItem){
        Boolean sReturn = false;
        if (Utilities.Item_Location.containsKey(sItem.toLowerCase())){
            sReturn= true;
        }

        return sReturn;
    }

     /**
     * If the quantity of the item is equal to 0, then it will show that isItemQtyZero 
     * case is true
     * @param sItem - name of product
     * @return SReturn - true if item qty of product is 0
     */
    protected boolean isItemQtyZero(String sItem){
        Boolean sReturn = false;
        if(Utilities.Item_Quantity.get(sItem.toLowerCase()) == 0)
        {
            sReturn = true;
        }
        return sReturn;
    }

     /**
     * When the product quantity available is greater than typed quantity needed, this 
     * condition will then be valid.
     * @param sItem- name of item
     * @param nQty- qty of item
     * @return sReturn= true if there are more items than qty asked
     */
    protected boolean isItemQtyAvailable(String sItem, int nQty){
        Boolean sReturn = false;
        if(Utilities.Item_Quantity.get(sItem.toLowerCase()) >= nQty)
        {
            sReturn = true;
        }
        return sReturn;
    }
    
}
