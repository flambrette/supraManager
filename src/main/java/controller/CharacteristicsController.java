package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Characteristic;

/**
 * Created by Florent L.
 */
public class CharacteristicsController {
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

    private MainController mainController;

    /**
     * Automatically called after the fxml file has been loaded.
     */

    public CharacteristicsController(){

    }

    @FXML
    private void initialize() {
        characteristicTableView.setFixedCellSize(35);
        characteristicTableView.setMaxHeight((35*9) + 50);
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

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
        refreshCharacteristicTableView();
    }

    public void refreshCharacteristicTableView() {
        // Add observable list data to the table
        characteristicTableView.setItems(mainController.getCharacter().getCharacteristics());
    }

    public TableView<Characteristic> getCharacteristicTableView() {
        return characteristicTableView;
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
}
