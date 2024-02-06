package com.example.ytfedora;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField txtUrl;
    public Button btnDescargar;
    public ChoiceBox<String> cboResulcion;
    public ProgressBar brProgreso;
    public Button btnCambiar;
    public Label url;
    public TextField txtRuta;
    public TextField url2;
    public Button btnDescargar2;

    private String[]resoluciones={"2160","1440","1080","720","480","360","240","144"};
    private  YoutubeDl yt;


    public void descargar(ActionEvent actionEvent) {
        setear();
        String reso=cboResulcion.getValue();
        yt.setUrl(txtUrl.getText());
        yt.downloadVideo(brProgreso,reso);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brProgreso.setVisible(false);
        String directorioPersonal = System.getProperty("user.home");
        txtRuta.setText(directorioPersonal);
        txtRuta.setEditable(false);
        txtUrl.setPromptText("URL");
        url2.setPromptText("URL");
        btnDescargar.disableProperty().bind(txtUrl.textProperty().isEmpty());
        yt=new YoutubeDl();
        cboResulcion.setValue(resoluciones[2]);
        cboResulcion.getItems().addAll(resoluciones);
        btnDescargar2.setOnAction(this::descargarMP3);

    }

    public void eligirDirectorio(ActionEvent actionEvent) {
        DirectoryChooser chooser=new DirectoryChooser();
        chooser.setTitle("Seleccione Ruta");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage =(Stage) url.getScene().getWindow();
        File dFile=chooser.showDialog(stage);
        if (dFile!=null){
            txtRuta.setText(dFile.getAbsolutePath());
        }
    }

    public void descargarMP3(ActionEvent actionEvent) {

        System.out.println("#");
        setear();
        yt.setUrl(url2.getText());
        yt.downloadAudio(brProgreso);


    }

    private void setear(){
        Stage stage=(Stage) url.getScene().getWindow();
        yt.setRuta(txtRuta.getText());
        yt.setPrimaryStage(stage);
    }

    public static class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }


    }
}