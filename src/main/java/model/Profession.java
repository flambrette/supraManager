package model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Florent L. on 16-02-17.
 */
public class Profession {
    private String name;
    private List<Aptitude> aptitudeList;

    @XmlElement(name="name")
    public String getName() {
        return name;
    }

        @XmlElement(name="aptitude")
    public List<Aptitude> getAptitudeList() {
        return aptitudeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAptitudeList(List<Aptitude> aptitudeList) {
        this.aptitudeList = aptitudeList;
    }
}
