/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Renzo Pretto
 */
public class WriterAndReader {

    public static ObservableList<Person> readPersonInfo() throws FileNotFoundException{
    
        ObservableList<Person> list = FXCollections.observableArrayList();
        
        File file;
        file = new File("Contacts.txt");
        File file2;
        file2 = new File("ContactKeys.txt");
        Scanner sc = new Scanner(file);
        Scanner sc2 = new Scanner(file2);
        String s = "";
        int counter = 0;
        String firstName = " ", middleName = " ", lastName = " ", homePhone = " ", mobilePhone = " ", workPhone = " ",
            country = " ", state = " ", city = " ", zipCode = " ", address = " ", personalEmail = " ", workEmail = " ", schoolEmail = " ", birthday = " ", key = " ";
        while (sc.hasNextLine()) {
            firstName = sc.nextLine();
            middleName = sc.nextLine();
            lastName = sc.nextLine();
            homePhone = sc.nextLine();
            mobilePhone = sc.nextLine();
            workPhone = sc.nextLine();
            country = sc.nextLine();
            state = sc.nextLine();
            city = sc.nextLine();
            zipCode = sc.nextLine();
            address = sc.nextLine();
            personalEmail = sc.nextLine();
            workEmail = sc.nextLine();
            schoolEmail = sc.nextLine();
            key = sc.nextLine();
            list.add(new Person(firstName, middleName, lastName, homePhone, mobilePhone,
            workPhone, country, state, city, zipCode, address, personalEmail, workEmail, schoolEmail, key));
        }
        
        sc.close();
        sc2.close();
        
        return list;
    }
    
    public static void writePersonInfo(ObservableList<Person> list) throws Exception {
        
        Scanner kb = new Scanner(System.in);
        
        PrintWriter writer = new PrintWriter("Contacts.txt");
        
        for (int i = 0; i < list.size(); i++) {
            writer.println(list.get(i).getFirstName());
            writer.println(list.get(i).getMiddleName());
            writer.println(list.get(i).getLastName());
            writer.println(list.get(i).getHomePhone());
            writer.println(list.get(i).getMobilePhone());
            writer.println(list.get(i).getWorkPhone());
            writer.println(list.get(i).getCountry());
            writer.println(list.get(i).getState());
            writer.println(list.get(i).getCity());
            writer.println(list.get(i).getZipCode());
            writer.println(list.get(i).getAddress());
            writer.println(list.get(i).getPersonalEmail());
            writer.println(list.get(i).getWorkEmail());
            writer.println(list.get(i).getSchoolEmail());
            writer.println(list.get(i).getKey());
        }

            writer.close();
        
    }
    
    /**
     * This method opens the Contact Key file and reads a new unique key.
     * @return
     * @throws FileNotFoundException 
     */
    public static String readNewKey() throws FileNotFoundException{ //TODO!
        File file = new File("ContactKeys.txt");
        
        Scanner reader = new Scanner(file);
        int key = reader.nextInt();
        key++;
        reader.close();
        
        PrintWriter keyFile = new PrintWriter("ContactKeys.txt");
        keyFile.println(key);
        keyFile.close();
        
        return Integer.toString(key);
        
        
    }
    
}