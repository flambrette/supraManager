package main;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Character;

public class MainApp extends Application {

    private Stage primaryStage;
    private Character character = new Character();

    public MainApp(){

    }

    @Override
    public void start(Stage pStage) throws Exception{
        this.primaryStage = pStage;
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();

        createController(loader);

        primaryStage.setTitle("Supra character manager");
        primaryStage.setScene(new Scene(root, 800, 900));
        primaryStage.show();
    }

    private void createController(final FXMLLoader loader) {
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
