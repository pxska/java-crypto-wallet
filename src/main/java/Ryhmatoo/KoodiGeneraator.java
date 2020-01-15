package Ryhmatoo;

//suvalise koodi genereerimise meetod

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KoodiGeneraator {
    //random klassiga leitakse suvaline arv antud vahemikust ning väljastatakse arv, arvud vastavad ASCII tabelis tähe- ja numbrimärkidele..
    private static int randomSuurT2ht() {
        return 65 + (int) (Math.random() * ((90-65) + 1)) ; //.. 65-90 on suured tähed..
    }

    private static int randomV2ikeT2ht() {
        return 97 + (int) (Math.random() * ((122-97) + 1)); //.. 97-122 on väiksed tähed..
    }

    private static int randomNumber() {
        return 48 + (int) (Math.random() * ((57-48) + 1)); //.. ja 48-57 on numbrid.
    }

    //koodi genereerimise meeotid
    public static String generaator() {

        List<String> elemendid = new ArrayList<>(); //elementide jaoks luuakse uus list

        for (int i = 0; i < 6; i++) {   //ASCII koodis luuakse ja teisendatakse 5 suurt tähte, 5 väikest tähte ja 5 numbrit
            elemendid.add(Character.toString(randomSuurT2ht()));
        }
        for (int i = 6; i < 11; i++) {
            elemendid.add(Character.toString(randomV2ikeT2ht()));
        }

        for (int i = 11; i < 16; i++) {
            elemendid.add(Character.toString(randomNumber()));
        }

        Collections.shuffle(elemendid); //elemendid pannakse suvalisse järjekorda

        StringBuilder sb = new StringBuilder(); //stringbuilderiga tehakse listi elementidest sõne ning tagastatakse see

        for(String el : elemendid) {
            sb.append(el);
        }

        return sb.toString();
    }
}
