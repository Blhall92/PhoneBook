/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian Hall
 */
public class FXMLAddContactController implements Initializable {

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField middleNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField homePhoneNumberText;
    @FXML
    private TextField mobileNumberText;
    @FXML
    private TextField workPhoneText;
    @FXML
    private TextField countryTet;
    @FXML
    private TextField stateText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField zipText;
    @FXML
    private TextField adressText;
    @FXML
    private TextField workEmailText;
    @FXML
    private TextField schoolEmailText;
    @FXML
    private TextField personalEmailText;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    
    /**
     * Initializes the controller class.
     */
    // Will store the input stored in the TextFields.
    // The Optional object will wrap a String Array with 3 elements.
    private Optional<String[]> result;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        result = Optional.empty();  // Create an empty Optional object with NO value present.
        
        firstNameText.setText("");
        middleNameText.setText("");
        lastNameText.setText("");
        homePhoneNumberText.setText("");
        mobileNumberText.setText("");
        workPhoneText.setText("");
        countryTet.setText("");
        stateText.setText("");
        cityText.setText("");
        zipText.setText("");
        adressText.setText("");
        workEmailText.setText("");
        schoolEmailText.setText("");
        personalEmailText.setText("");
        
        
    } 
        
    // Returns the user's response (i.e. the input entered)
    public Optional<String[]> getResponse() {
        return result;
    }
    
    @FXML
    private void handleOkButtonAction(ActionEvent event) {       
        String[] values = new String[15];
        values[0] = firstNameText.getText();
        values[1] = middleNameText.getText();
        values[2] = lastNameText.getText();
        values[3] = homePhoneNumberText.getText();
        values[4] = mobileNumberText.getText();;
        values[5] =  workPhoneText.getText();
        values[6] = countryTet.getText();
        values[7] =  stateText.getText();
        values[8] =  cityText.getText();
        values[9] =  zipText.getText();
        values[10] =  adressText.getText();
        values[11] =  workEmailText.getText();
        values[12] = schoolEmailText.getText();
        values[13] =  personalEmailText.getText();
        
        // Create an Optional object with a value present (i.e. the wrapped array)
        result = Optional.of(values);
        
        // Close the Stage containing the okButton
        ((Stage)okButton.getScene().getWindow()).close();
        
    }
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        // Close the Stage containing the cancelButton
        ((Stage)cancelButton.getScene().getWindow()).close();
    }
    
}
