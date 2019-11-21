package lotto;

import java.net.URL;                                    //importáljuk a szükséges osztályokat
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LottoNezetController implements Initializable {        // implementáljuk az Initializable interface-t
    //<editor-fold defaultstate="collapsed" desc="Class Variables">
    private final int MAX = 90;                                     //definiáljuk az állandókat
    private final int MIN = 1;
    private final String WIN0 = "Sajnos nem nyertél semmit";
    private final String WIN1 = "Egyesed van a lottón, sajnos ez nem fizet semmit";
    private final String WIN2 = "Kettesed van a lottón, sajnos ez csak mosolyra elég";
    private final String WIN3 = "Hármasod van a lottón. Ez már valami!";
    private final String WIN4 = "Négyesed van a lottón. Legyél magadra nagyon büszke!";
    private final String WIN5 = "ÖTÖSÖD VAN! Gratulálok!";
    
    private int genNum1;                                            //definiáljuk a változókat
    private int genNum2;
    private int genNum3;
    private int genNum4;
    private int genNum5;
    private int selNum1;
    private int selNum2;
    private int selNum3;
    private int selNum4;
    private int selNum5;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML items import">
    @FXML
    private Pane basePane;
    @FXML
    private Pane alertPane;                                         //panelek, gombok, beviteli mezők, cimkék létrehozása 
    @FXML
    private Label alertText;
    @FXML
    private Button alertButton;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private TextField input3;
    @FXML
    private TextField input4;
    @FXML
    private TextField input5;
//</editor-fold>
    
    @FXML                                                   // a handleAlertButton gomb eseménykezelőjénel elkészítése, panelek 
    private void handleAlertButton(ActionEvent event) {
        basePane.setDisable(false);                         //láthatóvá teszük
        basePane.setOpacity(1);                             //átlátszóságot 1-re állítjuk
        alertPane.setVisible(false);                        //itt a láthatóságot állítjuk false-ra
        alertText.setText("");                              //a text mezőnek "" értéket adunk
    }
    
    
    @FXML                                                   // a handleAlertButton gomb eseménykezelőjénel elkészítése, számok
    private void handleButtonAction(ActionEvent event) {  
        //We are generating numbers
        genNum1 = 0;
        genNum2 = 0;                                        // a gomb benyomásakor 0 értéket adunk a számoknak
        genNum3 = 0;
        genNum4 = 0;
        genNum5 = 0;
        genNum1 = getRandomNumber();                        //ezután ráhívunk egy getRandomunber() függvényt
        genNum2 = getRandomNumber();
        genNum3 = getRandomNumber();
        genNum4 = getRandomNumber();
        genNum5 = getRandomNumber();
        
                
        calculate();                                        //meghívjuk a calculate metódust
    }
    
    private void calculate(){                               //definiáljuk a calculate metódust
        
        //Are they numbers?
        try{
         selNum1 = Integer.parseInt(input1.getText());      //selectedNumbereket hozunk létre, amibe a felhaszánló által bevitt text-et Integerré alakítjuk
         selNum2 = Integer.parseInt(input2.getText());
         selNum3 = Integer.parseInt(input3.getText());
         selNum4 = Integer.parseInt(input4.getText());
         selNum5 = Integer.parseInt(input5.getText());
        }catch(Exception e){                                //ha nem sikerül, rossz számot ad meg, akkor elkapjuk a kivételt 
            alert("Nem jó számot adtál meg!");
            return;
        }
                   
        //Are they unique
        HashSet<Integer> selectedNumbers = new HashSet<>(); //HashSetbe tároljuk a megadott számokat melyn CSAK KÜLÖNBÖZŐ SZÁMOKAT TARTALMAZHAT
        selectedNumbers.add(selNum1);
        selectedNumbers.add(selNum2);
        selectedNumbers.add(selNum3);
        selectedNumbers.add(selNum4);
        selectedNumbers.add(selNum5);
        if (selectedNumbers.size() < 5){                    //HA KÜLÖNBÖZNEK A SZÁMOK, eléri az méretét(5) de ha nem, akkor kiíratjuk a hibát   
           alert("A számoknak különbözőnek kell lenniük!");
           return;
        }
      
        ArrayList<Integer> userNumbers = new ArrayList<>(selectedNumbers);  //Áttöltjük HasSet tartalmát ArrayList-be
    
        //Are they between 1-99?
        for(Integer number : userNumbers){                                  //Ellenőrizzük hogy 1-99 között vannak, figyelmeztetünk ha nem
            if (number < MIN || number > MAX){
                alert("Minden számnak 1 és 90 között kell lennie!");
                return;
            }
        }
    
        
        //We are setting the generated numbers to the labels                //A labeleknek megadjuk a generált számok értékét
        label1.setText("" + genNum1);
        label2.setText("" + genNum2);
        label3.setText("" + genNum3);
        label4.setText("" + genNum4);
        label5.setText("" + genNum5);
        
        resultCheck(userNumbers);                                           //meghívjuk a resultCheck metódust
    
        return;
    }
    
    
    private void alert(String text){                                        //alert pane-t definiáljuk
         basePane.setDisable(true);
         basePane.setOpacity(0.3);
         alertPane.setVisible(true);
         alertText.setText(text);
    }
    
    
    private void resultCheck(ArrayList<Integer> userNumbers){                  //reslutCheckk metódusnak átadjuk a fekhasználó számait
        int result = 0;                                                        // result számláló
        for(int i=0;i<userNumbers.size();i++){                                 // az Arraylist elemein futunk végig a for ciklussal 
            if(userNumbers.get(i) == genNum1 || userNumbers.get(i) == genNum2 || userNumbers.get(i) == genNum3 || userNumbers.get(i) == genNum4 || userNumbers.get(i) == genNum5)
                result++;                                                      //userNumber.get(i) fgvt használba összehasolnítjuk minden bevitt számot a generált számokkal, ha egyezik 
        }                                                                      // akkör növeljük a számlálót
            
        switch(result){                                                        // swtich függénym a találat szerinti üzenetre 
            case 0 : resultLabel.setText(WIN0);
                    break;
            case 1 : resultLabel.setText(WIN1);
                    break;
            case 2 : resultLabel.setText(WIN2);
                    break;
            case 3 : resultLabel.setText(WIN3);
                    break;
            case 4 : resultLabel.setText(WIN4);
                    break;
            case 5 : resultLabel.setText(WIN5);
                    break;
        }
    }
   
    
     
    private int getRandomNumber(){                                              //getRandomNumber fgv ami a genNumbers-t adja 
      int random = (int) (Math.random() * MAX) + MIN; //0 0.000001 0.11211212 0.784446 0.99999  random számgenerálás, és tartomány kiszámítása 
        
      if (random == genNum1 || random == genNum2 || random == genNum3 || random == genNum4 || random == genNum5){
          return getRandomNumber();                                             // rekurzív fgv, ami megvizsgálja, hogy a random szám egyenlő e a korábban generált számmal, 
      }                                                                         // megkerülve az ismétlődést, kérjük az első randomnumbert és az biztos h. nem lesz egyenlő semmivel
                                                                                //ha egyenlőség áll fennt, újrahívja a fgvt 
      return random;
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      System.out.println("Szevasz Gyulám");
    }    
    
}
