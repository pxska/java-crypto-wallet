package Ryhmatoo;

//lisamise meetod

import java.io.FileWriter;
import java.util.*;
import java.io.File;

public class tegevusLisa {
    //lisa meetod vajab andmebaasi, rahakottide listi ning scannerit
    public static void lisa(File andmebaas, List<Rahakott> rahakotid, Scanner scan) throws Exception{
        FileWriter kirjutaja = new FileWriter(andmebaas); //kõigepealt kirjutatakse faili juba olemasolevad rahakotid
        FailiKirjutaja.kirjutaja(rahakotid, kirjutaja);

        System.out.print("Sisestage tunnusnumber: ");   //palutakse sisestada tunnusnumber, omaniku nimi, valuuta nimi ning valuuta kogus
        String tunnusNumber = scan.nextLine();

        System.out.print("Sisestage omaniku nimi: ");
        String omanikuNimi = scan.nextLine();

        String oigeValuutaNimi = "";    //väärtustatakse muutuja

        while (oigeValuutaNimi.equals("")) {    //niikaua, kui muutuja on tühi, tsükkel töötab
            System.out.print("Sisestage valuuta nimi: ");
            String valuutaNimi = scan.nextLine().toUpperCase(); //et kontrollimisel ei tekiks segadust, tehakse sisestatud sõne suurtähtedeks
            for (String el : Valuuta.lubatudValuutad) { //kontrollitakse, kas sisestatud valuuta on lubatud valuutade nimekirjas
                if (el.equals(valuutaNimi)) {
                    oigeValuutaNimi = valuutaNimi;  //kui on, väärtustatakse oigevaluuta ümber ning tsükkli töö lõpetatakse
                    break;
                }
            }
            if (oigeValuutaNimi.equals("")) {   //kui ei, siis palutakse uuesti proovida
                System.out.println("Sellist valuutat ei toetata, proovige uuesti");
            }
        }

        System.out.print("Sisestage valuuta kogus: ");
        String valuutaKogus = scan.nextLine();

        String kood = KoodiGeneraator.generaator(); //genereeritakse uuele rahakotile kood, kasutatakse meetodit "generaator"

        //lisatud rahakott kirjutatakse faili ning lisatakse listi, seejärel list sorditakse
        kirjutaja.write(tunnusNumber + " " + omanikuNimi.substring(0, 1).toUpperCase() + omanikuNimi.substring(1) + " " + oigeValuutaNimi.toUpperCase() + " " + valuutaKogus + " " + kood);
        rahakotid.add(new Rahakott(Integer.parseInt(tunnusNumber), omanikuNimi, oigeValuutaNimi, Double.parseDouble(valuutaKogus), kood));
        Collections.sort(rahakotid);
        kirjutaja.flush();
        kirjutaja.close();

    }

    public static void lisa2(List<Rahakott> rahakotid, String tunnusNumber, String omanikuNimi, String oigeValuutaNimi, String valuutaKogus) {
        try (FileWriter kirjutaja = new FileWriter("rahakotid.txt")) {
            FailiKirjutaja.kirjutaja(rahakotid, kirjutaja);
            String kood = KoodiGeneraator.generaator(); //genereeritakse uuele rahakotile kood, kasutatakse meetodit "generaator"

            //lisatud rahakott kirjutatakse faili ning lisatakse listi, seejärel list sorditakse
            kirjutaja.write(tunnusNumber + " " + omanikuNimi.substring(0, 1).toUpperCase() + omanikuNimi.substring(1) + " " + oigeValuutaNimi.toUpperCase() + " " + valuutaKogus + " " + kood);
            rahakotid.add(new Rahakott(Integer.parseInt(tunnusNumber), omanikuNimi, oigeValuutaNimi, Double.parseDouble(valuutaKogus), kood));
            Collections.sort(rahakotid);
            kirjutaja.flush();
        } catch (Exception e) {
            System.out.println("error");
            System.exit(1);
        }
    }
}
