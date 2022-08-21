package ZonaZakat.controller;

import ZonaZakat.data.Pemberi;
import ZonaZakat.data.Sistem;
import ZonaZakat.data.ZakatFitrah;
import ZonaZakat.data.ZakatMal;
import ZonaZakat.main.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DataPemberiZM {

    @FXML
    public TableView<Pemberi> tabelPemberiZakat;
    @FXML
    private TableColumn<Pemberi, String> kolomNama;
    @FXML
    private TableColumn<Pemberi, String> kolomAlamat;
    @FXML
    private TableColumn<Pemberi, String> kolomBarang;
    @FXML
    private TableColumn<Pemberi, String> kolomJumlah;

    @FXML
    private Label totalUang;
    @FXML
    private Label totalEmas;

    @FXML
    public void initialize(){
        kolomNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
        kolomAlamat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlamat()));
        kolomBarang.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZakat().getJenisBarang()));
        kolomJumlah.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getZakat().getNominal())));

        tampilkan();
        hitungTotal();
    }

    public void tampilkan(){
        tabelPemberiZakat.getItems().removeAll(Sistem.pemberi);

        for (Pemberi p : Sistem.pemberi){
            if (p.getZakat() instanceof ZakatMal)
                tabelPemberiZakat.getItems().add(p);
        }
    }

    public void kembali(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MenuZakatMal.fxml");
    }

    public void hapus(ActionEvent event) throws IOException{
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

        Sistem.simpanZM();
        Sistem.simpanZakatMasuk();

        hitungTotal();
    }

    public void hitungTotal(){
        double totalU = 0;
        double totalE = 0;

        for (Pemberi p : Sistem.pemberi){
            if (p.getZakat() instanceof ZakatMal && p.getZakat().getJenisBarang().equals("Uang"))
                totalU += p.getZakat().getNominal();
            if (p.getZakat() instanceof ZakatMal && p.getZakat().getJenisBarang().equals("Emas"))
                totalE += p.getZakat().getNominal();
        }

        totalUang.setText(String.valueOf(totalU));
        totalEmas.setText(String.valueOf(totalE));
    }

    public void clicked(MouseEvent mouseEvent) {
    }

    public void edit(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Pemberi p = tabelPemberiZakat.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ZonaZakat/fxmlFiles/EditDataZM.fxml"));
        Parent root = loader.load();

        stage.setTitle("Edit Data");
        stage.setScene(new Scene(root, 400, 400));

        EditDataZM editDataZM = loader.getController();
        editDataZM.setField(p.getNama(), p.getAlamat(), p.getZakat().getJenisBarang(), p.getZakat().getNominal());
        editDataZM.setPemberi(p);

        stage.show();
    }
}
