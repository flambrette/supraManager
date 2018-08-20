package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Florent L.
 */
public class Characteristic {
    private final SimpleStringProperty label = new SimpleStringProperty();
    private final SimpleIntegerProperty base = new SimpleIntegerProperty();
    private final SimpleIntegerProperty bonus = new SimpleIntegerProperty();
    private final SimpleIntegerProperty total = new SimpleIntegerProperty();
    private final SimpleIntegerProperty modifier = new SimpleIntegerProperty();
    private final SimpleStringProperty comment = new SimpleStringProperty();
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();

    public Characteristic() {

    }

    private Characteristic(final int idParam, final String labelParam){
        id.set(idParam);
        label.set(labelParam);
        base.set(0);
        bonus.set(0);
        total.set(0);
        modifier.set(0);
        comment.set("");

        bindTotal();
    }

    public void bindTotal() {
        total.bind(sum());
        total.addListener((observable, oldValue, newValue) -> this.setModifier(generateModifier(newValue.intValue())));
    }

    private ObservableValue<? extends Number> sum() {
        return base.add(bonus);
    }

    private Integer generateModifier(final Integer total){

        if(total <= 6){
            return -20;
        }

        switch (total){
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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    private void setModifier(int modifier) {
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

    public static CharacteristicBuilder newBuilder() {
        return new CharacteristicBuilder();
    }

    public static CharacteristicBuilder newBuilder(final Characteristic characteristic) {
        return new CharacteristicBuilder()
              .base(characteristic.getBase())
              .bonus(characteristic.getBonus())
              .total(characteristic.getTotal())
              .label(characteristic.getLabel())
              .comment(characteristic.getComment());
    }

    public static final class CharacteristicBuilder {
        private SimpleStringProperty label = new SimpleStringProperty();
        private SimpleIntegerProperty base = new SimpleIntegerProperty();
        private SimpleIntegerProperty bonus = new SimpleIntegerProperty();
        private SimpleIntegerProperty total = new SimpleIntegerProperty();
        private SimpleStringProperty comment = new SimpleStringProperty();

        private CharacteristicBuilder() {
        }

        public CharacteristicBuilder label(final String label) {
            this.label = new SimpleStringProperty(label);
            return this;
        }

        public CharacteristicBuilder base(final int base) {
            this.base = new SimpleIntegerProperty(base);
            return this;
        }

        public CharacteristicBuilder bonus(final int bonus) {
            this.bonus = new SimpleIntegerProperty(bonus);
            return this;
        }

        public CharacteristicBuilder total(final int total) {
            this.total = new SimpleIntegerProperty(total);
            return this;
        }

        public CharacteristicBuilder comment(final String comment) {
            this.comment = new SimpleStringProperty(comment);
            return this;
        }

        public Characteristic build() {
            Characteristic characteristic = new Characteristic();
            characteristic.setLabel(label.get());
            characteristic.setBase(base.get());
            characteristic.setBonus(bonus.get());
            characteristic.setTotal(total.get());
            characteristic.setComment(comment.get());
            characteristic.setModifier(characteristic.generateModifier(total.get()));
            return characteristic;
        }
    }
}
