package Ryhmatoo;

//näitamise meetod

import java.util.List;

public class tegevusNäita {
    //näitamise meetod vajab rahakottide listi
    public static void näita(List<Rahakott> rahakotid) {
        for (Rahakott rahakott : rahakotid) {   //käiakse läbi kõik rahakotid ning väljastatakse iga rahakoti puhul toString meetod
            System.out.println(rahakott.toString());
        }
    }
}
