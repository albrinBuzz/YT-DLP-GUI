package com.example.ytfedora;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;


public class YoutubeDl {

    private String url;
    private String ruta;
    private String bash,sep, fileSeparator;
    private Alert alert=null;
    private  Stage primaryStage;
    private String resolucion;
    private  static  final   String YOUTUBE_DL_COMMAND="yt-dlp";

    public YoutubeDl(String url, String ruta, Stage primaryStage) {
        this.url = url;
        this.ruta = ruta;
        this.primaryStage=primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        fileSeparator="/";
        bash="bash";
        sep="-c";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public YoutubeDl() {

    }
    private void descarga(ProgressBar progressBar,String comando){
        Service<Void> downloadService = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        updateProgress(-3, 1);
                        // Indicar que la descarga ha comenzado
                        ProcessBuilder processBuilder = new ProcessBuilder(bash,sep,comando);
                        try {
                            Process process = processBuilder.start();
                            process.waitFor(); // Esperar a que termine la descarga
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                            alert =new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("ERROR!");
                            alert.setHeaderText("ocurio un error");
                            alert.setContentText("a ocurrido un error inseperado");
                            alert.show();
                        }
                        updateProgress(1, 1);
                        // Indicar que la descarga ha terminado
                        return null;
                    }
                };
            }
        };
        progressBar.progressProperty().bind(downloadService.progressProperty());
        progressBar.setVisible(true);
        downloadService.start();
        downloadService.setOnSucceeded(e -> progressBar.setVisible(false));
        downloadService.setOnFailed(e -> progressBar.setVisible(false));
    }
    public void downloadVideo(ProgressBar brProgreso, String reso) {
        resolucion=reso;
        String calidad=" -S ext  -f \"bestvideo[height<="+resolucion+"]+bestaudio/best[height<="+resolucion+"]\" ";
        String comando=YOUTUBE_DL_COMMAND+calidad+ " -o \"" + ruta + fileSeparator +"%(title)s.%(ext)s\" " + url;
        descarga(brProgreso,comando);
    }
    public void downloadAudio(ProgressBar brProgreso) {
        String mp3=" --extract-audio --audio-format mp3";
        String comando=YOUTUBE_DL_COMMAND+mp3+ " -o \"" + ruta + fileSeparator +"%(title)s.%(ext)s\" " + url;
        descarga(brProgreso,comando);
    }

}

