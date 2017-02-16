package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Florent L. on 01-02-17.
 */
public class Aptitude {
    private final SimpleStringProperty label= new SimpleStringProperty();
    private final SimpleIntegerProperty race = new SimpleIntegerProperty();
    private final SimpleIntegerProperty cpKey = new SimpleIntegerProperty();
    private final SimpleIntegerProperty genre = new SimpleIntegerProperty();
    private final SimpleIntegerProperty exp = new SimpleIntegerProperty();
    private final SimpleIntegerProperty c = new SimpleIntegerProperty();
    private final SimpleIntegerProperty total = new SimpleIntegerProperty();
    private final SimpleStringProperty comment = new SimpleStringProperty();

    public Aptitude(){}

    public Aptitude(final String labelParam){
        label.set(labelParam);
        race.set(0);
        cpKey.set(0);
        genre.set(0);
        exp.set(0);
        c.set(0);
        total.set(0);
        comment.set("");
    }

    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public int getRace() {
        return race.get();
    }

    public SimpleIntegerProperty raceProperty() {
        return race;
    }

    public void setRace(int race) {
        this.race.set(race);
    }

    public int getCpKey() {
        return cpKey.get();
    }

    public SimpleIntegerProperty cpKeyProperty() {
        return cpKey;
    }

    public void setCpKey(int cpKey) {
        this.cpKey.set(cpKey);
    }

    public int getGenre() {
        return genre.get();
    }

    public SimpleIntegerProperty genreProperty() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre.set(genre);
    }

    public int getExp() {
        return exp.get();
    }

    public SimpleIntegerProperty expProperty() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp.set(exp);
    }

    public int getC() {
        return c.get();
    }

    public SimpleIntegerProperty cProperty() {
        return c;
    }

    public void setC(int c) {
        this.c.set(c);
    }

    public int getTotal() {
        return total.get();
    }

    public SimpleIntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        this.total.set(total);
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
