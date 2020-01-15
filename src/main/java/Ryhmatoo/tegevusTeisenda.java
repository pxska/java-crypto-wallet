package Ryhmatoo;

//teisendamise meetod

import java.util.*;

public class tegevusTeisenda {
    //teisneda meetod vajab rahakottide listi ning scannerit
    public static void teisenda(List<Rahakott> rahakotid, Scanner scan) {
        //väärtustatakse tühi sõne
        String teisendus = "";
        //niikaua, kui pole sobivat tunnusnumbrit, jätkatakse tööd
        while (teisendus.equals("")) {
            System.out.print("Sisestage enda tunnusnumber: ");
            String kood = scan.nextLine();  //küsitakse koodi ning otsitakse, kas sellise koodiga rahakotti leidub andmebaasis
            for (Rahakott rahakott : rahakotid) {
                if (Integer.toString(rahakott.getTunnusNumber()).equals(kood)) {    //kui leidub, siis tehakse teisendus, teisenduse tegemine toimub klassis "Valuuta"

                    teisendus = rahakott.getValuutaKogus() + " " + rahakott.getValuutaNimi() + " = " + Valuuta.valuuta2Eur(rahakott.getValuutaNimi(), rahakott.getValuutaKogus()) + " EUR";
                    //teisendus = "1";
                    break;  //kui teisendus tehtud, katkestatakse tsükkel
                }

            }
            if (teisendus.equals("")) { //kui sellist rahakotti ei leitud, siis palutakse uuesti proovida
                System.out.println("Sellise tunnusnumbriga rahakotti ei leidu, proovige uuesti");
            }
        }
        System.out.println(teisendus);  //kui tsükkel lõpetatakse, väljastatakse saanud teisendus
    }
}
