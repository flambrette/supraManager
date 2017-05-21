package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import main.MainApp;
import model.Character;
import utils.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class MainController {

    private MainApp mainApp;

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
    }

    @FXML
    private void handleNewCharacter() {

    }

    @FXML
    private void handleEditCharacter() {

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
                mainApp.setCharacter(characterLoaded);
                this.characteristicsController.refreshCharacteristicTableView();
                this.infosController.refreshFields();
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

            marshaller.marshal(mainApp.getCharacter(), file);

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
        aptitudesController.setMainApp(mainApp);
        characteristicsController.setMainApp(mainApp);
        infosController.setMainApp(mainApp);
    }
}
