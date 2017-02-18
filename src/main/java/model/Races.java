package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Florent L. on 13-02-17.
 */
@XmlRootElement(name = "races")
public class Races {

    private List<Race> races = new ArrayList<>();

    @XmlElement(name = "race")
    public List<Race> getRaces() {
        return races;
    }

    public List<String> getRaceNames(){
        return races.stream().map(Race::getName).collect(Collectors.toList());
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}
