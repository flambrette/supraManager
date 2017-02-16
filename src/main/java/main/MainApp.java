package main;

import controller.EditCharacterDialogController;
import controller.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Characteristic;
import model.Character;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private Character character = new Character();
    private ObservableList<Characteristic> characteristicList = FXCollections.observableArrayList();

    public MainApp(){
        characteristicList = Characteristic.generateDefaultCharacteristicList();
    }

    @Override
    public void start(Stage pStage) throws Exception{
        this.primaryStage = pStage;

        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();

        createCharacteristicController(loader);

        primaryStage.setTitle("Supra character manager");
        primaryStage.setScene(new Scene(root, 800, 900));
        primaryStage.show();
    }

    private void createCharacteristicController(final FXMLLoader loader) {
        // Give the controller access to the main app.
        final MainController controller = loader.getController();
        controller.setMainApp(this);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Characteristic> getCharacteristicList() {
        return characteristicList;
    }

    public boolean showCharacterEditDialog(final Character character) {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/editCharacterDialog.fxml"));
            final AnchorPane page = loader.load();

            final Stage dialogStage = new Stage();
            dialogStage.setTitle("New character");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            final EditCharacterDialogController controller = loader.getController();
            controller.setCharacter(character);
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
