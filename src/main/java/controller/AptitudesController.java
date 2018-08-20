package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import main.MainApp;
import model.Aptitude;

/**
 * Created by Florent L.
 */
public class AptitudesController {

    @FXML
    private TableView<Aptitude> aptitudeTableView;
    @FXML
    private TableColumn<Aptitude, String> labelColumn;
    @FXML
    private TableColumn<Aptitude, Integer> raceColumn;
    @FXML
    private TableColumn<Aptitude, Integer> cpColumn;
    @FXML
    private TableColumn<Aptitude, Integer> genreColumn;
    @FXML
    private TableColumn<Aptitude, Integer> editColumn;
    @FXML
    private TableColumn<Aptitude, Integer> expColumn;
    @FXML
    private TableColumn<Aptitude, String> cColumn;
    @FXML
    private TableColumn<Aptitude, String> commentColumn;
    @FXML
    private TableColumn<Aptitude, Integer> totalColumn;

    private MainController mainController;

    public AptitudesController() {
    }

    @FXML
    private void initialize() {
        labelColumn.setCellValueFactory(cellData -> cellData.getValue().labelProperty());

        raceColumn.setCellValueFactory(cellData -> cellData.getValue().raceProperty().asObject());
        raceColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        raceColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setRace(t.getNewValue());
                    aptitudeTableView.refresh();
                }
        );

        cpColumn.setCellValueFactory(cellData -> cellData.getValue().cpProperty().asObject());
        cpColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        cpColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCp(t.getNewValue());
                    aptitudeTableView.refresh();
                }
        );

        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty().asObject());
        genreColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        genreColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setGenre(t.getNewValue());
                    aptitudeTableView.refresh();
                }
        );

        editColumn.setCellValueFactory(cellData -> cellData.getValue().editProperty().asObject());
        editColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        editColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setEdit(t.getNewValue());
                    aptitudeTableView.refresh();
                }
        );

        expColumn.setCellValueFactory(cellData -> cellData.getValue().expProperty().asObject());
        expColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        expColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setExp(t.getNewValue());
                    aptitudeTableView.refresh();
                }
        );

        totalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());
        totalColumn.setCellFactory(TextFieldTableCell.forTableColumn(generateStringConvert()));
        totalColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTotal(t.getNewValue());
                    aptitudeTableView.refresh();
                }
        );

        cColumn.setCellValueFactory(cellData -> cellData.getValue().cProperty());
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().cProperty());
    }

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
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
