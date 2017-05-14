package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import model.Character;
import model.Profession;
import model.Professions;
import model.Race;
import model.Races;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Florent L. on 06-02-17.
 */
public class InfosController {

    private Stage dialogStage;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField sizeField;
    @FXML
    private TextField ageField;
    @FXML
    private ComboBox<String> raceComboBox;
    @FXML
    private ComboBox<String> careerComboBox;

    private Map<String, Race> racesMap;
    private Map<String, Profession> professionsMap;

    private MainApp mainApp;

    @FXML
    private void initialize() throws URISyntaxException {
        raceComboBox.getItems().addAll(loadRacesDataFromFile());
        careerComboBox.getItems().addAll(loadProfessionsDataFromFile());
    }

    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (ageField.getText() == null || ageField.getText().length() == 0) {
            errorMessage += "No valid age!\n";
        }
        if (sizeField.getText() == null || sizeField.getText().length() == 0) {
            errorMessage += "No valid size!\n";
        }
        if (raceComboBox.getValue() == null) {
            errorMessage += "No valid race!\n";
        }

        if ( careerComboBox.getValue() == null) {
            errorMessage += "No valid class!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);
        alert.showAndWait();

        return false;
    }

    /**
     * Loads data from the specified file.
     */
    private List<String> loadRacesDataFromFile() throws URISyntaxException {

        final File racesFile = new File(getClass().getResource( "/races.xml" ).getFile());

        if(!racesFile.exists()){
            manageNonexistingFile("races",racesFile);
        }

        try {
            final JAXBContext context = JAXBContext
                    .newInstance(Races.class);
            final Unmarshaller um = context.createUnmarshaller();
            @SuppressWarnings("unchecked")
            final Races races  = (Races) um.unmarshal(racesFile);

            racesMap = races.getRaces().stream()
                  .collect(Collectors.toMap(Race::getName, Function.identity()));

            return races.getRaceNames();

        } catch (Exception e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load races");
            alert.setContentText(e.toString());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    private List<String> loadProfessionsDataFromFile() throws URISyntaxException {

        final File professionsFile = new File(getClass().getResource( "/professions.xml" ).getFile());

        if(!professionsFile.exists()){
            manageNonexistingFile("professions",professionsFile);
        }

        try {
            final JAXBContext context = JAXBContext
                    .newInstance(Professions.class);
            final Unmarshaller um = context.createUnmarshaller();
            @SuppressWarnings("unchecked")
            final Professions professions  = (Professions) um.unmarshal(professionsFile);

            professionsMap = professions.getProfessions().stream()
                  .collect(Collectors.toMap(Profession::getName, Function.identity()));

            return professions.getProfessionNames();

        } catch (Exception e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load professions");
            alert.setContentText(e.toString());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    private  List<String> manageNonexistingFile(final String name,final File file) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not find " +name);
        alert.setContentText("Could not find "+name+" from file:\n" + file.getPath());
        alert.showAndWait();
        return Collections.emptyList();
    }

    public void refreshFields() {
        firstNameField.setText(mainApp.getCharacter().getFirstName());
        lastNameField.setText(mainApp.getCharacter().getLastName());
        sizeField.setText(mainApp.getCharacter().getSize());
        ageField.setText(mainApp.getCharacter().getAge());
        raceComboBox.setValue(mainApp.getCharacter().getRace());
        careerComboBox.setValue(mainApp.getCharacter().getCareer());
    }

    public void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;
    }
}