/**
 * The adminScreenLoginController portion of the program will obtain the input usernames and 
 * passwords of the admin user and authenticate it. It will check if the credential pair matches
 * the existing one in the system, before giving them the access to special dashboards
 * to monitor their lab equipments and chemicals. 
 * @author: Harini Karthik 
*/

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class adminScreenLoginController extends Utilities {
    //Define all of the fxml components
    @FXML
    TextField aInputUsername;
    @FXML
    TextField aInputPassword;
    @FXML
    Button aEnterButton;

    String sInputUsername ;
    //= aInputUsername.getText();
    int iInputPassword ;
    //= aInputPassword.getText();
    
    /**
     * The method, putEnteredDataIntoHashmap, is for putting the entered values of username and 
     * password into the hashmap as a key value pair. 
     * @param enteredUsername the username that is entered
     * @param enteredPassword the password that is entered (must be integer)
     */
    public void putEnteredDataIntoHashmap(String enteredUsername, int enteredPassword){
        Utilities.entered_username_password.put(enteredUsername, enteredPassword);
    }

    /**
     * The method, ifMapContains,will loop through every key value set of the main hashmap and check if the
     * key value pair of subOfMap exists. If it does, then it will return true. Else it will return 
     * false.
     * @param mainOfMap the main hashmap which contains everything from UsernamePassword.txt file
     * @param subOfMap the hashmap containing putEnteredDataIntoHashmap things which include 
     * the input of username and password as key value pair
     * @return boolean (true or false) if the subOfMap exists under mainOfMap
     */
    public boolean ifMapContains(Map<String, Integer> mainOfMap, Map<String, Integer> subOfMap) {
        for (String key : mainOfMap.keySet()) {
            if (subOfMap.containsKey(key) && subOfMap.get(key).equals(mainOfMap.get(key))) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method checkUserPassMatch, will capture the inputed data, place it in the hashmap (username_password), 
     * and check if the condition of ifMapContains passes
     */
    @FXML
    public void checkUserPassMatch() throws IOException{
        this.sInputUsername = aInputUsername.getText();
        this.iInputPassword = Integer.parseInt(aInputPassword.getText());

        putEnteredDataIntoHashmap(sInputUsername, iInputPassword);
        if (ifMapContains(Utilities.Username_Password, Utilities.entered_username_password) == true){
            App.setRoot("admin");
        }else{
            //passwordWarning.setVisible(true);
            System.out.println("THERE IS SOMETHING WRONG WITH USERNAME AND PASSWORD. TRY AGAIN");
        }
        
    }

    /**
     * When the backbutton is clicked, it will return to the UserScreen.
     */
    @FXML 
    Button backButton;
    @FXML
    public void returnToHome(ActionEvent event) throws IOException{
        App.setRoot("UserScreen");
    }

    

}
