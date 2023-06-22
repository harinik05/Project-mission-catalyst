import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static ArrayList<String> listOfItems = new ArrayList<>();
    

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Initialize();
        scene = new Scene(loadFXML("WelcomeScreen"), 866, 601);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

   /**
     * The method, Initialize, will take in the function LoadAllDataIntoDictionary from utilities and 
     * load the data from the file to display it as a hashmap
     */
    public void Initialize(){
        Utilities ut = new Utilities();
        ut.LoadAllDataIntoDictionary(Utilities.Item_Quantity, Utilities.ITEM_QTY);
        ut.displayHashMap(Utilities.Item_Quantity);
        System.out.println("----------------------------------------");
        ut.LoadAllDataIntoDictionary(Utilities.Item_Location, Utilities.ITEM_LOC);
        ut.displayHashMap(Utilities.Item_Location);
        System.out.println("____________________________________");
        System.out.println(ut.getItemLocation("xyz"));
        ut.LoadAllDataIntoDictionary(Utilities.number_username, Utilities.NUM_USER);
        ut.displayHashMap(Utilities.number_username);
        System.out.println("____________________________________");
        ut.LoadAllDataIntoDictionary(Utilities.Username_Password, Utilities.USER_PASS);
        ut.displayHashMap(Utilities.Username_Password);
        System.out.println("____________________________________");
    }

    public static void main(String[] args) {
        launch();
    }

}