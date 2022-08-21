package ZonaZakat.controller;

import ZonaZakat.data.Pemberi;
import ZonaZakat.data.Sistem;
import ZonaZakat.data.ZakatFitrah;
import ZonaZakat.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditDataZF {

    @FXML
    private TextField fieldNama;
    @FXML
    private TextField fieldAlamat;
    @FXML
    private TextField fieldBerat;
    @FXML
    private TextField fieldBarang;

    Pemberi p;

    @FXML
    public void initialize(){
        fieldBerat.setText("3.0");
        fieldBarang.setText("Beras");
    }


    public void perbarui(ActionEvent event) throws IOException {

        if (p != null) {
            String nama = fieldNama.getText();
            String alamat = fieldAlamat.getText();
            String barang = fieldBarang.getText();
            double berat = Double.parseDouble(fieldBerat.getText());

            for (Pemberi pemberi : Sistem.pemberi) {
                if (pemberi.hashCode() == p.hashCode()) {
                    p.setNama(nama);
                    p.setAlamat(alamat);
                    p.setZakat(new ZakatFitrah(barang, berat));
                    break;
                }
            }


            Sistem.simpanZF();
            Sistem.simpanZakatMasuk();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Berhasil");
            alert.setHeaderText("");
            alert.setContentText("Data berhasil diperbarui!");
            alert.showAndWait();

            close();
        }
    }

    public void setField(String nama, String alamat){
        fieldNama.setText(nama);
        fieldAlamat.setText(alamat);
    }

    public void setPemberi(Pemberi pemberi){
        p = pemberi;
    }

    public void close() throws IOException{
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/DataPemberiZF.fxml");
    }
}
