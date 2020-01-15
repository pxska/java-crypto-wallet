package Ryhmatoo;

//faili kirjutamise meetod

import java.io.FileWriter;
import java.util.List;

public class FailiKirjutaja {
    //meetod kirjutaja vajab rahakottide listi ning kirjutajat
    public static void kirjutaja(List<Rahakott> rahakotid, FileWriter kirjutaja) throws Exception{
        kirjutaja.write("");    //algul kirjutatakse fail üle, ehk sinna kirjutatakse üks tühi sõne

        for (Rahakott rahakott : rahakotid) {   //käiakse läbi kõik listis olevad rahakotid ning kirjutatakse nende kohta info faili, iga rahakoti kohta tehakse uus rida
            kirjutaja.write(rahakott.getTunnusNumber() + " " +
                    rahakott.getOmanikuNimi() + " " +
                    rahakott.getValuutaNimi() + " " +
                    rahakott.getValuutaKogus() + " " +
                    rahakott.getKood() + "\r\n");
            kirjutaja.flush();
        }
    }

    public static void kirjutaja2(List<Rahakott> rahakotid) {
        try (FileWriter kirjutaja = new FileWriter("rahakotid.txt")) {
            for (Rahakott rahakott : rahakotid) {
                kirjutaja.write(rahakott.getTunnusNumber() + " " +
                        rahakott.getOmanikuNimi() + " " +
                        rahakott.getValuutaNimi() + " " +
                        rahakott.getValuutaKogus() + " " +
                        rahakott.getKood() + "\r\n");
                kirjutaja.flush();
            }

        } catch (Exception e) {
            System.out.println("viga");
            System.exit(1);
        }
    }
}
