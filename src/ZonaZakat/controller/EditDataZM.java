package ZonaZakat.controller;

import ZonaZakat.controller.DataPemberiZM;
import ZonaZakat.data.Pemberi;
import ZonaZakat.data.Sistem;
import ZonaZakat.data.ZakatMal;
import ZonaZakat.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditDataZM {

    @FXML
    private TextField fieldNama;
    @FXML
    private TextField fieldAlamat;
    @FXML
    private TextField fieldNominal;
    @FXML
    private ChoiceBox<String> pilihBarang;

    Pemberi p;

    @FXML
    public void initialize(){
        pilihBarang.getItems().add("Uang");
        pilihBarang.getItems().add("Emas");
    }

    public void perbarui(ActionEvent event) throws IOException {

        if (p != null) {
            String nama = fieldNama.getText();
            String alamat = fieldAlamat.getText();
            String barang = pilihBarang.getValue();
            double nominal = Double.parseDouble(fieldNominal.getText());

            for (Pemberi pemberi : Sistem.pemberi) {
                if (pemberi.hashCode() == p.hashCode()) {
                    p.setNama(nama);
                    p.setAlamat(alamat);
                    p.setZakat(new ZakatMal(barang, nominal));
                    break;
                }
            }

            Sistem.simpanZM();
            Sistem.simpanZakatMasuk();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Berhasil");
            alert.setHeaderText("");
            alert.setContentText("Data berhasil diperbarui!");
            alert.showAndWait();

            close();
        }
    }

    public void setField(String nama, String alamat, String barang, double nominal){
        fieldNama.setText(nama);
        fieldAlamat.setText(alamat);
        fieldNominal.setText(String.valueOf(nominal));
        pilihBarang.setValue(barang);
    }

    public void setPemberi(Pemberi pemberi){
        p = pemberi;
    }

    public void close() throws IOException{
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/DataPemberiZM.fxml");
    }
}
