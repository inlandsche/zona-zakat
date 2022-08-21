package ZonaZakat.data;

public class Pemberi {
    private String nama;
    private String alamat;

    private Zakat zakat;

    public Pemberi(String nama, String alamat, Zakat zakat) {
        this.nama = nama;
        this.alamat = alamat;
        this.zakat = zakat;
    }

    public Zakat getZakat() {
        return zakat;
    }

    public void setZakat(Zakat zakat) {
        this.zakat = zakat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


}
