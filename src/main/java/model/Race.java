package model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Florent L. on 13-02-17.
 */
public class Race {

    private String name;
    private List<Characteristic> characteristics;

    public Race() {
    }

    public Race(final String label,final List<Characteristic> characteristics) {
        this.name = label;
        this.characteristics = characteristics;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String label) {
        this.name = label;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    @XmlElement(name="characteristic")
    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
