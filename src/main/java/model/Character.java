package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florent L. on 06-02-17.
 */
@XmlRootElement(name = "character")
public class Character {

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty race;
    private final SimpleStringProperty age;
    private final SimpleStringProperty size;
    private final SimpleStringProperty career;
    private final ObservableList<Characteristic> characteristics;
    private final List<Aptitude> aptitudes;

    public Character() {
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.race = new SimpleStringProperty("");
        this.age = new SimpleStringProperty("");
        this.characteristics = Characteristic.generateDefaultCharacteristicList();
        this.aptitudes = new ArrayList<Aptitude>();
        this.size = new SimpleStringProperty("");
        this.career = new SimpleStringProperty("");
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @XmlElement(name = "race")
    public String getRace() {
        return race.get();
    }

    public SimpleStringProperty raceProperty() {
        return race;
    }

    public void setRace(String race) {
        this.race.set(race);
    }

    @XmlElement(name = "age")
    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    @XmlElement(name = "characteristics")
    public ObservableList<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(final List<Characteristic> list){
        this.characteristics.remove(0,this.characteristics.size());
        list.forEach(Characteristic::bindTotal);
        this.characteristics.addAll(list);
    }

    @XmlElement(name = "aptitudes")
    public List<Aptitude> getAptitudes() {
        return aptitudes;
    }

    @XmlElement(name = "size")
    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    @XmlElement(name = "career")
    public String getCareer() {
        return career.get();
    }

    public SimpleStringProperty careerProperty() {
        return career;
    }

    public void setCareer(String career) {
        this.career.set(career);
    }


}
