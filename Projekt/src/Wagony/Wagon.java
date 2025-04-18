package Wagony;

import Exceptions.ZlyWyborWagonu;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Wagon {
    public static int index=0;
    private int id;
    private String nazwa;
    private int wagaNetto;
    private int wagaBrutto;

    public Wagon(String nazwa, int wagaNetto, int wagaBrutto) {
        this.id = ++index;
        this.nazwa = nazwa;
        this.wagaNetto = wagaNetto;
        this.wagaBrutto = wagaBrutto;
    }

    public static int zliczWage(ArrayList<Wagon> wagon){
        int suma=0;
        for (Wagon w: wagon) {
            suma+=w.wagaBrutto;
        }
        return suma;
    }

    public static int zliczIlePodlaczanychDoSieci(ArrayList<Wagon> wagon){
        int suma=0;
        for (Wagon w: wagon) {
            if(w instanceof PodlaczenieDoSieciElektrycznej){
                suma++;
            }
        }
        return suma;
    }

    public void szczegoly(){
    }

    ////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }
    public int getWagaNetto() {
        return wagaNetto;
    }

    public int getWagaBrutto() {
        return wagaBrutto;
    }
    //////////////////////////////////////

    public static Wagon dodajWagon() throws ZlyWyborWagonu{
        Scanner scan = new Scanner(System.in);
        System.out.println("******************************************************");
        System.out.println("* 1 - Wagon Pasazerski                               *");
        System.out.println("* 2 - Wagon pocztowy                                 *");
        System.out.println("* 3 - Wagon bagazowo-pocztowy                        *");
        System.out.println("* 4 - Wagon restauracyjny                            *");
        System.out.println("* 5 - Wagon towarowy podstawowy                      *");
        System.out.println("* 6 - Wagon towarowy ciezki                          *");
        System.out.println("* 7 - Wagon Chlodniczy                               *");
        System.out.println("* 8 - Wagon na materialy ciekle                      *");
        System.out.println("* 9 - Wagon na materialy gazowe                      *");
        System.out.println("* 10 - Wagon na materialy wybuchowe                  *");
        System.out.println("* 11 - Wagon na materialy toksyczne                  *");
        System.out.println("* 12 - Wagon na materialy toksyczne                  *");
        System.out.println("******************************************************");

        int x=scan.nextInt();
        return switch (x){
            case 1 -> new WagonPasazerski("Pasazerski", (int)(Math.random()*1500+3500));
            case 2 -> new WagonPocztowy("pocztowy", (int)(Math.random()*1500+3700));
            case 3 -> new WagonBagazowoPocztowy("BagazowoPocztowy", (int)(Math.random()*1500+3300));
            case 4 -> new WagonRestauracyjny("Restauracyjny", (int)(Math.random()*1500+4000));
            case 5 -> new WagonTowarowyPodstawowy("TowarowyPodstawowy", (int)(Math.random()*4000+3000));
            case 6 -> new WagonTowarowyCiezki("TowarowyCiezki", (int)(Math.random()*5000+3700));
            case 7 -> new WagonChlodniczy("Chlodniczy", (int)(Math.random()*2000+2900));
            case 8 -> new WagonNaMaterialyCiekle("Ciekle", (int)(Math.random()*7000+2700));
            case 9 -> new WagonNaMaterialyGazowe("Gazowe", (int)(Math.random()*6000+2800));
            case 10 -> new WagonNaMaterialyWybuchowe("Wybuchowe", (int)(Math.random()*5000+3900));
            case 11 -> new WagonNaMaterialyToksyczne("Toksyczne", (int)(Math.random()*4000+3400));
            case 12 -> new WagonNaCiekleMaterialyToksyczne("CieklToksyczne", (int)(Math.random()*5000+3500));
            default -> throw new ZlyWyborWagonu("Wybrales zly numer");
        };
    }

    public static Wagon dodajWagonAuto() throws ZlyWyborWagonu{
        int x=(int)(Math.random()*12+1);
        return switch (x){
            case 1 -> new WagonPasazerski("Pasazerski", 4000);
            case 2 -> new WagonPocztowy("pocztowy", 4100);
            case 3 -> new WagonBagazowoPocztowy("BagazowoPocztowy", 3800);
            case 4 -> new WagonRestauracyjny("Restauracyjny", 5000);
            case 5 -> new WagonTowarowyPodstawowy("TowarowyPodstawowy", 7000);
            case 6 -> new WagonTowarowyCiezki("TowarowyCiezki", 9000);
            case 7 -> new WagonChlodniczy("Chlodniczy", 7000);
            case 8 -> new WagonNaMaterialyCiekle("Ciekle", 7000);
            case 9 -> new WagonNaMaterialyGazowe("Gazowe", 7000);
            case 10 -> new WagonNaMaterialyWybuchowe("Wybuchowe", 9000);
            case 11 -> new WagonNaMaterialyToksyczne("Toksyczne", 9000);
            case 12 -> new WagonNaCiekleMaterialyToksyczne("CieklToksyczne", 9500);
            default -> throw new ZlyWyborWagonu("Wybrales zly numer");
        };
    }
    ////////////////////////////
    @Override
    public String toString() {
        return "id: "+id +
                " nazwa: "+nazwa +
                " Waga netto: " + wagaNetto +
                " Waga brutto: " + wagaBrutto;
    }
    public static void wyswietlListeWagonow(ArrayList<Wagon> wagony){
        int i=0;
        for(Wagon w : wagony){
            System.out.println((++i)+": "+w);
        }
    }
}
