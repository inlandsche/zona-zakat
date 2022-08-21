package ZonaZakat.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Sistem {

    //public static ArrayList<Pemberi> pemberi = new ArrayList<>();
    public static LinkedList<Pemberi> pemberi = new LinkedList<>();

    public static void inisialisasi() throws IOException{
        File dataPemberi = new File("src/ZonaZakat/database/dataZakatMasuk.txt");
        File dataZF = new File("src/ZonaZakat/database/dataZakatFitrah.txt");
        File dataZM = new File("src/ZonaZakat/database/dataZakatMal.txt");

        if (!dataPemberi.exists()) dataPemberi.createNewFile();
        if (!dataZF.exists()) dataZF.createNewFile();
        if (!dataZM.exists()) dataZM.createNewFile();

        Scanner in = new Scanner(dataZF);
        while (in.hasNextLine()){
            simpanListZF(in.nextLine());
        }

        Scanner ins = new Scanner(dataZM);
        while (ins.hasNextLine()){
            simpanListZM(ins.nextLine());
        }
    }

    public static void show(){

        for (Pemberi p : pemberi) {
            System.out.println("Nama\t: " + p.getNama());
            System.out.println("Zakat\t: " + p.getZakat().getClass().getSimpleName());
            System.out.println("Index\t: " + pemberi.indexOf(p));
        }
    }

    public static void simpanListZF(String data){
        StringTokenizer st = new StringTokenizer(data, "|");

        String nama = st.nextToken();
        String alamat = st.nextToken();
        String barang = st.nextToken();
        String banyak = st.nextToken();

        Zakat<ZakatFitrah> zakatFitrah = new ZakatFitrah(barang, Double.parseDouble(banyak));
        pemberi.addLast(new Pemberi(nama, alamat, zakatFitrah));
    }

    public static void simpanListZM(String data){
        StringTokenizer st = new StringTokenizer(data, "|");

        String nama = st.nextToken();
        String alamat = st.nextToken();
        String barang = st.nextToken();
        String banyak = st.nextToken();

        Zakat<ZakatMal> zakatMal = new ZakatMal(barang, Double.parseDouble(banyak));
        pemberi.addLast(new Pemberi(nama, alamat, zakatMal));
    }

    public static void simpanZF() throws IOException{
        File dataZF = new File("src/ZonaZakat/database/dataZakatFitrah.txt");
        FileWriter writer = new FileWriter(dataZF);

        for (Pemberi p : pemberi){
            if (p.getZakat() instanceof ZakatFitrah){
                String save = p.getNama() + "|" + p.getAlamat() + "|" + p.getZakat().getJenisBarang()
                        + "|" + p.getZakat().getNominal();

                writer.write(save + "\n");
            }
        }

        writer.close();
    }

    public static void simpanZM() throws IOException{
        File dataZM = new File("src/ZonaZakat/database/dataZakatMal.txt");
        FileWriter writer = new FileWriter(dataZM);

        for (Pemberi p : pemberi){
            if (p.getZakat() instanceof ZakatMal){
                String save = p.getNama() + "|" + p.getAlamat() + "|" + p.getZakat().getJenisBarang()
                        + "|" + p.getZakat().getNominal();

                writer.write(save + "\n");
            }
        }

        writer.close();
    }

    public static void simpanZakatMasuk() throws IOException {
        File data = new File("src/ZonaZakat/database/dataZakatMasuk.txt");
        FileWriter writer = new FileWriter(data);

        for (Pemberi p : pemberi){
            String save = p.getNama() + "|" + p.getAlamat() + "|" + p.getZakat().getJenisBarang()
                    + "|" + p.getZakat().getNominal();

            writer.write(save + "\n");
        }

        writer.close();
    }
}
