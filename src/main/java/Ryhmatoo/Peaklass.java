package Ryhmatoo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Peaklass extends Application {

    @Override
    public void start(Stage pealava) throws Exception {

        List<Rahakott> rahakotid = new ArrayList<>();

        File andmebaas = new File("rahakotid.txt");

        if (!andmebaas.exists()) {
            Stage viga = new Stage();
            viga.setTitle("Viga");

            BorderPane veateade = new BorderPane();
            HBox viga_okbutton = new HBox();
            viga_okbutton.setAlignment(Pos.CENTER);

            ImageView veapilt = new ImageView(new Image(new FileInputStream("viga_pilt.png")));
            veapilt.setFitHeight(50);
            veapilt.setFitWidth(50);

            VBox vbox_veapilt = new VBox(veapilt);
            vbox_veapilt.setAlignment(Pos.CENTER);
            vbox_veapilt.setPadding(new Insets(0, 0, 0, 20));

            veateade.setLeft(vbox_veapilt);
            viga_okbutton.setPadding(new Insets(0, 0, 25, 0));
            Text veatekst = new Text("Andmebaasi ei eksisteeri!");
            Button okbutton = new Button("OK");
            okbutton.setOnAction(event -> viga.hide());
            viga_okbutton.getChildren().add(okbutton);
            veateade.setCenter(veatekst);
            veateade.setBottom(viga_okbutton);

            Scene veastseen = new Scene(veateade, 250, 125);
            viga.setScene(veastseen);
            viga.show();

        } else {
            failistListi.failistList(andmebaas, rahakotid);

            Stage vahelava = new Stage();
            vahelava.setTitle("Sisestamine");


            /********************
             * PEAMENÜÜ
             ********************/
            BorderPane alus = new BorderPane();
            alus.setPadding(new Insets(0, 0, 20, 0));


            HBox xnupp = new HBox();    //hbox, kus on x nupp
            xnupp.setPadding(new Insets(5, 5, 60, 0));
            xnupp.setAlignment(Pos.CENTER_RIGHT);
            Button nupp_x = new Button("X");

            xnupp.getChildren().add(nupp_x);
            alus.setTop(xnupp);

            VBox nupud = new VBox(); //vbox, kus nupud peal on
            nupud.setPadding(new Insets(0, 60, 0, 60));
            nupud.setSpacing(20);

            /********************
             * NUPP NÄITA
             ********************/
            Button n2ita = new Button("NÄITA");
            n2ita.setMaxWidth(Double.MAX_VALUE);
            n2ita.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            n2ita.setPrefHeight(100);
            nupud.getChildren().add(n2ita);

            //vajutades nupule näita
            BorderPane nuppn2ita = new BorderPane();
            nuppn2ita.setPadding(new Insets(0, 8, 0, 8));

            HBox xnuppN2ita = new HBox();
            xnuppN2ita.setPadding(new Insets(5, -3, 60, 0));
            xnuppN2ita.setAlignment(Pos.CENTER_RIGHT);
            Button n2itanupp_x = new Button("X");

            xnuppN2ita.getChildren().add(n2itanupp_x);
            nuppn2ita.setTop(xnuppN2ita);

            HBox tagasiNuppNäita = new HBox();
            tagasiNuppNäita.setPadding(new Insets(0, 0, 75, 0));
            tagasiNuppNäita.setAlignment(Pos.CENTER_LEFT);
            Button tagasinupp_näita = new Button("<- Tagasi");
            tagasinupp_näita.setMinSize(75, 50);
            tagasiNuppNäita.getChildren().add(tagasinupp_näita);
            nuppn2ita.setBottom(tagasiNuppNäita);


            TableView rahakotid_tabelina = new TableView();
            TableColumn<String, Rahakott> tunnusnumber = new TableColumn<>("Tunnusnumber");
            tunnusnumber.setCellValueFactory(new PropertyValueFactory<>("tunnusNumber"));

            TableColumn<String, Rahakott> nimi = new TableColumn<>("Omaniku nimi");
            nimi.setCellValueFactory(new PropertyValueFactory<>("omanikuNimi"));

            TableColumn<String, Rahakott> valuuta = new TableColumn<>("Valuuta");
            valuuta.setCellValueFactory(new PropertyValueFactory<>("valuutaNimi"));

            TableColumn<String, Rahakott> kogus = new TableColumn<>("Raha arvel");
            kogus.setCellValueFactory(new PropertyValueFactory<>("valuutaKogus"));

            TableColumn<String, Rahakott> kood = new TableColumn<>("Rahakoti kood");
            kood.setCellValueFactory(new PropertyValueFactory<>("kood"));

            rahakotid_tabelina.getColumns().addAll(Arrays.asList(tunnusnumber, nimi, valuuta, kogus, kood));

            for (Rahakott rahakott : rahakotid) {
                rahakotid_tabelina.getItems().add(rahakott);
            }


            VBox tabel = new VBox(rahakotid_tabelina);
            nuppn2ita.setCenter(tabel);

            /********************
             * NUPP LISA
             ********************/
            Button lisa = new Button("LISA");
            lisa.setMaxWidth(Double.MAX_VALUE);
            lisa.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            lisa.setPrefHeight(100);
            nupud.getChildren().add(lisa);

            FailiKirjutaja.kirjutaja2(rahakotid);

            BorderPane nupplisa = new BorderPane();
            nupplisa.setPadding(new Insets(0, 8, 0, 8));

            HBox xnuppLisa = new HBox();
            xnuppLisa.setPadding(new Insets(5, -3, 60, 0));
            xnuppLisa.setAlignment(Pos.CENTER_RIGHT);
            Button lisanupp_x = new Button("X");

            xnuppLisa.getChildren().add(lisanupp_x);
            nupplisa.setTop(xnuppLisa);

            VBox tekstiv2ljad_lisamine = new VBox();
            tekstiv2ljad_lisamine.setAlignment(Pos.CENTER);
            tekstiv2ljad_lisamine.setSpacing(20.0);

            HBox tunnusnumbri_sisestamine = new HBox();
            tunnusnumbri_sisestamine.setAlignment(Pos.CENTER);
            tunnusnumbri_sisestamine.setSpacing(10);
            Text abistav_tekst_1 = new Text("Sisestage uue konto tunnusnumber");
            TextField tekstiv2li_tunnusnumber = new TextField();

            tekstiv2li_tunnusnumber.setAlignment(Pos.CENTER);
            tekstiv2li_tunnusnumber.setPrefWidth(60);
            tekstiv2li_tunnusnumber.setFont(Font.font("Arial", 20));
            tunnusnumbri_sisestamine.getChildren().addAll(abistav_tekst_1, tekstiv2li_tunnusnumber);


            HBox omaniku_sisestamine = new HBox();
            omaniku_sisestamine.setAlignment(Pos.CENTER);
            omaniku_sisestamine.setSpacing(10);
            Text abistav_tekst_2 = new Text("Sisestage rahakoti omanik");
            TextField tekstiv2li_omanik = new TextField();
            tekstiv2li_omanik.setAlignment(Pos.CENTER);
            tekstiv2li_omanik.setFont(Font.font("Arial"));
            omaniku_sisestamine.getChildren().addAll(abistav_tekst_2, tekstiv2li_omanik);

            HBox valuuta_sisestamine = new HBox();
            valuuta_sisestamine.setAlignment(Pos.CENTER);
            valuuta_sisestamine.setSpacing(10);
            Text abistav_tekst_3 = new Text("Valige valuuta");
            ComboBox valuutavalik = new ComboBox();

            for (String el : Valuuta.lubatudValuutad) {
                valuutavalik.getItems().add(el);
            }

            valuuta_sisestamine.getChildren().addAll(abistav_tekst_3, valuutavalik);

            HBox valuutakoguse_sisestamine = new HBox();
            valuutakoguse_sisestamine.setAlignment(Pos.CENTER);
            valuutakoguse_sisestamine.setSpacing(10);
            Text abistav_tekst_4 = new Text("Sisestage valuuta kogus rahakotis");
            TextField tekstiv2li_valuutakogus = new TextField();
            tekstiv2li_valuutakogus.setAlignment(Pos.CENTER);
            tekstiv2li_valuutakogus.setFont(Font.font("Arial"));
            valuutakoguse_sisestamine.getChildren().addAll(abistav_tekst_4, tekstiv2li_valuutakogus);

            tekstiv2ljad_lisamine.getChildren().addAll(tunnusnumbri_sisestamine, omaniku_sisestamine, valuuta_sisestamine, valuutakoguse_sisestamine);
            nupplisa.setCenter(tekstiv2ljad_lisamine);

            HBox tagasiNuppLisa = new HBox();
            tagasiNuppLisa.setPadding(new Insets(0, 0, 75, 0));
            tagasiNuppLisa.setAlignment(Pos.CENTER_LEFT);
            Button tagasinupp_lisa = new Button("<- Tagasi");
            tagasinupp_lisa.setMinSize(75, 50);
            tagasiNuppLisa.getChildren().add(tagasinupp_lisa);
            nupplisa.setBottom(tagasiNuppLisa);

            HBox kontrolliNuppLisa = new HBox();
            kontrolliNuppLisa.setPadding(new Insets(50, 0, 75, 0));
            kontrolliNuppLisa.setAlignment(Pos.CENTER);
            Button kontrollinupp_lisa = new Button("Kontrolli");
            kontrollinupp_lisa.setMinSize(75, 50);
            kontrolliNuppLisa.getChildren().add(kontrollinupp_lisa);
            tekstiv2ljad_lisamine.getChildren().add(kontrolliNuppLisa);


            kontrollinupp_lisa.setOnAction(event -> {
                tunnusnumbri_sisestamine.getChildren().clear();
                tunnusnumbri_sisestamine.getChildren().addAll(abistav_tekst_1, tekstiv2li_tunnusnumber);
                String sisestatud_tunnusnumber = tekstiv2li_tunnusnumber.getText();

                omaniku_sisestamine.getChildren().clear();
                omaniku_sisestamine.getChildren().addAll(abistav_tekst_2, tekstiv2li_omanik);
                String sisestatud_omanik = tekstiv2li_omanik.getText();

                valuuta_sisestamine.getChildren().clear();
                valuuta_sisestamine.getChildren().addAll(abistav_tekst_3, valuutavalik);
                String sisestatud_valuuta = (String) valuutavalik.getValue();

                valuutakoguse_sisestamine.getChildren().clear();
                valuutakoguse_sisestamine.getChildren().addAll(abistav_tekst_4, tekstiv2li_valuutakogus);
                String sisestatud_kogus = tekstiv2li_valuutakogus.getText();

                try {
                    FileInputStream fis = new FileInputStream("viga_pilt.png");
                    FileInputStream fis2 = new FileInputStream("ok_m2rk.png");
                    Image viga_pilt_image = new Image(fis);
                    Image ok_pilt_image = new Image(fis2);

                    ImageView viga_pilt1 = new ImageView(viga_pilt_image);
                    viga_pilt1.setFitHeight(25);
                    viga_pilt1.setFitWidth(25);
                    ImageView viga_pilt2 = new ImageView(viga_pilt_image);
                    viga_pilt2.setFitHeight(25);
                    viga_pilt2.setFitWidth(25);
                    ImageView viga_pilt3 = new ImageView(viga_pilt_image);
                    viga_pilt3.setFitHeight(25);
                    viga_pilt3.setFitWidth(25);
                    ImageView viga_pilt4 = new ImageView(viga_pilt_image);
                    viga_pilt4.setFitHeight(25);
                    viga_pilt4.setFitWidth(25);

                    ImageView ok_pilt1 = new ImageView(ok_pilt_image);
                    ok_pilt1.setFitWidth(25);
                    ok_pilt1.setFitHeight(25);
                    ImageView ok_pilt2 = new ImageView(ok_pilt_image);
                    ok_pilt2.setFitWidth(25);
                    ok_pilt2.setFitHeight(25);
                    ImageView ok_pilt3 = new ImageView(ok_pilt_image);
                    ok_pilt3.setFitWidth(25);
                    ok_pilt3.setFitHeight(25);
                    ImageView ok_pilt4 = new ImageView(ok_pilt_image);
                    ok_pilt4.setFitWidth(25);
                    ok_pilt4.setFitHeight(25);

                    int i = 0;
                    while (true) {
                        if (i == rahakotid.size()) {
                            tunnusnumbri_sisestamine.getChildren().add(ok_pilt1);
                            break;
                        }

                        else if (rahakotid.get(i).getTunnusNumber() == Integer.parseInt(sisestatud_tunnusnumber)) {
                            tunnusnumbri_sisestamine.getChildren().add(viga_pilt1);
                            break;
                        }
                        i++;

                    }

                    if (sisestatud_omanik.equals("")) omaniku_sisestamine.getChildren().add(viga_pilt4);
                    else omaniku_sisestamine.getChildren().add(ok_pilt4);

                    if (!sisestatud_valuuta.equals("")) valuuta_sisestamine.getChildren().add(ok_pilt3);
                    else valuuta_sisestamine.getChildren().add(viga_pilt3);

                    if (Double.parseDouble(sisestatud_kogus) < 0) valuutakoguse_sisestamine.getChildren().add(viga_pilt2);
                    else valuutakoguse_sisestamine.getChildren().add(ok_pilt2);

                    if (tunnusnumbri_sisestamine.getChildren().contains(ok_pilt1) && omaniku_sisestamine.getChildren().contains(ok_pilt4) &&
                            valuuta_sisestamine.getChildren().contains(ok_pilt3) && valuutakoguse_sisestamine.getChildren().contains(ok_pilt2)) {
                        tegevusLisa.lisa2(rahakotid, sisestatud_tunnusnumber, sisestatud_omanik, sisestatud_valuuta, sisestatud_kogus);

                        Stage sisestatud_koikok = new Stage();
                        sisestatud_koikok.setTitle("Sisestatud");

                        BorderPane sisestatud = new BorderPane();
                        HBox sisestatud_okbutton = new HBox();
                        sisestatud_okbutton.setAlignment(Pos.CENTER);

                        sisestatud_okbutton.setPadding(new Insets(0, 0, 25, 0));
                        Text sisestatud_korras = new Text("Uus rahakott sisestatud!");
                        Button ok_nupp = new Button("OK");
                        ok_nupp.setOnAction(event2 -> sisestatud_koikok.hide());
                        sisestatud_okbutton.getChildren().add(ok_nupp);
                        sisestatud.setCenter(sisestatud_korras);
                        sisestatud.setBottom(sisestatud_okbutton);

                        Scene veastseen = new Scene(sisestatud, 250, 125);
                        sisestatud_koikok.setScene(veastseen);
                        sisestatud_koikok.show();
                    }

                } catch (Exception e) {
                    Stage viga = new Stage();
                    viga.setTitle("Viga");

                    BorderPane veateade = new BorderPane();
                    HBox viga_okbutton = new HBox();
                    viga_okbutton.setAlignment(Pos.CENTER);

                    try (FileInputStream fis = new FileInputStream("viga_pilt.png")) {
                        ImageView veapilt = new ImageView(new Image(fis));
                        veapilt.setFitHeight(50);
                        veapilt.setFitWidth(50);

                        VBox vbox_veapilt = new VBox(veapilt);
                        vbox_veapilt.setAlignment(Pos.CENTER);
                        vbox_veapilt.setPadding(new Insets(0, 0, 0, 20));

                        veateade.setLeft(vbox_veapilt);
                        viga_okbutton.setPadding(new Insets(0, 0, 25, 0));
                        Text veatekst = new Text("Sisestusviga");
                        Button ok_nupp = new Button("OK");
                        ok_nupp.setOnAction(event2 -> viga.hide());
                        viga_okbutton.getChildren().add(ok_nupp);
                        veateade.setCenter(veatekst);
                        veateade.setBottom(viga_okbutton);

                        Scene veastseen = new Scene(veateade, 250, 125);
                        viga.setScene(veastseen);
                        viga.show();
                    } catch (Exception e2) {
                        System.out.println("fatal error");
                        System.exit(1);
                    }
                }
            });


            /***
             * NUPP KUSTUTA
             */

            Button kustuta = new Button("KUSTUTA");
            kustuta.setMaxWidth(Double.MAX_VALUE);
            kustuta.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            kustuta.setPrefHeight(100);
            nupud.getChildren().add(kustuta);


            Stage kustutamine = new Stage();
            kustutamine.setTitle("Kustuta");

            BorderPane nuppKustuta = new BorderPane();

            HBox abitekst_hbox = new HBox();
            abitekst_hbox.setAlignment(Pos.CENTER);
            abitekst_hbox.setPadding(new Insets(10, 0,0,0));
            Text abitekst = new Text("Sisestage oma tunnusnumber");
            abitekst_hbox.getChildren().add(abitekst);

            HBox tekstiv2li_hbox = new HBox();
            tekstiv2li_hbox.setAlignment(Pos.CENTER);
            tekstiv2li_hbox.setPadding(new Insets(10, 0, 0, 0));
            TextField tekstiv2li_kustuta_tunnusnumber = new TextField();
            tekstiv2li_kustuta_tunnusnumber.setFont(Font.font("Arial", 20));
            tekstiv2li_kustuta_tunnusnumber.setMaxWidth(60);
            tekstiv2li_kustuta_tunnusnumber.setAlignment(Pos.CENTER);
            tekstiv2li_hbox.getChildren().add(tekstiv2li_kustuta_tunnusnumber);

            HBox okbutton_hbox = new HBox();
            okbutton_hbox.setAlignment(Pos.CENTER);
            okbutton_hbox.setPadding(new Insets(0, 0,15,0));
            Button nuppKustuta_okbutton1 = new Button("OK");
            okbutton_hbox.getChildren().add(nuppKustuta_okbutton1);

            nuppKustuta_okbutton1.setOnAction(event -> {
                try {
                    String kustuta_sisestatud_tunnusnr = tekstiv2li_kustuta_tunnusnumber.getText();
                    int i = 0;
                    for (Rahakott rahakott : rahakotid) {
                        if (rahakott.getTunnusNumber() == Integer.parseInt(kustuta_sisestatud_tunnusnr)) {
                            Stage kinnitusaken = new Stage();
                            kinnitusaken.setTitle("Kindel?");
                            BorderPane kinnitus = new BorderPane();
                            HBox kinnitus_nupud = new HBox();
                            kinnitus_nupud.setAlignment(Pos.CENTER);
                            kinnitus_nupud.setPadding(new Insets(0, 0, 25, 0));
                            kinnitus_nupud.setSpacing(10.0);
                            Text veatekst = new Text("Oled sa kindel?");
                            Button jah_nupp = new Button("Jah");
                            Button ei_nupp = new Button("Välju");

                            jah_nupp.setOnAction(event2 -> {
                                tegevusKustuta.kustuta2(rahakotid, kustuta_sisestatud_tunnusnr);
                                kinnitusaken.hide();
                                kustutamine.hide();
                                Stage teavitus = new Stage();
                                teavitus.setTitle("Teavitus");
                                BorderPane teavitusaken = new BorderPane();
                                HBox teavitus_ok_hbox = new HBox();
                                teavitus_ok_hbox.setAlignment(Pos.CENTER);
                                teavitus_ok_hbox.setPadding(new Insets(0,0,10,0));
                                Text teavitustekst = new Text("Kustutatud!");
                                Button teavitus_ok = new Button("OK");
                                teavitus_ok_hbox.getChildren().add(teavitus_ok);
                                teavitusaken.setCenter(teavitustekst);
                                teavitusaken.setBottom(teavitus_ok_hbox);
                                teavitus_ok.setOnAction(event3 -> teavitus.hide());
                                Scene stseenTeavitus = new Scene(teavitusaken, 250, 125);
                                teavitus.setScene(stseenTeavitus);
                                teavitus.show();
                                rahakotid_tabelina.getItems().clear();
                                for (Rahakott rahakott2 : rahakotid) {
                                    rahakotid_tabelina.getItems().add(rahakott2);
                                }
                            });

                            ei_nupp.setOnAction(event2 -> {
                                kinnitusaken.hide();
                                kustutamine.hide();
                            });

                            kinnitus_nupud.getChildren().addAll(jah_nupp, ei_nupp);
                            kinnitus.setCenter(veatekst);
                            kinnitus.setBottom(kinnitus_nupud);

                            Scene stseenKinnitus = new Scene(kinnitus, 250, 125);
                            kinnitusaken.setScene(stseenKinnitus);
                            kinnitusaken.show();

                        } else i++;
                    }

                    if (i == rahakotid.size()) {
                        throw new Exception();
                    }

                } catch (Exception e) {
                    Stage viga = new Stage();
                    viga.setTitle("Viga");

                    BorderPane veateade = new BorderPane();
                    HBox viga_okbutton = new HBox();
                    viga_okbutton.setAlignment(Pos.CENTER);

                    try (FileInputStream fis = new FileInputStream("viga_pilt.png")) {
                        ImageView veapilt = new ImageView(new Image(fis));
                        veapilt.setFitHeight(50);
                        veapilt.setFitWidth(50);

                        VBox vbox_veapilt = new VBox(veapilt);
                        vbox_veapilt.setAlignment(Pos.CENTER);
                        vbox_veapilt.setPadding(new Insets(0, 0, 0, 20));

                        veateade.setLeft(vbox_veapilt);
                        viga_okbutton.setPadding(new Insets(0, 0, 25, 0));
                        Text veatekst = new Text("Sisestusviga");
                        Button ok_nupp = new Button("OK");
                        ok_nupp.setOnAction(event2 -> viga.hide());
                        viga_okbutton.getChildren().add(ok_nupp);
                        veateade.setCenter(veatekst);
                        veateade.setBottom(viga_okbutton);

                        Scene veastseen = new Scene(veateade, 250, 125);
                        viga.setScene(veastseen);
                        viga.show();
                    } catch (Exception e2) {
                        System.out.println("fatal error");
                        System.exit(1);
                    }
                }

            });

            nuppKustuta.setTop(abitekst_hbox);
            nuppKustuta.setCenter(tekstiv2li_hbox);
            nuppKustuta.setBottom(okbutton_hbox);
            Scene stseenKustuta = new Scene(nuppKustuta, 250, 150);
            kustutamine.setScene(stseenKustuta);

            kustuta.setOnAction(event -> kustutamine.show());

            /********************
             * NUPP TEISENDA
             ********************/

            Button teisenda = new Button("TEISENDA");
            teisenda.setMaxWidth(Double.MAX_VALUE);
            teisenda.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            teisenda.setPrefHeight(100);
            nupud.getChildren().add(teisenda);

            BorderPane nuppTeisenda = new BorderPane();
            nuppTeisenda.setPadding(new Insets(0, 8, 0, 8));

            HBox xnuppTeisenda = new HBox();
            xnuppTeisenda.setPadding(new Insets(5, -3, 60, 0));
            xnuppTeisenda.setAlignment(Pos.CENTER_RIGHT);
            Button teisendanupp_X = new Button("X");
            xnuppTeisenda.getChildren().add(teisendanupp_X);
            nuppTeisenda.setTop(xnuppTeisenda);

            BorderPane sisestamine = new BorderPane();
            HBox juhistekst = new HBox();
            juhistekst.setAlignment(Pos.CENTER);
            juhistekst.setPadding(new Insets(10, 0, 0, 0));

            HBox oknupp = new HBox();
            oknupp.setAlignment(Pos.CENTER);
            oknupp.setPadding(new Insets(0, 0, 20, 0));
            TextField tunnusnumber_sisestus = new TextField();
            tunnusnumber_sisestus.setAlignment(Pos.CENTER);
            tunnusnumber_sisestus.setPrefWidth(50);
            tunnusnumber_sisestus.setMaxWidth(50);
            Text juhis = new Text("Sisestage enda tunnusnumber");
            juhistekst.getChildren().add(juhis);
            sisestamine.setTop(juhistekst);
            sisestamine.setCenter(tunnusnumber_sisestus);
            Button okbutton = new Button("OK");
            Button eisoovi = new Button("Ei soovi");
            oknupp.setSpacing(15.0);
            oknupp.getChildren().add(okbutton);
            oknupp.getChildren().add(eisoovi);
            sisestamine.setBottom(oknupp);

            VBox tekstiv2ljad = new VBox();
            tekstiv2ljad.setSpacing(20);
            HBox ylemine_v2li = new HBox();
            HBox teisendanupp = new HBox();
            teisendanupp.setAlignment(Pos.CENTER);

            ylemine_v2li.setSpacing(5.0);
            ylemine_v2li.setAlignment(Pos.CENTER);
            ComboBox valik = new ComboBox();

            for (String el : Valuuta.lubatudValuutad) {
                valik.getItems().add(el);
            }

            TextField omanikuraha = new TextField();
            omanikuraha.setFont(Font.font("Arial", 30));
            omanikuraha.setAlignment(Pos.CENTER_RIGHT);

            TextField teisendus = new TextField();
            teisendus.setFont(Font.font("Arial", 30));
            teisendus.setEditable(false);
            teisendus.setAlignment(Pos.CENTER_RIGHT);

            tekstiv2ljad.setPadding(new Insets(15, 10, 15, 10));
            Button teisendauuesti = new Button("Teisenda");
            teisendauuesti.setMinSize(75, 50);
            teisendanupp.getChildren().add(teisendauuesti);

            ylemine_v2li.getChildren().addAll(omanikuraha, valik);
            tekstiv2ljad.getChildren().addAll(ylemine_v2li, teisendus, teisendanupp);

            teisendauuesti.setOnAction(event1 ->
                    teisendus.setText(Valuuta.valuuta2Eur((String) valik.getValue(), Double.parseDouble(omanikuraha.getText())) + " EUR"));

            nuppTeisenda.setCenter(tekstiv2ljad);

            eisoovi.setOnAction(event -> vahelava.hide());

            okbutton.setOnAction(event -> {
                try {
                    int sisestatud_tunnusnr = Integer.parseInt(tunnusnumber_sisestus.getText());
                    Rahakott oigerahakott = null;
                    for (Rahakott rahakott : rahakotid) {
                        if (rahakott.getTunnusNumber() == sisestatud_tunnusnr) {
                            oigerahakott = rahakott;
                        }
                    }
                    valik.getSelectionModel().select(oigerahakott.getValuutaNimi());
                    omanikuraha.setText(Double.toString(oigerahakott.getValuutaKogus()));
                    teisendus.setText(Valuuta.valuuta2Eur((String) valik.getValue(), Double.parseDouble(omanikuraha.getText())) + " EUR");
                    vahelava.hide();
                } catch (Exception e) {
                    Stage viga = new Stage();
                    viga.setTitle("Viga");

                    BorderPane veateade = new BorderPane();
                    HBox viga_okbutton = new HBox();
                    viga_okbutton.setAlignment(Pos.CENTER);

                    try (FileInputStream fis = new FileInputStream("viga_pilt.png")) {
                        ImageView veapilt = new ImageView(new Image(fis));
                        veapilt.setFitHeight(50);
                        veapilt.setFitWidth(50);

                        VBox vbox_veapilt = new VBox(veapilt);
                        vbox_veapilt.setAlignment(Pos.CENTER);
                        vbox_veapilt.setPadding(new Insets(0, 0, 0, 20));

                        veateade.setLeft(vbox_veapilt);
                        viga_okbutton.setPadding(new Insets(0, 0, 25, 0));
                        Text veatekst = new Text("Sisestusviga");
                        Button ok_nupp = new Button("OK");
                        ok_nupp.setOnAction(event2 -> viga.hide());
                        viga_okbutton.getChildren().add(ok_nupp);
                        veateade.setCenter(veatekst);
                        veateade.setBottom(viga_okbutton);

                        Scene veastseen = new Scene(veateade, 250, 125);
                        viga.setScene(veastseen);
                        viga.show();

                    } catch (Exception e2) {
                        System.out.println("fatal error");
                        System.exit(1);
                    }
                }
            });


            HBox tagasiNuppTeisenda = new HBox();
            tagasiNuppTeisenda.setPadding(new Insets(0, 0, 75, 0));
            tagasiNuppTeisenda.setAlignment(Pos.CENTER_LEFT);
            Button teisendanupp_tagasi = new Button("<- Tagasi");
            teisendanupp_tagasi.setMinSize(75, 50);
            tagasiNuppTeisenda.getChildren().add(teisendanupp_tagasi);
            nuppTeisenda.setBottom(tagasiNuppTeisenda);


            /********************
             * NUPP ÜLEKANNE
             ********************/

            Button ylekanne = new Button("ÜLEKANNE");
            ylekanne.setMaxWidth(Double.MAX_VALUE);
            ylekanne.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            ylekanne.setPrefHeight(100);
            nupud.getChildren().add(ylekanne);

            BorderPane nuppYlekanne = new BorderPane();
            nuppYlekanne.setPadding(new Insets(0, 8, 0, 8));

            HBox xnuppYlekanne = new HBox();
            xnuppYlekanne.setPadding(new Insets(5, -3, 60, 0));
            xnuppYlekanne.setAlignment(Pos.CENTER_RIGHT);
            Button ylekannenupp_x = new Button("X");
            xnuppYlekanne.getChildren().add(ylekannenupp_x);
            nuppYlekanne.setTop(xnuppYlekanne);

            HBox tagasiNuppYlekanne = new HBox();
            tagasiNuppYlekanne.setPadding(new Insets(0, 0, 75, 0));
            tagasiNuppYlekanne.setAlignment(Pos.CENTER_LEFT);
            Button ylekannenupp_tagasi = new Button("<- Tagasi");
            ylekannenupp_tagasi.setMinSize(75, 50);
            tagasiNuppYlekanne.getChildren().add(ylekannenupp_tagasi);
            nuppYlekanne.setBottom(tagasiNuppYlekanne);

            VBox ylekanne_tekstiv2ljad = new VBox();
            ylekanne_tekstiv2ljad.setSpacing(20);

            HBox tunnusnumbri_sisestamine_ylekanne = new HBox();
            tunnusnumbri_sisestamine_ylekanne.setAlignment(Pos.CENTER);
            tunnusnumbri_sisestamine_ylekanne.setPadding(new Insets(20,0,0,0));
            tunnusnumbri_sisestamine_ylekanne.setSpacing(10);
            Text ylekanne_abistav_tekst_1 = new Text("Sisestage oma tunnusnumber");
            TextField ylekanne_tekstiv2li_tunnusnumber = new TextField();

            ylekanne_tekstiv2li_tunnusnumber.setAlignment(Pos.CENTER);
            ylekanne_tekstiv2li_tunnusnumber.setPrefWidth(60);
            ylekanne_tekstiv2li_tunnusnumber.setFont(Font.font("Arial", 20));
            tunnusnumbri_sisestamine_ylekanne.getChildren().addAll(ylekanne_abistav_tekst_1, ylekanne_tekstiv2li_tunnusnumber);

            HBox ylekanneKantavSummaHBox = new HBox();
            ylekanneKantavSummaHBox.setAlignment(Pos.CENTER);
            ylekanneKantavSummaHBox.setSpacing(10);
            Text ylekanneAbistavTekst2 = new Text("Kui palju soovite kanda");
            TextField tekstiv2liKantavSumma = new TextField();
            tekstiv2liKantavSumma.setFont(Font.font("Arial", 20));
            tekstiv2liKantavSumma.setAlignment(Pos.CENTER);
            tekstiv2liKantavSumma.setPrefWidth(100);
            ylekanneKantavSummaHBox.getChildren().addAll(ylekanneAbistavTekst2, tekstiv2liKantavSumma);

            HBox ylekanneKantavaKoodHBox = new HBox();
            ylekanneKantavaKoodHBox.setAlignment(Pos.CENTER);
            ylekanneKantavaKoodHBox.setSpacing(10);
            Text ylekanneKantavakood = new Text("Sisestage rahakoti kood, kuhu soovite kanda");
            TextField ylekanneKantavaKoodTextfield = new TextField();
            ylekanneKantavaKoodTextfield.setFont(Font.font("Arial"));
            ylekanneKantavaKoodTextfield.setAlignment(Pos.CENTER);
            ylekanneKantavaKoodHBox.getChildren().addAll(ylekanneKantavakood, ylekanneKantavaKoodTextfield);


            HBox ylekanneKannaYle = new HBox();
            ylekanneKannaYle.setAlignment(Pos.CENTER);
            Button kannayle = new Button("Tee ülekanne");
            ylekanneKannaYle.getChildren().add(kannayle);

            ylekanne_tekstiv2ljad.getChildren().addAll(tunnusnumbri_sisestamine_ylekanne, ylekanneKantavSummaHBox, ylekanneKantavaKoodHBox, ylekanneKannaYle);

            nuppYlekanne.setCenter(ylekanne_tekstiv2ljad);

            kannayle.setOnAction(event -> {
                tunnusnumbri_sisestamine_ylekanne.getChildren().clear();
                tunnusnumbri_sisestamine_ylekanne.getChildren().addAll(ylekanne_abistav_tekst_1, ylekanne_tekstiv2li_tunnusnumber);
                String ylekanneTunnusNumber = ylekanne_tekstiv2li_tunnusnumber.getText();

                ylekanneKantavSummaHBox.getChildren().clear();
                ylekanneKantavSummaHBox.getChildren().addAll(ylekanneAbistavTekst2, tekstiv2liKantavSumma);
                String ylekanneKantavSumma = tekstiv2liKantavSumma.getText();

                ylekanneKantavaKoodHBox.getChildren().clear();
                ylekanneKantavaKoodHBox.getChildren().addAll(ylekanneKantavakood, ylekanneKantavaKoodTextfield);
                String ylekanneKantavKood = ylekanneKantavaKoodTextfield.getText();


                try {
                    FileInputStream fis3 = new FileInputStream("viga_pilt.png");
                    FileInputStream fis4 = new FileInputStream("ok_m2rk.png");
                    Image ylekanne_viga_pilt_image = new Image(fis3);
                    Image ylekanne_ok_pilt_image = new Image(fis4);

                    ImageView ylekanne_viga_pilt1 = new ImageView(ylekanne_viga_pilt_image);
                    ylekanne_viga_pilt1.setFitHeight(25);
                    ylekanne_viga_pilt1.setFitWidth(25);
                    ImageView ylekanne_viga_pilt2 = new ImageView(ylekanne_viga_pilt_image);
                    ylekanne_viga_pilt2.setFitHeight(25);
                    ylekanne_viga_pilt2.setFitWidth(25);
                    ImageView ylekanne_viga_pilt3 = new ImageView(ylekanne_viga_pilt_image);
                    ylekanne_viga_pilt3.setFitHeight(25);
                    ylekanne_viga_pilt3.setFitWidth(25);


                    ImageView ylekanne_ok_pilt1 = new ImageView(ylekanne_ok_pilt_image);
                    ylekanne_ok_pilt1.setFitWidth(25);
                    ylekanne_ok_pilt1.setFitHeight(25);
                    ImageView ylekanne_ok_pilt2 = new ImageView(ylekanne_ok_pilt_image);
                    ylekanne_ok_pilt2.setFitWidth(25);
                    ylekanne_ok_pilt2.setFitHeight(25);
                    ImageView ylekanne_ok_pilt3 = new ImageView(ylekanne_ok_pilt_image);
                    ylekanne_ok_pilt3.setFitWidth(25);
                    ylekanne_ok_pilt3.setFitHeight(25);

                    int i = 0;
                    while (true) {
                        if (i == rahakotid.size()) {
                            tunnusnumbri_sisestamine_ylekanne.getChildren().add(ylekanne_viga_pilt1);
                            break;
                        }

                        else if (rahakotid.get(i).getTunnusNumber() == Integer.parseInt(ylekanneTunnusNumber)) {
                            tunnusnumbri_sisestamine_ylekanne.getChildren().add(ylekanne_ok_pilt1);
                            break;
                        }
                        i++;

                    }

                    int j = 0;
                    while (true) {
                        if (j == rahakotid.size()) {
                            ylekanneKantavaKoodHBox.getChildren().add(ylekanne_viga_pilt3);
                            break;
                        }

                        else if (rahakotid.get(j).getKood().equals(ylekanneKantavKood)) {
                            ylekanneKantavaKoodHBox.getChildren().add(ylekanne_ok_pilt3);
                            break;
                        }
                        j++;

                    }

                    Rahakott kandjaRahakott = null;

                    for (Rahakott rahakott : rahakotid) {
                        if (rahakott.getTunnusNumber() == Integer.parseInt(ylekanneTunnusNumber)) {
                            kandjaRahakott = rahakott;
                            break;
                        }
                    }

                    Rahakott saajaRahakott = null;
                    for (Rahakott rahakott : rahakotid) {
                        if (rahakott.getKood().equals(ylekanneKantavKood)) {
                            saajaRahakott = rahakott;
                            break;
                        }
                    }

                    if (Double.parseDouble(ylekanneKantavSumma) <= 0 || Double.parseDouble(ylekanneKantavSumma) > kandjaRahakott.getValuutaKogus()) {
                        ylekanneKantavSummaHBox.getChildren().add(ylekanne_viga_pilt2);
                    }
                    else ylekanneKantavSummaHBox.getChildren().add(ylekanne_ok_pilt2);





                    if (tunnusnumbri_sisestamine_ylekanne.getChildren().contains(ylekanne_ok_pilt1) &&
                            ylekanneKantavSummaHBox.getChildren().contains(ylekanne_ok_pilt2) &&
                            ylekanneKantavaKoodHBox.getChildren().contains(ylekanne_ok_pilt3)) {


                        tegevusYlekanne.kannayle2(kandjaRahakott, saajaRahakott, Double.parseDouble(ylekanneKantavSumma));

                        Stage ylekanne_koikok = new Stage();
                        ylekanne_koikok.setTitle("Sisestatud");
                        BorderPane ylekanneOkAken = new BorderPane();
                        HBox ylekannekoikOk = new HBox();
                        ylekannekoikOk.setAlignment(Pos.CENTER);

                        ylekannekoikOk.setPadding(new Insets(0,0,25,0));
                        Text ylekanneKoikKorras = new Text("Ülekanne tehtud");
                        Button ylekannekoikOk_Okbutton = new Button("OK");

                        ylekannekoikOk_Okbutton.setOnAction(event2 -> ylekanne_koikok.hide());
                        ylekannekoikOk.getChildren().add(ylekannekoikOk_Okbutton);
                        ylekanneOkAken.setCenter(ylekanneKoikKorras);
                        ylekanneOkAken.setBottom(ylekannekoikOk);

                        Scene ylekanneOkStseen = new Scene(ylekanneOkAken, 250, 125);
                        ylekanne_koikok.setScene(ylekanneOkStseen);
                        ylekanne_koikok.show();
                    }

                } catch (Exception e) {
                    Stage viga = new Stage();
                    viga.setTitle("Viga");

                    BorderPane veateade = new BorderPane();
                    HBox viga_okbutton = new HBox();
                    viga_okbutton.setAlignment(Pos.CENTER);

                    try (FileInputStream fis = new FileInputStream("viga_pilt.png")) {
                        ImageView veapilt = new ImageView(new Image(fis));
                        veapilt.setFitHeight(50);
                        veapilt.setFitWidth(50);

                        VBox vbox_veapilt = new VBox(veapilt);
                        vbox_veapilt.setAlignment(Pos.CENTER);
                        vbox_veapilt.setPadding(new Insets(0, 0, 0, 20));

                        veateade.setLeft(vbox_veapilt);
                        viga_okbutton.setPadding(new Insets(0, 0, 25, 0));
                        Text veatekst = new Text("Sisestusviga");
                        Button ok_nupp = new Button("OK");
                        ok_nupp.setOnAction(event2 -> viga.hide());
                        viga_okbutton.getChildren().add(ok_nupp);
                        veateade.setCenter(veatekst);
                        veateade.setBottom(viga_okbutton);

                        Scene veastseen = new Scene(veateade, 250, 125);
                        viga.setScene(veastseen);
                        viga.show();
                    } catch (Exception e2) {
                        System.out.println("fatal error");
                        System.exit(1);
                    }
                }
            });





            alus.setCenter(nupud);


            //stseenid
            Scene peastseen = new Scene(alus, 480, 800);
            Scene stseenN2ita = new Scene(nuppn2ita, 480, 800);
            Scene stseenTeisenda = new Scene(nuppTeisenda, 480, 800);
            Scene stseenTunnusnumbriSisestus = new Scene(sisestamine, 300, 150);
            Scene stseenLisa = new Scene(nupplisa, 480, 800);
            Scene stseenYlekanne = new Scene(nuppYlekanne, 480, 800);



            n2ita.setOnAction(event -> {
                pealava.setScene(stseenN2ita);
                pealava.setTitle("Näitamine");
            });
            teisenda.setOnAction(event -> {
                pealava.setScene(stseenTeisenda);
                pealava.setTitle("Teisendamine");
                vahelava.setScene(stseenTunnusnumbriSisestus);
                vahelava.show();
            });
            lisa.setOnAction(event -> {
                pealava.setScene(stseenLisa);
                pealava.setTitle("Lisamine");
            });
            ylekanne.setOnAction(event -> {
                pealava.setScene(stseenYlekanne);
                pealava.setTitle("Ülekanne");
            });
            eisoovi.setOnAction(event -> vahelava.hide());


            //tagasinupud
            tagasinupp_näita.setOnAction(event -> {
                pealava.setScene(peastseen);
                pealava.setTitle("Peamenüü");
                rahakotid_tabelina.getItems().clear();
                for (Rahakott rahakott : rahakotid) {
                    rahakotid_tabelina.getItems().add(rahakott);
                }
            });
            teisendanupp_tagasi.setOnAction(event -> {
                pealava.setScene(peastseen);
                pealava.setTitle("Peamenüü");
                rahakotid_tabelina.getItems().clear();
                for (Rahakott rahakott : rahakotid) {
                    rahakotid_tabelina.getItems().add(rahakott);
                }
            });
            tagasinupp_lisa.setOnAction(event -> {
                pealava.setScene(peastseen);
                pealava.setTitle("Peamenüü");
                rahakotid_tabelina.getItems().clear();
                for (Rahakott rahakott : rahakotid) {
                    rahakotid_tabelina.getItems().add(rahakott);
                }
            });

            ylekannenupp_tagasi.setOnAction(event -> {
                pealava.setScene(peastseen);
                pealava.setTitle("Peamenüü");
                rahakotid_tabelina.getItems().clear();
                for (Rahakott rahakott : rahakotid) {
                    rahakotid_tabelina.getItems().add(rahakott);
                }
            });



            //x nupud
            nupp_x.setOnAction(event -> {
                pealava.hide();
                vahelava.hide();
                FailiKirjutaja.kirjutaja2(rahakotid);
            });
            n2itanupp_x.setOnAction(event -> {
                pealava.hide();
                vahelava.hide();
                FailiKirjutaja.kirjutaja2(rahakotid);
            });
            teisendanupp_X.setOnAction(event -> {
                pealava.hide();
                vahelava.hide();
                FailiKirjutaja.kirjutaja2(rahakotid);
            });

            lisanupp_x.setOnAction(event -> {
                pealava.hide();
                vahelava.hide();
                FailiKirjutaja.kirjutaja2(rahakotid);
            });

            ylekannenupp_x.setOnAction(event -> {
                pealava.hide();
                vahelava.hide();
                FailiKirjutaja.kirjutaja2(rahakotid);
            });



            pealava.setScene(peastseen);
            pealava.show();

            pealava.setTitle("Peamenüü");

            pealava.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    FailiKirjutaja.kirjutaja2(rahakotid);
                    Platform.exit();
                    System.exit(0);
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
