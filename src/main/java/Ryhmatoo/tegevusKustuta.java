package Ryhmatoo;

//kustutamise meetod

import java.io.FileWriter;
import java.util.*;
import java.io.File;

public class tegevusKustuta {
    //kustuta meetod vajab rahakottide listi, andmebaasi faili ning scannerit
    public static void kustuta(List<Rahakott> rahakotid, File andmebaas, Scanner scan) throws Exception{
        String oigeKood = ""; //väärtustatakse tühi sõne
        boolean eemalda = false;  //väärtustatakse boolean tüüpi muutuja, default väärtus on false
        while (oigeKood.equals("")) {   //niikaua, kuni enne väärtustatud tühi sõne on tühi, programm töötab
            System.out.print("Sisestage rahakoti tunnuskood: ");
            String kood = scan.nextLine();  //palutakse sisestada tunnuskood
            for (Iterator<Rahakott> i = rahakotid.listIterator(); i.hasNext(); ) {  //https://stackoverflow.com/a/17279584, lihtsalt listist eemaldamisega tekkis probleeme..
                Rahakott rahakott = i.next();
                if (Integer.toString(rahakott.getTunnusNumber()).equals(kood)) {    //kui leitakse selline kood..
                    System.out.print("Olete kindel, et soovite rahakoti tunnusnumbriga " + kood + " kustutada? (Y/N) ");  //küsitakse veelkord kinnitust, kas tahetakse kustutada
                    String yesOrNo = scan.nextLine().toUpperCase(); //et poleks vahet, kas sisestatakse "Y" või "y", siis teisendatakse sõna suurtäheks
                    if (yesOrNo.equals("Y")) {  //kui sisestatakse "y", siis "eemalda" saab väärtuseks true ning "oigekood" saab väärtuseks enne sisestatud koodi
                        eemalda = true;
                        oigeKood = kood;
                        break;  //tsükkli töö lõpetatakse
                    }
                    else if (yesOrNo.equals("N")) { //kui valik on "n", siis oigekood väärtustatakse, et tsükkel lõpmatult ei töötaks, tsükkli töö lõpetatakse
                        oigeKood = kood;
                        break;
                    }

                    else {
                        System.out.println("Tundmatu valik, töö lõpetatakse."); //kui sisestatakse midagi muud, väljastatakse, et tundmatu valik ning töö lõpetatakse
                        oigeKood = kood;    //et tsükkel ei töötaks lõpmatuseni, muudetakse oigekoodi väärtus ning tsükli töö lõpetatakse
                        break;
                    }
                }
            }
            if (oigeKood.equals("")) {  //kui listist ei leita sellise koodiga rahakotti, palutakse uuesti proovida
                System.out.println("Sellise tunnuskoodiga rahakotti ei leidu, proovige uuesti");
            }
        }

        if (eemalda) {  //kui eemalda = true, siis otistakse rahakottide seast õige rahakott ja eemaldatakse see
            for (Rahakott rahakott : rahakotid) {
                if (Integer.toString(rahakott.getTunnusNumber()).equals(oigeKood)) {
                    rahakotid.remove(rahakott);
                    break;  //kui õige rahakott leitakse ja eemaldataks, siis lõpetatakse töö
                }
            }
        }
        Collections.sort(rahakotid);    //rahakotid sorditakse uuesti ning ka fail kirjutatakse üle, faili kirjutamiseks meetod "kirjutaja"

        FileWriter kirjutaja = new FileWriter(andmebaas);
        FailiKirjutaja.kirjutaja(rahakotid, kirjutaja);
    }

    public static void kustuta2(List<Rahakott> rahakotid, String valik) {
        for (Rahakott rahakott : rahakotid) {
            if (Integer.toString(rahakott.getTunnusNumber()).equals(valik)) {
                rahakotid.remove(rahakott);
                break;
            }
        }
        Collections.sort(rahakotid);
        FailiKirjutaja.kirjutaja2(rahakotid);
    }
}
