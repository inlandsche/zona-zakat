package ZonaZakat.controller;

import ZonaZakat.data.Pemberi;
import ZonaZakat.data.Sistem;
import ZonaZakat.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MenuZakatFitrah {

    @FXML
    private TextField inputNama;
    @FXML
    private TextField inputAlamat;
    @FXML
    private TextField fieldBarang;
    @FXML
    private TextField fieldBerat;

    @FXML
    public void initialize(){
        fieldBarang.setText("Beras");
        fieldBerat.setText("3.0");

        inputNama.requestFocus();
    }

    public void tambah(ActionEvent event) throws IOException {
        String nama = inputNama.getText();
        String alamat = inputAlamat.getText();
        String barang = fieldBarang.getText();
        String berat = fieldBerat.getText();

        if (nama.equals("") || alamat.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Field tidak boleh kosong!");
            alert.showAndWait();

            inputNama.requestFocus();
            return;
        }

        String save = nama + "|" + alamat + "|" + barang + "|" + berat;

        Sistem.simpanListZF(save);
        Sistem.simpanZF();
        Sistem.simpanZakatMasuk();

        Sistem.show();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setHeaderText("");
        alert.setContentText("Berhasil Menambahkan Data!");
        alert.showAndWait();

        inputNama.setText("");
        inputAlamat.setText("");
        inputNama.requestFocus();
        
    }


    public void kembali(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MainMenu.fxml");
    }

    public void data(ActionEvent event) throws IOException{
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/DataPemberiZF.fxml");
    }
}
