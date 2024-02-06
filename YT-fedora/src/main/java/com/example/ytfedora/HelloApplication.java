package com.example.ytfedora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("YT-GUI");
        stage.setScene(scene);
        centerStage(stage);
        stage.show();

        stage.centerOnScreen();

    }
    private void centerStage(Stage stage) {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        double centerX = (screenWidth - stageWidth) / 2;
        double centerY = (screenHeight - stageHeight) / 2;
        stage.setX(centerX);
        stage.setY(centerY);
    }
    public static void main(String[] args) {
        launch();
    }
}
