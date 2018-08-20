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

        //TODO find a better font or fix css visual problems involved by this one
        //Font.loadFont(
         //     MainApp.class.getResource("/GreatVibes-Regular.otf").toExternalForm(),
        //      1
       // );


        this.primaryStage = pStage;
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();
        createController(loader);
        final Scene scene = getScene(root);

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        primaryStage.setTitle("Supra character manager");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private Scene getScene(Parent root) {
        final Scene scene = new Scene(root, 900, 900);
        scene.getStylesheets().addAll(this.getClass().getResource("/view/style.css").toExternalForm());
        return scene;
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
