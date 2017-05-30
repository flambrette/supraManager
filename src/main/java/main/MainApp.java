package main;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Character;

public class MainApp extends Application {

    private Stage primaryStage;

    public MainApp(){

    }

    @Override
    public void start(Stage pStage) throws Exception{
        Font.loadFont(
              MainApp.class.getResource("/GreatVibes-Regular.otf").toExternalForm(),
              25
        );
        this.primaryStage = pStage;
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();
        createController(loader);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        primaryStage.setTitle("Supra character manager");
        final Scene scene = new Scene(root, 800, 900);
        scene.getStylesheets().addAll(this.getClass().getResource("/view/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void createController(final FXMLLoader loader) {
        final MainController controller = loader.getController();
        controller.setMainApp(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
