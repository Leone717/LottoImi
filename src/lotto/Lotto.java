package lotto;

import javafx.application.Application;                      //importáljuk a szükséges dolgokat
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Lotto extends Application {                    //a Lotto-t kibővítjük az Application osztállyal
    
    @Override                                               //Felülírjuk a Application start metódusát
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LottoNezet.fxml"));   //betöltjük az fxml fájlt (LottoNezet)
        
        Scene scene = new Scene(root);                      //ÚJ színpadot hozunk létre
        
        stage.setScene(scene);                              //a stage-hez beállítjük a színpadot(ani a Lottonezetet tartalmazza)     
        stage.setResizable(false);                          //Az ablak méretét lefixáljuk
        stage.setTitle("Lottó program");                    //Ablak címe 
        stage.show();                                       //Az ablakot meg fogja mutatni a program
    }

      public static void main(String[] args) {
        launch(args);
    }
    
}
