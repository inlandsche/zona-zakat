package ZonaZakat.data;

public class Zakat<T> {
    private T zakat;
    private String jenisBarang;
    private double nominal;

    public Zakat(String jenisBarang, double nominal) {
        this.jenisBarang = jenisBarang;
        this.nominal = nominal;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public T getZakat(){
        return zakat;
    }
}
