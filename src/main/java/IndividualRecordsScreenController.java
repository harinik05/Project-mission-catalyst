/**
 * The individualRecordsScreen portion of the program will collect the records of a particular user at
 * a specific moment of time in the program. It will store the items borrowed, reported, and returned, and put
 * it in the appropriate hashmaps for the admin to view. Furthermore, it prints a receipt for the user to keep 
 * track of their selections. 
 * @author: Harini Karthik 
*/

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class IndividualRecordsScreenController extends Utilities {
    //Define FXML variables for each of the defined components
    @FXML
    Label lUsernameID;
    @FXML
    Label lUserData;
    @FXML
    Button printButton;
    @FXML
    Button exitButton;

    @FXML
    Button backButton;
    @FXML
    Rectangle backgroundRect;
    @FXML
    ToggleButton modeButton;

    //Define variables for mode
    protected static Color wRectColor;
    protected static String wButtonTxt;
    //Define some fonts here:
    private static Font BiggerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    private static Font TitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 45,Font.BOLD, BaseColor.ORANGE);
    private static Font StarFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD, BaseColor.ORANGE);
    /**
     * The initialize method will call the methods to display the UserID and the linked list with 
     * the item statuses
     */
    public void initialize(){
        displayUserName();
        displayHashmapOnLabel();
    }

    /**
     * displayUserName will get the text from the userLoginScreen that has the user barcode. 
     * Also, it will call ModeActivate for setting initial conditions for dark/light mode
     */
    public void displayUserName(){
        lUsernameID.setText(userLoginScreenController.sUserID);
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
     * The method, displayHashmapOnLabel will convert the linked list with all of the statuses to a string 
     * and set the text as the label in lUserData
     */
    public void displayHashmapOnLabel(){
        String hashmapAsString = Utilities .linkItem_Status.toString();

        //for (int i = 0;i<=Utilities.item_status.size();i++){
            hashmapAsString = hashmapAsString.replaceAll(",", " \n");
            hashmapAsString = hashmapAsString.replaceAll("\\[", "");
            hashmapAsString = hashmapAsString.replaceAll("\\]", "");
            hashmapAsString = hashmapAsString.replaceAll("\\=", "");
            lUserData.setText(hashmapAsString);
        //}
}
    /**
     * When printHashmapAsPara is called, then it will take the linked list and convert it as a string 
     * and return it as properly formatted. 
     * @return hashmapAsString - the status of all item, item qty, and name will be converted to 
     * a string all together to be returned in this function. 
     */
    public String printHashmapAsPara(){
        String hashmapAsString = Utilities.linkItem_Status.toString();

        try{

            hashmapAsString = hashmapAsString.replaceAll(",", " \n");
            hashmapAsString = hashmapAsString.replaceAll("\\[", "");
            hashmapAsString = hashmapAsString.replaceAll("\\]", "");
            hashmapAsString = hashmapAsString.replaceAll("\\=", "");
            //String hashmapAsString2 = hashmapAsString1.replaceAll("}", "");
            return hashmapAsString;
            //lUserData.setText(hashmapAsString);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;

        
}
    /**
     * The method, switchToExitButton, will clear up all of the values in this temporary linked list
     * once the exit button is clicked on. This will enable the next user to login fresh with only their records
     * printed on the screen. However, all the statuses will still be stored for the admin to see in 
     * appropriate hashmaps for reported, returned, borrowed items. Then, it will also capture the current theme 
     * of the screen before moving on to the next one. 
     */
    @FXML
    private void switchToExitButton() throws IOException{
        Utilities.linkItem_Status.clear();
        App.setRoot("UserScreen");
        IndividualRecordsScreenController.wRectColor = (Color) backgroundRect.getFill();
        IndividualRecordsScreenController.wButtonTxt = modeButton.getText();
    }

    /**
     * The method, generatePDF, will create a new document via Pdf Writer and print out all of the statuses
     * defined in the method printHashmapAsPara
     */
    public void generatePDF()
   {
      Document document = new Document();
      try
      {
        String fileName = "Records Receipt ----" + userLoginScreenController.sUserID;
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName+ "Printed.pdf"));
         document.open();
         //Insert the title of the page
         document.add(new Paragraph("VIEW YOUR RECORDS HERE", TitleFont));
         document.add(new Paragraph("***************************************", StarFont));
         document.add(new Paragraph(printHashmapAsPara(), BiggerFont));
         document.add(new Paragraph("***************************************", StarFont));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
   /**
     * When the backbutton is pressed, it will go to the userInput Screen.
     */
   @FXML
   private void switchToBackScreen() throws IOException{
       App.setRoot("userInput");

   }

}
