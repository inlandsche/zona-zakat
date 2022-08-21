package ZonaZakat.controller;

import ZonaZakat.data.Pemberi;
import ZonaZakat.data.Sistem;
import ZonaZakat.data.ZakatFitrah;
import ZonaZakat.main.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class DataPemberi {

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
    private Label totalBeras;
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
    }

    public void tampilkan(){
        tabelPemberiZakat.getItems().addAll(Sistem.pemberi);
        perhitungan();
    }

    public void kembali(ActionEvent event) throws IOException {
        Main main = new Main();

        main.changeScene("/ZonaZakat/fxmlFiles/MainMenu.fxml");
    }

    public void perhitungan(){
        double totalU = 0, totalB = 0, totalE = 0;

        for (Pemberi p : Sistem.pemberi){
            if (p.getZakat().getJenisBarang().equals("Beras")){
                totalB += p.getZakat().getNominal();
            } else if (p.getZakat().getJenisBarang().equals("Uang")){
                totalU += p.getZakat().getNominal();
            } else if (p.getZakat().getJenisBarang().equals("Emas")){
                totalE += p.getZakat().getNominal();
            }
        }

        totalBeras.setText(String.valueOf(totalB));
        totalUang.setText(String.valueOf(totalU));
        totalEmas.setText(String.valueOf(totalE));
    }
}
