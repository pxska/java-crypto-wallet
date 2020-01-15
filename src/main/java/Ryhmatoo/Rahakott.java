package Ryhmatoo;

//klass rahakottide loomiseks, kasutab "Comparable" liidest

public class Rahakott implements Comparable<Rahakott>{
    //5 privaatset isendivälja
    private int tunnusNumber;
    private String omanikuNimi;
    private String valuutaNimi;
    private double valuutaKogus;
    private String kood;

    //getterid
    public int getTunnusNumber() { return tunnusNumber; }

    public double getValuutaKogus() { return valuutaKogus; }

    public String getOmanikuNimi() { return omanikuNimi; }

    public String getValuutaNimi() { return valuutaNimi; }

    public String getKood() { return kood; }

    public void setValuutaKogus(double valuutaKogus) {
        this.valuutaKogus = valuutaKogus;
    }

    //konstruktor, väärtustatakse uus rahakott
    public Rahakott(int tunnusNumber, String omanikuNimi, String valuutaNimi, double valuutaKogus, String kood) {
        this.tunnusNumber = tunnusNumber;
        this.omanikuNimi = omanikuNimi;
        this.valuutaNimi = valuutaNimi;
        this.valuutaKogus = valuutaKogus;
        this.kood = kood;
    }

    //"Comparable" kasutus selleks, et rahakottide väljastamisel järjestada rahakotid tunnusnumbri järgi

    public int compareTo(Rahakott võrreldav) {
        if (tunnusNumber < võrreldav.tunnusNumber) {
            return -1;
        }
        if (tunnusNumber > võrreldav.tunnusNumber) {
            return 1;
        }
        return 0;
    }

    //toString meetod
    @Override
    public String toString() {
        return "Tunnusnumber: " + tunnusNumber + ", Omanik: " + omanikuNimi + ", Valuuta: " + valuutaNimi + ", Kogus: " + valuutaKogus + ", Rahakoti kood: " + kood;
    }
}
