package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import main.MainApp;
import model.Characteristic;
import model.Character;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class MainController {
    @FXML
    private TableView<Characteristic> characteristicTableView;
    @FXML
    private TableColumn<Characteristic, String> labelColumn;
    @FXML
    private TableColumn<Characteristic, Integer> baseColumn;
    @FXML
    private TableColumn<Characteristic, Integer> bonusColumn;
    @FXML
    private TableColumn<Characteristic, Integer> totalColumn;
    @FXML
    private TableColumn<Characteristic, Integer> modifierColumn;
    @FXML
    private TableColumn<Characteristic, String> commentColumn;

    private MainApp mainApp;

    private Character character;

    public MainController(){}

    /**
     * Automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        labelColumn.setCellValueFactory(cellData -> cellData.getValue().labelProperty());

        baseColumn.setCellValueFactory(cellData -> cellData.getValue().baseProperty().asObject());
        baseColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        baseColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setBase(t.getNewValue());
                    characteristicTableView.refresh();
                }
        );

        bonusColumn.setCellValueFactory(cellData -> cellData.getValue().bonusProperty().asObject());
        bonusColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        bonusColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setBonus(t.getNewValue());
                    characteristicTableView.refresh();
                }
        );

        totalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        modifierColumn.setCellValueFactory(cellData -> cellData.getValue().modifierProperty().asObject());

        commentColumn.setCellValueFactory(cellData -> cellData.getValue().commentProperty());
        commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        commentColumn.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setComment(t.getNewValue())
        );
    }

    public void setMainApp(final MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        characteristicTableView.setItems(mainApp.getCharacteristicList());
    }

    @FXML
    private void handleEditCharacter() {
        character = new Character();
        boolean okClicked = mainApp.showCharacterEditDialog(character);
        if (okClicked) {
            mainApp.setCharacter(character);
        }
    }

    @FXML
    private void handleImportCharacter() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(selectedFile != null){
            loadCharacterDataFromFile(selectedFile);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("File Error");
            alert.setHeaderText("Could not load the selected file...");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSaveCharacter(final File file){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(selectedFile != null){
            saveCharacterDataToFile(selectedFile);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("File Error");
            alert.setHeaderText("Could not find the file");
            alert.showAndWait();
        }
    }


    /**
     * Loads the character data from the specified file.
     *
     * @param file
     */
    private void loadCharacterDataFromFile(final File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(Character.class);
            Unmarshaller um = context.createUnmarshaller();
            character = (Character) um.unmarshal(file);

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

            marshaller.marshal(character, file);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save character");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    private StringConverter<Integer> generateStringConvert() {
        return new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.valueOf(string);
            }
        };
    }

    public void setMainApplicationApp(MainApp mainApplication) {

    }
}
