/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static phonebook.WriterAndReader.readNewKey;
import static phonebook.WriterAndReader.readPersonInfo;
import static phonebook.WriterAndReader.writePersonInfo;

/**
 *
 * @author mario
 */
public class FXMLDocumentController implements Initializable {
    // Info TextFields
    @FXML private TextField firstNameTextField;
    @FXML private TextField middleNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField homePhoneTextField;
    @FXML private TextField mobilePhoneTextField;
    @FXML private TextField workPhoneTextField;
    @FXML private TextField countryTextField;
    @FXML private TextField stateTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField personalEmailTextField;
    @FXML private TextField workEmailTextField;
    @FXML private TextField schoolEmailTextField;

    // Items for ABC Name Index
    @FXML private ListView<String> abcIndex;
    @FXML private Button findButton;
    @FXML private TextField searchTextField;

    // Items for Name Column
    @FXML private TableView<Person> nameTable;
    @FXML private TableColumn nameColumn;

    // Edit, OK, Cancel, Save Buttons
    @FXML private Button editButton;
    @FXML private Button okButton;
    @FXML private Button cancelButton;
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button saveButton;
    
    private String editedID;

    ObservableList<Person> persons = FXCollections.observableArrayList();

    public static int maxInt = 5890;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initListView();
        initTableView();

        //setting up Buttons
        addButton.setDisable(false);
        deleteButton.setDisable(false);
        editButton.setDisable(false);
        okButton.setDisable(true);
        cancelButton.setDisable(true);
        

    }    

    //Brian Hall
    @FXML
    public void editButtonPushed(ActionEvent e) {
        enableAccoridanText();
        okButton.setDisable(false);
        cancelButton.setDisable(false);
        addButton.setDisable(true);
        deleteButton.setDisable(true);
        
        setEditedPersonID(nameTable.getSelectionModel().getSelectedItem().getKey());
        nameTable.setDisable(true);
        
    }
    
    //Setter to set ID while maintaining the setDisable to true on the
    //name table
    private void setEditedPersonID(String editedID){
        this.editedID = editedID;
    }

    //Brian Hall
    @FXML
    public void okButtonPushed(ActionEvent e) {
       disableAccordianText();
      
       
       for(int i = 0; i < persons.size(); i++){
           if(persons.get(i).getKey().equals(editedID)){
               //Update all fields with all accordian fields
                persons.get(i).setFirstName(firstNameTextField.getText());
                persons.get(i).setMiddleName(middleNameTextField.getText());
                persons.get(i).setLastName(lastNameTextField.getText());
                persons.get(i).setHomePhone(homePhoneTextField.getText());
                persons.get(i).setMobilePhone(mobilePhoneTextField.getText());
                persons.get(i).setWorkPhone(workPhoneTextField.getText());
                persons.get(i).setCountry(countryTextField.getText());
                persons.get(i).setState(stateTextField.getText());
                persons.get(i).setCity(cityTextField.getText());
                persons.get(i).setZipCode(zipCodeTextField.getText());
                persons.get(i).setAddress(addressTextField.getText());
                persons.get(i).setPersonalEmail(personalEmailTextField.getText());
                persons.get(i).setWorkEmail(workEmailTextField.getText());
                persons.get(i).setSchoolEmail(schoolEmailTextField.getText());
                
                nameTable.getItems().clear();
                for(int j = 0; j < persons.size(); j++){
                    nameTable.getItems().add(persons.get(j));
                }
           }
       }
       
       //disable the add button and cancel button
       okButton.setDisable(true);
       cancelButton.setDisable(true);
       nameTable.setDisable(false);
       addButton.setDisable(false);
       deleteButton.setDisable(false);

    }
    
    //Brian Hall
    @FXML
    public void cancelButtonPushed(ActionEvent e) {
        disableAccordianText();
        clearAccordianText();
        
        //diable the add and cancel button
        okButton.setDisable(true);
        cancelButton.setDisable(true);
    } 

    //Brian Hall
    @FXML
    public void addButtonPushed(ActionEvent e) throws Exception {
        // Display the Add Dialog
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddContact.fxml"));    // Create FXMLLoader        
        Parent root = loader.load();                                                          // Load FXML file
        FXMLAddContactController addContactController = loader.getController();               // Get controller instance
        Scene scene = new Scene(root);
            
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add Product");
        stage.setResizable(false);
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);    // Block Events for all other Stages
        stage.showAndWait();  
        
        
         // Get the response value.
        Optional<String[]> result = addContactController.getResponse();
        if (result.isPresent()){
            String[] data = result.get();   // Get the value wrapped by the Optional object
                        
            Person p = new Person(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],
                                    data[12],data[13], Integer.toString(readNewKey()));
            maxInt++;
            persons.add(p);
            nameTable.getItems().add(p);
            
            // Sort the underlying array list of the TableView.
            Collections.sort(nameTable.getItems());   
            
        }
    }

    //Brian Hall
    @FXML
    public void deleteButtonPushed(ActionEvent e) {
        // Get the index of the selected item
        int index = nameTable.getSelectionModel().getSelectedIndex();
        
        // Securtiy Measure: No item is selected.
        if (index == -1)
            return;
        
        Person p = nameTable.getItems().get(index);
        
        //alet the user if they are sure they want to delete that Person
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remove Product Dialog");
        alert.setHeaderText("Remove Contact");
        alert.setContentText("Do you want to remove this person: " 
                + p.getFirstName() + " from your contacts?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //user chose OK
            nameTable.getItems().remove(index);     // Remove selected contact
            persons.remove(index);
            clearAccordianText();
        } else {
            //user chose CANCEL or closed the dialog do nothing
        }
    }
    
    //Brian Hall
    @FXML
    public void saveButtonPushed(ActionEvent e){
        try{
            writePersonInfo(persons);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Save File Dialog");
            alert.setHeaderText("Save File Sucessful");
            alert.setContentText("The program has sucessfully saved your contacts");
            Optional<ButtonType> result = alert.showAndWait();
            
        }
        catch(Exception ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Save File Dialog");
            alert.setHeaderText("Save File Unsucessful");
            alert.setContentText("Unable to save file.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        
    }
    
    //Brian Hall
    @FXML
    public void findButtonPushed(ActionEvent e){
        String str = searchTextField.getText().toLowerCase();
        
        nameTable.getItems().clear();
        
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getFirstName().toLowerCase().contains(str) || persons.get(i).getMiddleName().toLowerCase().contains(str)
                    || persons.get(i).getLastName().toLowerCase().contains(str)){
                nameTable.getItems().add(persons.get(i));
            }
        }
    }
    //Brian Hall
    private void initListView(){
        // Setting Up abcIndex
        abcIndex.getItems().addAll("All","A","B","C","D","E","F","G","H","I",
            "K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z");
        abcIndex.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        // Set selection mode
        // The SelectionModel is responsible for the selection mode and selected item.
        MultipleSelectionModel<String> model = abcIndex.getSelectionModel();
        model.setSelectionMode(SelectionMode.SINGLE);
        
        // Add a ChangeListener to the SelectedItemProperty of the selection model
        // Note: An anonymous class is used to specify the listener.
        model.selectedItemProperty().addListener( new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {    
                // If an item is selected ...
                if ( newValue != null) {
                    //two cases, either user clicks "All" or user picks a letter
                    if("All".equals(newValue)){
                        //show ALL person objects in the tableview
                        nameTable.getItems().clear();
                        for(int i = 0; i < persons.size(); i++){
                            nameTable.getItems().add(persons.get(i));
                        }
                    }
                    else{
                        //clear items from the tableview
                        nameTable.getItems().clear();
                        for(int i = 0; i < persons.size(); i++){
                            //If the first character of a Persons last name matches
                            //the users choice then we add it to the tableview.
                            if(newValue.charAt(0) == persons.get(i).getLastName().charAt(0)){
                                nameTable.getItems().add(persons.get(i));
                            }
                        }
                    }
                }
            }
        });
    }
    
    //Brian Hall
    private void initTableView(){
        // Set Name Table
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        nameTable.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //get the saved file and associate them with the observable list
        //and display to the table
        try{
            persons = readPersonInfo();
            for(int i = 0; i < persons.size(); i++){
                nameTable.getItems().add(persons.get(i));
            }
        }
        catch(FileNotFoundException e){
            //do nothing but alert the user that no file was found
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Read File Dialog");
            alert.setHeaderText("Read File Unsucessful");
            alert.setContentText("Unable to read any files.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        
        // Set selection mode
        MultipleSelectionModel<Person> model = nameTable.getSelectionModel();
        model.setSelectionMode(SelectionMode.SINGLE);
        
        // Add a ChangeListener to the SelectedItemProperty of the selection model
        model.selectedItemProperty().addListener( new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                if(newValue != null){
                    firstNameTextField.setText(newValue.getFirstName());
                    middleNameTextField.setText(newValue.getMiddleName());
                    lastNameTextField.setText(newValue.getLastName());
                    homePhoneTextField.setText(newValue.getHomePhone());
                    mobilePhoneTextField.setText(newValue.getMobilePhone());
                    workPhoneTextField.setText(newValue.getWorkPhone());
                    countryTextField.setText(newValue.getCountry());
                    stateTextField.setText(newValue.getState());
                    cityTextField.setText(newValue.getCity());
                    zipCodeTextField.setText(newValue.getZipCode());
                    addressTextField.setText(newValue.getAddress());
                    personalEmailTextField.setText(newValue.getPersonalEmail());
                    workEmailTextField.setText(newValue.getWorkEmail());
                    schoolEmailTextField.setText(newValue.getSchoolEmail());
                }
            }
        });
    }

    //Brian Hall
    /**
     * Set all fields in the accordian view to be editable
     */
    public void enableAccoridanText(){
        firstNameTextField.setEditable(true);
        middleNameTextField.setEditable(true);
        lastNameTextField.setEditable(true);
        homePhoneTextField.setEditable(true);
        mobilePhoneTextField.setEditable(true);
        workPhoneTextField.setEditable(true);
        countryTextField.setEditable(true);
        stateTextField.setEditable(true);
        cityTextField.setEditable(true);
        zipCodeTextField.setEditable(true);
        addressTextField.setEditable(true);
        personalEmailTextField.setEditable(true);
        workEmailTextField.setEditable(true);
        schoolEmailTextField.setEditable(true);

    }

    //Brian Hall
    /**
     * Disable all fields in the accordian view to be non-editable
     */
    public void disableAccordianText(){
        firstNameTextField.setEditable(false);
        middleNameTextField.setEditable(false);
        lastNameTextField.setEditable(false);
        homePhoneTextField.setEditable(false);
        mobilePhoneTextField.setEditable(false);
        workPhoneTextField.setEditable(false);
        countryTextField.setEditable(false);
        stateTextField.setEditable(false);
        cityTextField.setEditable(false);
        zipCodeTextField.setEditable(false);
        addressTextField.setEditable(false);
        personalEmailTextField.setEditable(false);
        workEmailTextField.setEditable(false);
        schoolEmailTextField.setEditable(false);

    }

    //Brian Hall
    /**
     * Clears all textFields in the accordian view
     */
    public void clearAccordianText(){
        firstNameTextField.clear();
        middleNameTextField.clear();
        lastNameTextField.clear();
        homePhoneTextField.clear();
        mobilePhoneTextField.clear();
        workPhoneTextField.clear();
        countryTextField.clear();
        stateTextField.clear();
        cityTextField.clear();
        zipCodeTextField.clear();
        addressTextField.clear();
        personalEmailTextField.clear();
        workEmailTextField.clear();
        schoolEmailTextField.clear();

    }
   
    
    
    
    
}
