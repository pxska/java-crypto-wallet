package Ryhmatoo;

//failist listi tegemise meetod

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class failistListi {
    //meetod failistList vajab andmebaasi faili ja rahakottide lista
    public static void failistList(File andmebaas, List<Rahakott> rahakotid) throws Exception{
        if (andmebaas.exists()) {   //kontrollitakse, kas andmebaas on olemas
            try (Scanner sc = new Scanner(andmebaas)) {
                while (sc.hasNextLine()) {  //kui on, käiakse kõik failiread läbi, loetakse see eraldi sõneks, tükeldatakse see ning lisatakse nendest uus rahakott
                    String rida = sc.nextLine();
                    String[] tykid = rida.split(" ");
                    rahakotid.add(new Rahakott(Integer.parseInt(tykid[0]),
                            tykid[1].substring(0, 1).toUpperCase() + tykid[1].substring(1),
                            tykid[2].toUpperCase(), Double.parseDouble(tykid[3]),
                            tykid[4]));
                }
            }

            Collections.sort(rahakotid);    //rahakotid sorditakse
        } else {    //kui andmebaasi ei ole olemas, väljastatakse, et seda ei eksisteeri ning töö lõpetatakse
            System.out.println("Andmebaasi ei eksisteeri.");
            System.exit(0);
        }

    }
}
