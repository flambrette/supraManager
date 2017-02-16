package model;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florent L. on 13-02-17.
 */
public class Race {

    private String label;
    private Characteristics characteristics;

    public Race() {
    }

    public Race(String label, Characteristics characteristics) {
        this.label = label;
        this.characteristics = characteristics;
    }

    public String getLabel() {
        return label;
    }

    @XmlElement
    public void setLabel(String label) {
        this.label = label;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    @XmlElement(name="characteristics")
    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }
}
