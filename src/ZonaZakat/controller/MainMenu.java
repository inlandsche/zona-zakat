package ZonaZakat.controller;

import ZonaZakat.main.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainMenu {

    public void zakatFitrah(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MenuZakatFitrah.fxml");
    }

    public void zakatMal(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MenuZakatMal.fxml");
    }

    public void informasi(ActionEvent event) throws IOException{
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/DataPemberi.fxml");
    }
}
