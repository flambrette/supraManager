package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Florent L. on 01-02-17.
 */
public class Aptitude {
    private final SimpleStringProperty label= new SimpleStringProperty();
    private final SimpleIntegerProperty race = new SimpleIntegerProperty();
    private final SimpleIntegerProperty cp = new SimpleIntegerProperty();
    private final SimpleIntegerProperty genre = new SimpleIntegerProperty();
    private final SimpleIntegerProperty edit = new SimpleIntegerProperty();
    private final SimpleIntegerProperty exp = new SimpleIntegerProperty();
    private final SimpleStringProperty c = new SimpleStringProperty();
    private final SimpleIntegerProperty total = new SimpleIntegerProperty();
    private final SimpleStringProperty comment = new SimpleStringProperty();

    public Aptitude() {
    }

    public Aptitude(final String labelParam, final int idParam){
        label.set(labelParam);
        race.set(0);
        cp.set(0);
        genre.set(0);
        edit.set(0);
        exp.set(0);
        c.set(LearningLevel.N.getValue());
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

    public int getCp() {
        return cp.get();
    }

    public SimpleIntegerProperty cpProperty() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp.set(cp);
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

    public int getEdit() {
        return edit.get();
    }

    public SimpleIntegerProperty editProperty() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit.set(edit);
    }

    public String getC() {
        return c.get();
    }

    public SimpleStringProperty cProperty() {
        return c;
    }

    public void setC(String c) {
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
