package ZonaZakat.controller;

import ZonaZakat.data.Sistem;
import ZonaZakat.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MenuZakatMal {

    @FXML
    private TextField inputNama;
    @FXML
    private TextField inputAlamat;
    @FXML
    private TextField inputNominal;

    @FXML
    private ChoiceBox<String> pilihBarang;

    @FXML
    private CheckBox ceklis;

    @FXML
    public void initialize(){
        pilihBarang.getItems().add("Uang");
        pilihBarang.getItems().add("Emas");
    }

    public void tambah(ActionEvent event) throws IOException {
        String nama = inputNama.getText();
        String alamat = inputAlamat.getText();
        String barang = pilihBarang.getValue();
        String nominal = inputNominal.getText();

        String save = "";

        if (nama.equals("") || alamat.equals("") || nominal.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Field tidak boleh kosong!");
            alert.showAndWait();

            inputNama.requestFocus();
            return;
        }

        if (ceklis.isSelected()){
            save = "Anonim" + "|" + alamat + "|" + barang + "|" + nominal;
        } else {
            save = nama + "|" + alamat + "|" + barang + "|" + nominal;
        }

        Sistem.simpanListZM(save);
        Sistem.simpanZM();
        Sistem.simpanZakatMasuk();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setHeaderText("");
        alert.setContentText("Berhasil Menambahkan Data!");
        alert.showAndWait();

        inputNama.setText("");
        inputAlamat.setText("");
        inputNominal.setText("");

        inputNama.requestFocus();
        ceklis.setSelected(false);
    }

    public void kembali(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MainMenu.fxml");
    }

    public void data(ActionEvent event) throws IOException{
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/DataPemberiZM.fxml");
    }


}
