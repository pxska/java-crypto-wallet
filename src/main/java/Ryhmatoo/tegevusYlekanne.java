package Ryhmatoo;


import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.sql.Timestamp;

public class tegevusYlekanne {
    public static void kannaYle(List<Rahakott> rahakotid, Scanner scan) throws Exception{
        File logid = new File("log.txt");

        String oigenumber = "";
        Rahakott kandjaRahakott = null;
        String kandjaValuuta = "";
        while (oigenumber.equals("")) {
            System.out.print("Sisestage enda tunnusnumber: ");
            String number = scan.nextLine();
            for (Rahakott rahakott : rahakotid) {
                if (Integer.toString(rahakott.getTunnusNumber()).equals(number)) {
                    kandjaValuuta = rahakott.getValuutaNimi();
                    kandjaRahakott = rahakott;
                    oigenumber = number;
                    break;
                }
            }
        }

        String oigekood = "";
        Rahakott saajaRahakott = null;
        String saajaValuuta = "";
        while (oigekood.equals("")) {
            System.out.print("Sisestage rahakott, kuhu soovite kanda: ");
            String kood = scan.nextLine();
            for (Rahakott rahakott : rahakotid) {
                if (rahakott.getKood().equals(kood)) {
                    saajaValuuta = rahakott.getValuutaNimi();
                    saajaRahakott = rahakott;
                    oigekood = kood;
                    break;
                }
            }
        }

        double summa = 0;
        while (summa == 0) {
            System.out.print("Sisestage summa, mida soovite üle kanda: ");
            summa = Double.parseDouble(scan.nextLine());

            if (kandjaValuuta.equals(saajaValuuta)) {
                if (kandjaRahakott.getValuutaKogus() >= summa) {
                    if (summa < 0) {
                        System.out.println("Kantav summa negatiivne, ülekannet ei teostata");
                        break;
                    }
                    double saajaVanasumma = saajaRahakott.getValuutaKogus();
                    saajaRahakott.setValuutaKogus(saajaVanasumma + summa);
                    double kandjaVanasumma = kandjaRahakott.getValuutaKogus();
                    kandjaRahakott.setValuutaKogus(kandjaVanasumma - summa);

                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);

                    FileWriter kirjutaja = new FileWriter(logid, true);
                    kirjutaja.write(ts + " " + kandjaRahakott.getKood() + " " + kandjaValuuta + " " + kandjaVanasumma + " -" + summa + " " + (kandjaVanasumma - summa) + " " +
                            saajaRahakott.getKood() + " " + saajaValuuta + " " + saajaVanasumma + " +" + summa + " " + (saajaVanasumma + summa) + "\r\n");
                    kirjutaja.flush();
                    kirjutaja.close();

                    break;
                }
                else {
                    System.out.println("Kontol on liiga vähe vahendeid, ülekannet ei teostata");
                    summa = -1;
                    break;
                }
            }
            else {
                if (kandjaRahakott.getValuutaKogus() >= summa) {
                    double kandjaVanasumma = kandjaRahakott.getValuutaKogus();
                    double saajaVanasumma = saajaRahakott.getValuutaKogus();

                    double kantavSummaEur = Valuuta.valuuta2Eur(kandjaValuuta, summa);
                    double kandjaVanaSummaEur = Valuuta.valuuta2Eur(kandjaValuuta, kandjaVanasumma);
                    double saajaKontoSummaEur = Valuuta.valuuta2Eur(saajaValuuta, saajaVanasumma);
                    saajaRahakott.setValuutaKogus(Valuuta.eur2Valuuta(saajaValuuta, kantavSummaEur + saajaKontoSummaEur));
                    kandjaRahakott.setValuutaKogus(Valuuta.eur2Valuuta(kandjaValuuta, kandjaVanaSummaEur - kantavSummaEur));

                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);

                    FileWriter kirjutaja = new FileWriter(logid, true);
                    kirjutaja.write(ts + " " + kandjaRahakott.getKood() + " " + kandjaValuuta + " " + kandjaVanasumma + " -" + summa + " " + (kandjaVanasumma - summa) + " " +
                            saajaRahakott.getKood() + " " + saajaValuuta + " " + saajaVanasumma + " +" + summa + " " + (saajaVanasumma + summa) + "\r");

                    kirjutaja.flush();
                    kirjutaja.close();
                    break;
                }
                else {
                    System.out.println("viga");
                }
            }
        }
    }

    public static void kannayle2(Rahakott kandjaRahakott, Rahakott saajaRahakott, double summa) {
        File logid = new File("logid.txt");

        try (FileWriter kirjutaja = new FileWriter(logid, true)) {
            if (kandjaRahakott.getValuutaNimi().equals(saajaRahakott.getValuutaNimi())) {
                double saajaVanasumma = saajaRahakott.getValuutaKogus();
                saajaRahakott.setValuutaKogus(saajaVanasumma + summa);
                double kandjaVanasumma = kandjaRahakott.getValuutaKogus();
                kandjaRahakott.setValuutaKogus(kandjaVanasumma - summa);

                Date date = new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);

                kandjaRahakott.setValuutaKogus(kandjaVanasumma - summa);
                kirjutaja.write(ts + " " + kandjaRahakott.getKood() + " " + kandjaRahakott.getValuutaNimi() + " " + kandjaVanasumma + " -" + summa + " " + (kandjaVanasumma - summa) + " " +
                        saajaRahakott.getKood() + " " + saajaRahakott.getValuutaNimi() + " " + saajaVanasumma + " +" + summa + " " + (saajaVanasumma + summa) + "\r\n");
                kirjutaja.flush();
            } else {
                double kandjaVanasumma = kandjaRahakott.getValuutaKogus();
                double saajaVanasumma = saajaRahakott.getValuutaKogus();

                double kantavSummaEur = Valuuta.valuuta2Eur(kandjaRahakott.getValuutaNimi(), summa);
                double kandjaVanaSummaEur = Valuuta.valuuta2Eur(kandjaRahakott.getValuutaNimi(), kandjaVanasumma);
                double saajaKontoSummaEur = Valuuta.valuuta2Eur(saajaRahakott.getValuutaNimi(), saajaVanasumma);
                saajaRahakott.setValuutaKogus(Valuuta.eur2Valuuta(saajaRahakott.getValuutaNimi(), kantavSummaEur + saajaKontoSummaEur));
                kandjaRahakott.setValuutaKogus(Valuuta.eur2Valuuta(kandjaRahakott.getValuutaNimi(), kandjaVanaSummaEur - kantavSummaEur));

                Date date = new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);

                kirjutaja.write(ts + " " + kandjaRahakott.getKood() + " " + kandjaRahakott.getValuutaNimi() + " " + kandjaVanasumma + " -" + summa + " " + (kandjaVanasumma - summa) + " " +
                        saajaRahakott.getKood() + " " + saajaRahakott.getValuutaNimi() + " " + saajaVanasumma + " +" + summa + " " + (saajaVanasumma + summa) + "\r\n");

                kirjutaja.flush();
                kirjutaja.close();
            }
        } catch (Exception e) {
            System.out.println("viga");
            System.exit(1);
        }



    }
}
