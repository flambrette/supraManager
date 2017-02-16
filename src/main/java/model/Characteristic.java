package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Florent L. on 01-02-17.
 */
public class Characteristic {
    private final SimpleStringProperty label = new SimpleStringProperty();
    private final SimpleIntegerProperty base = new SimpleIntegerProperty();
    private final SimpleIntegerProperty bonus = new SimpleIntegerProperty();
    private final SimpleIntegerProperty total = new SimpleIntegerProperty();
    private final SimpleIntegerProperty modifier = new SimpleIntegerProperty();
    private final SimpleStringProperty comment = new SimpleStringProperty();

    public Characteristic() {

    }

    private Characteristic(final int idParam, final String labelParam){
        label.set(labelParam);
        base.set(0);
        bonus.set(0);
        total.set(0);
        modifier.set(0);
        comment.set("");

        total.bind(sum());
        total.addListener((observable, oldValue, newValue) -> this.setModifier(generateModifier(newValue.intValue())));
    }

    private ObservableValue<? extends Number> sum() {
        return base.add(bonus);
    }

    private Integer generateModifier(final Integer total){
        switch (total){
            case 6:
                return -20;
            case 7:
                return -15;
            case 8:
                return -10;
            case 9:
                return -5;
            case 10: case 11:
                return 0;
            case 12:
                return 2;
            case 13:
                return 5;
            case 14:
                return 10;
            case 15: case 16:
                return 15;
            case 17: case 18:
                return 20;
            case 19: case 20:
                return 25;
            default:
                return 0;
        }
    }

    public static ObservableList<Characteristic> generateDefaultCharacteristicList(){

        final ObservableList<Characteristic> characteristics = FXCollections.observableArrayList();
        characteristics.add(new Characteristic(1,"Courage"));
        characteristics.add(new Characteristic(2,"Will"));
        characteristics.add(new Characteristic(3,"Charisma"));
        characteristics.add(new Characteristic(4,"Beauty"));
        characteristics.add(new Characteristic(5,"Constitution"));
        characteristics.add(new Characteristic(6,"Dexterity"));
        characteristics.add(new Characteristic(7,"Perception"));
        characteristics.add(new Characteristic(8,"Intelligence"));
        characteristics.add(new Characteristic(9,"Strength"));
        return characteristics;
    }

    @XmlElement
    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }
    @XmlElement
    public int getBase() {
        return base.get();
    }

    public SimpleIntegerProperty baseProperty() {
        return base;
    }

    public void setBase(int base) {
        this.base.set(base);
    }
    @XmlElement
    public int getBonus() {
        return bonus.get();
    }

    public SimpleIntegerProperty bonusProperty() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus.set(bonus);
    }
    @XmlElement
    public int getTotal() {
        return total.get();
    }

    public SimpleIntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        this.total.set(total);
    }
    @XmlElement
    public int getModifier() {
        return modifier.get();
    }

    public SimpleIntegerProperty modifierProperty() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier.set(modifier);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
}
