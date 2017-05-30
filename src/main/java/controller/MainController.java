package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import main.MainApp;
import model.Character;
import model.Characteristic;
import model.Profession;
import model.Race;
import utils.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainController {

    private MainApp mainApp;

    private Character character = new Character();
    private Map<String, Race> racesMap;
    private Map<String, Profession> professionsMap;

    @FXML
    private AptitudesController aptitudesController;
    @FXML
    private CharacteristicsController characteristicsController;
    @FXML
    private InfosController infosController;

    public MainController(){}

    /**
     * Automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        aptitudesController.setMainController(this);
        characteristicsController.setMainController(this);
        infosController.setMainController(this);
    }

    @FXML
    private void handleNewCharacter() {

    }

    @FXML
    private void handleImportCharacter() {
        final FileChooser fileChooser = new FileChooser();
        final File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(selectedFile != null){
            if(!Utils.isValidString(selectedFile.getPath())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("File Error");
                alert.setHeaderText("Could not load the selected file...");
                alert.showAndWait();
            } else {
                loadCharacterDataFromFile(selectedFile);
            }
        }
    }

    @FXML
    private void handleSaveCharacter(){
        if(infosController.isInputValid()){
            final FileChooser fileChooser = new FileChooser();
            final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            final File selectedFile = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

            if(selectedFile != null) {
                if (!Utils.isValidString(selectedFile.getPath())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("File Error");
                    alert.setHeaderText("Could not find the file");
                    alert.showAndWait();
                } else {
                    saveCharacterDataToFile(selectedFile);
                }
            }
        }
    }


    /**
     * Loads the character data from the specified file.
     *
     * @param file
     */
    private void loadCharacterDataFromFile(final File file) {
        try {
            final JAXBContext context = JAXBContext
                    .newInstance(Character.class);
            final Unmarshaller um = context.createUnmarshaller();
            final Character characterLoaded = (Character) um.unmarshal(file);
            if(characterLoaded != null){
                this.setCharacter(characterLoaded);
                this.infosController.refreshFields();
                this.characteristicsController.refreshCharacteristicTableView();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not load character");
                alert.setContentText("Could not load character from file:\n" + file.getPath()+"\nBe sure to select a valid file");
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load character");
            alert.setContentText("Could not load character from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    /**
     * Saves the current character data to the specified file.
     *
     * @param file
     */
    private void saveCharacterDataToFile(final File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(Character.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(getCharacter(), file);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save character");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    public Map<String, Race> getRacesMap() {
        return racesMap;
    }

    public void setRacesMap(final Map<String, Race> racesMap) {
        this.racesMap = racesMap;
    }

    public Map<String, Profession> getProfessionsMap() {
        return professionsMap;
    }

    public void setProfessionsMap(final Map<String, Profession> professionsMap) {
        this.professionsMap = professionsMap;
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void updateCharacteristicsFromRace(final String raceName) {
        final List<Characteristic> newCharacteristicList = new ArrayList<>();
        final Race race = racesMap.get(raceName);
        character.setRace(race.getName());

        final ObservableList<Characteristic> items = character.getCharacteristics();
        int i = 0;
        for (final Characteristic characteristic : items) {
            final Characteristic characteristicFromRace = race.getCharacteristics().get(i);
            newCharacteristicList.add(
                  Characteristic.newBuilder(characteristic)
                        .base(characteristicFromRace.getBase())
                        .build()
            );
            i++;
        }
        character.setCharacteristics(newCharacteristicList);
        characteristicsController.refreshCharacteristicTableView();
    }
}
