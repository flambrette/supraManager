package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Florent L. on 16-02-17.
 */
@XmlRootElement(name = "professions")
public class Professions {

    private List<Profession> professionList = new ArrayList<>();

    @XmlElement(name = "profession")
    public List<Profession> getProfessions() {
        return professionList;
    }

    public List<String> getProfessionNames(){
        return professionList.stream().map(Profession::getName).collect(Collectors.toList());
    }

    public void setProfessionList(final List<Profession> professionList) {
        this.professionList = professionList;
    }
}
