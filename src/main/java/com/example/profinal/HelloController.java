package com.example.profinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class HelloController {
    @FXML
    public TextField textInicio;

    @FXML
    public TextField textFin;
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea textOutput;
    private ArrayList[] grafo;
    private FullDijsktra Dijsk = new FullDijsktra();
    @FXML
    protected void AbrirArchivo() {
        //welcomeText.setText("Welcome to JavaFX Application!");
        boolean testMode = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);

        if(fileChooser != null){
            String FilePath = file.getAbsolutePath();

            try {
                if (testMode){
                    grafo = Dijsk.GrafoTest(true);
                } else {
                    grafo = Dijsk.LeerArchivo(FilePath);
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    public void Ejecutar(){
        Map<String, Integer> Nombre_Numero = Dijsk.getNombre_Numero();
        Map<Integer, String> Numero_Nombre = Dijsk.getNumero_Nombre();

        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Dialog");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("Valide los datos ingresados");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);


        if(textInicio.getText() != "" && textFin.getText() != ""){
            if (Nombre_Numero.containsKey(textInicio.getText()) && Nombre_Numero.containsKey(textFin.getText())){
                Dijsk.Dijsktra(grafo,textInicio.getText());

                Dijsk.reconstruirRuta(textFin.getText(),textInicio.getText());

                textOutput.setText(Dijsk.getResultFinal());

            }else {
                dialog.showAndWait();
            }
        }else {
            dialog.showAndWait();
        }


    }

    @FXML
    public void initialize() throws IOException {


    }
}