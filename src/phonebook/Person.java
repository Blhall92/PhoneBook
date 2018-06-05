/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author mario
 */
public class Person implements Comparable<Person> {
    private SimpleStringProperty firstName, middleName, lastName, homePhone, mobilePhone, workPhone,
            country, state, city, zipCode, address, personalEmail, workEmail, schoolEmail, birthday, key,
            firstAndLast;
    
    public Person(String firstName, String middleName, String lastName, String homePhone, String mobilePhone,
    String workPhone, String country, String state, String city, String zipCode, String address,
    String personalEmail, String workEmail, String schoolEmail, String key) {
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.homePhone = new SimpleStringProperty(homePhone);
        this.mobilePhone = new SimpleStringProperty(mobilePhone);
        this.workPhone = new SimpleStringProperty(workPhone);
        this.country = new SimpleStringProperty(country);
        this.state = new SimpleStringProperty(state);
        this.city = new SimpleStringProperty(city);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.address = new SimpleStringProperty(address);
        this.personalEmail = new SimpleStringProperty(personalEmail);
        this.workEmail = new SimpleStringProperty(workEmail);
        this.schoolEmail = new SimpleStringProperty(schoolEmail);
        this.key = new SimpleStringProperty(key);
        this.firstAndLast = new SimpleStringProperty(lastName + ", " + firstName);
    }

    public String getFirstAndLast() {
        return firstAndLast.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }
    
    public String getMiddleName() {
        return middleName.get();
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = new SimpleStringProperty(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }
    
    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }
    
    public String getHomePhone() {
        return homePhone.get();
    }
    
    public void setHomePhone(String homePhone) {
        this.homePhone =  new SimpleStringProperty(homePhone);
    }
    
    public String getMobilePhone() {
        return mobilePhone.get();
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone =  new SimpleStringProperty(mobilePhone);
    }
    
    public String getWorkPhone() {
        return workPhone.get();
    }
    
    public void setWorkPhone(String workPhone) {
        this.workPhone =  new SimpleStringProperty(workPhone);
    }
    
    public String getCountry() {
        return country.get();
    }
    
    public void setCountry(String country) {
        this.country =  new SimpleStringProperty(country);
    }
    
    public String getState() {
        return state.get();
    }
    
    public void setState(String state) {
        this.state =  new SimpleStringProperty(state);
    }
    
    public String getCity() {
        return city.get();
    }
    
    public void setCity(String city) {
        this.city =  new SimpleStringProperty(city);
    }
    
    public String getZipCode() {
        return zipCode.get();
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode =  new SimpleStringProperty(zipCode);
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public void setAddress(String address) {
        this.address =  new SimpleStringProperty(address);
    }
    
    public String getPersonalEmail() {
        return personalEmail.get();
    }
    
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail =  new SimpleStringProperty(personalEmail);
    }
    
    public String getWorkEmail() {
        return workEmail.get();
    }
    
    public void setWorkEmail(String workEmail) {
        this.workEmail =  new SimpleStringProperty(workEmail);
    }
    
    public String getSchoolEmail() {
        return schoolEmail.get();
    }
    
    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail =  new SimpleStringProperty(schoolEmail);
    }
    
    public String getKey() {
        return key.get();
    }
    
    public void setKey(String key) {
        this.key = new SimpleStringProperty(key);
    }

    /**
     * Uses the Strings compareTo method in order to compare Person object's 
     * last name.
     * @param otherPerson
     * @return 
     */
    @Override
    public int compareTo(Person otherPerson) {
        return this.getLastName().compareTo(otherPerson.getLastName());
    }
} 

