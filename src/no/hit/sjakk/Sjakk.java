/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hit.sjakk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.hit.sjakk.brikker.Brikke;
import no.hit.sjakk.brikker.Farge;

/*  Sjakk program som bare kan spilles med tekst input fra bruker.
*   Man må skrive inn både fra rute og til rute så trykke på flytt knappen.
*   Spillet har kun grunnleggende regler og mangler et par av de mest avanserte,
*   og spillet vet ikke når kongen har blitt slått ut.
 */
public class Sjakk extends Application {

    private final BorderPane root = new BorderPane();
    private final Text toppText = new Text("Start nytt spill");
    private Farge farge = Farge.BLACK;
    private Brett brett;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        root.setTop(toppText);
        root.setCenter(grid(new Brikke[8][8]));
        root.setBottom(bunnPane());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sjakk");
        stage.show();
    }

    private FlowPane bunnPane() {
        Text fraText = new Text("Fra rute:");
        TextField fraField = new TextField();
        fraField.setMaxWidth(40);

        Text tilText = new Text("Til rute:");
        TextField tilField = new TextField();
        tilField.setMaxWidth(40);

        Button flytt = new Button("Flytt");

        flytt.setOnMouseClicked((event) -> {
            String fra = fraField.getText();
            String til = tilField.getText();
            Brikke valgt = brett.getBrikke(fra);
            //Sjekker om valgt brikke er null, om begge er lovlige rutenavn,
            //om det er korrekt farge sin tur, og om brikken kunne flyttes(og flytter den hvis mulig)
            if (valgt != null
                    && Brett.erLovligRutenavn(til)
                    && Brett.erLovligRutenavn(fra)
                    && valgt.getFarge() != farge
                    && brett.flyttBrikke(fra, til)) {

                //fjerner det gamle brettet og lager et nytt i midten av borderPane
                root.setCenter(null);
                root.setCenter(grid(brett.getBrikkene()));
                toppText.setText("Skriv neste trekk");
                farge = valgt.getFarge();
            } else {
                toppText.setText("Ugyldig trekk!");
            }
        });
        //ikke brukt
        Button lagre = new Button("Lagre");

        Button nyttSpill = new Button("Nytt Spill");

        //starter nytt spill og resetter fargen til black slik at det er hvit som begynner
        nyttSpill.setOnMouseClicked((event) -> {
            brett = new Brett(1);
            farge = Farge.BLACK;
            root.setCenter(null);
            root.setCenter(grid(brett.getBrikkene()));

        });

        FlowPane bunn = new FlowPane(fraText, fraField, tilText, tilField, flytt, lagre, nyttSpill);
        bunn.setHgap(15);
        return bunn;
    }

    //Lager gridpane ut ifra en Brikke array
    private GridPane grid(Brikke[][] arr) {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                Brikke tempBrikke = arr[i][j];
                StackPane tempPane = new StackPane();

                if (tempBrikke != null) {
                    Text tempText = new Text(tempBrikke.getBrikkenavn());

                    //hvis brikken har hvit farge blir teksten hvit
                    if (tempBrikke.getFarge() == Farge.WHITE) {
                        tempText.setStyle("-fx-fill:white;-fx-font-size:40");
                        tempText.setStroke(Color.BLACK);
                    } else {
                        tempText.setStyle("-fx-font-size:40;");
                    }

                    tempPane.getChildren().add(tempText);
                }

                //hvis både i og j er enten partall eller oddetall blir fargen lys
                if (i % 2 == 0 && j % 2 == 0 || i % 2 != 0 && j % 2 != 0) {
                    tempPane.setStyle("-fx-background-color:gainsboro;");
                } else {
                    tempPane.setStyle("-fx-background-color:grey;");
                }

                tempPane.setMinHeight(80);
                tempPane.setMinWidth(80);

                gridPane.add(tempPane, j, Brett.BRETTSTORRELSE - i);
            }
        }
        return gridPane;
    }

}
