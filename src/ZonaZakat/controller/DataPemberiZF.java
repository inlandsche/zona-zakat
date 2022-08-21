package ZonaZakat.controller;

import ZonaZakat.data.Pemberi;
import ZonaZakat.data.Sistem;
import ZonaZakat.data.ZakatFitrah;
import ZonaZakat.main.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DataPemberiZF {
    @FXML
    private Label totalBeras;

    @FXML
    private TableView<Pemberi> tabelPemberiZakat;
    @FXML
    private TableColumn<Pemberi, String> kolomNama;
    @FXML
    private TableColumn<Pemberi, String> kolomAlamat;
    @FXML
    private TableColumn<Pemberi, String> kolomBarang;
    @FXML
    private TableColumn<Pemberi, String> kolomJumlah;

    @FXML
    public void initialize(){
        kolomNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
        kolomAlamat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlamat()));
        kolomBarang.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZakat().getJenisBarang()));
        kolomJumlah.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getZakat().getNominal()) + " kg"));

        tampilkan();
        hitungTotalBeras();
    }

    public void tampilkan(){
        for (Pemberi p : Sistem.pemberi){
            if (p.getZakat() instanceof ZakatFitrah)
                tabelPemberiZakat.getItems().add(p);
        }
    }

    public void kembali(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MenuZakatFitrah.fxml");
    }

    public void hitungTotalBeras(){
        double total = 0;

        for (Pemberi p : Sistem.pemberi){
            if (p.getZakat() instanceof ZakatFitrah)
                total += p.getZakat().getNominal();
        }

        totalBeras.setText(String.valueOf(total));
    }

    public void edit(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Pemberi p = tabelPemberiZakat.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ZonaZakat/fxmlFiles/EditDataZF.fxml"));
        Parent root = loader.load();

        stage.setTitle("Edit Data");
        stage.setScene(new Scene(root, 400, 400));

        EditDataZF editDataZF = loader.getController();
        editDataZF.setField(p.getNama(), p.getAlamat());
        editDataZF.setPemberi(p);

        stage.show();
    }

    public void hapus(ActionEvent event) throws IOException {
        Pemberi deleted = tabelPemberiZakat.getSelectionModel().getSelectedItem();

        if (deleted != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin untuk menghapus?");

            var action = alert.showAndWait();

            if (action.get() == ButtonType.OK){
                for (Pemberi p : Sistem.pemberi){
                    if (deleted.hashCode() == p.hashCode()){
                        Sistem.pemberi.remove(p);
                        tabelPemberiZakat.getItems().remove(p);
                        break;
                    }
                }
            }
        }


        Sistem.simpanZF();
        Sistem.simpanZakatMasuk();

        hitungTotalBeras();
    }
}
