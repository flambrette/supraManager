package model;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florent L. on 14-02-17.
 */
public class Characteristics {
    private List<Characteristic> characteristics = new ArrayList<>();

    public Characteristics() {
    }

    @XmlElement(name = "characteristic")
    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setRaces(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
