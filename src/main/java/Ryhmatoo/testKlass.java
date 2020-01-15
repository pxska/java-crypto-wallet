package Ryhmatoo;

//Testklass

import java.io.FileWriter;
import java.util.*;
import java.io.File;

public class testKlass {
    public static void main(String[] args) throws Exception {
        //luuakse rahakottide jaoks list, ning tehakse uus Fail
        List<Rahakott> rahakotid = new ArrayList<>();
        File andmebaas = new File("rahakotid.txt");
        Scanner scan = new Scanner(System.in);

        //kutsutakse välja meetod failistlist, mis koostab andmebaasist listi
        failistListi.failistList(andmebaas, rahakotid);

        //programm töötab nii kaua, kuniks sisestatakse "peata"
        /*while (true) {
            //küsitakse valikut
            System.out.print("Lisa / näita / kustuta / peata / teisenda / ülekanne: ");
            //et programm saaks aru, kui kirjutatakse "peata" või "PeaTa", siis teisendatakse saadud sõne väikesteks tähtedeks
            String s = scan.nextLine().toLowerCase();

            //niikaua, kui muutuja s ei ole peata, siis jätkatakse tööd
            if (!s.equals("peata")) {
                if (s.equals("näita")) {
                    tegevusNäita.näita(rahakotid);  //"näita" korral kutsutakse välja meetod, mis väljastab kõik andmebaasis olevad rahakotid
                } else if (s.equals("lisa")) {
                    tegevusLisa.lisa(andmebaas, rahakotid, scan);   //"lisa" korral kutsutakse välja meetod, mis lisab andmebaasi ja listi uue rahakoti
                } else if (s.equals("kustuta")) {
                    tegevusKustuta.kustuta(rahakotid, andmebaas, scan); //kustuta korral kutsutakse välja meetod, mis kustutab andmebaasist ja listist rahakoti
                } else if (s.equals("teisenda")) {
                    tegevusTeisenda.teisenda(rahakotid, scan); //teisenda korral kutsutakse välja meetod, mis teisendab rahakotis oleva valuuta väärtuse eurodesse
                } else if (s.equals("ülekanne")) {
                    tegevusYlekanne.kannaYle(rahakotid, scan);
                }

            }
            else {
                System.out.println("Programm suletakse");   //kui kirjutatakse "peata", siis väljastatakse, et programm suletakse ning töö lõpetatakse
                FailiKirjutaja.kirjutaja(rahakotid, new FileWriter(andmebaas));
                System.exit(0);
                break;
            }
        }*/
    }
}

