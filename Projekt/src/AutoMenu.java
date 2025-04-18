import Exceptions.*;
import Wagony.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
public class AutoMenu{
    static Scanner scan = new Scanner(System.in);
    public static void autoMenu(){
        ArrayList<Lokomotywa> lokomotywy = new ArrayList<>();
        ArrayList<Wagon> wagony = new ArrayList<>();
        ArrayList<Pociag> pociagi = new ArrayList<>();
        ArrayList<Stacja> stacje = new ArrayList<>();
        ArrayList<Polaczenia> polaczenia = new ArrayList<>();
        Generacja.generowanie(stacje, lokomotywy, pociagi, wagony, polaczenia);
        boolean wykonuj=true;
        int x;
        while(wykonuj){
            wybor();
            x=scan.nextInt();
            switch (x){
                case 1 -> dodajUsunLokomotywe(lokomotywy, stacje);

                case 2 -> dodajUsunWagon(wagony);

                case 3 -> dodajUsunPociag(pociagi, lokomotywy, stacje, polaczenia);

                case 4 -> dodajWagonDoPociagu(pociagi, wagony);

                case 5 -> stacje.add(Stacja.dodajStacje());

                case 6 -> dodajPolaczenieMiedzyStacjami(stacje, polaczenia);

                case 7 -> {
                    System.out.println("SKLADY POCIAGOW: ");
                    Pociag.wyswietlListePociagow(pociagi);

                    System.out.println("LOKOMOTYWY NA PARKINGU:");
                    Lokomotywa.wyswietlListeLokomotyw(lokomotywy);

                    System.out.println("WAGONY NA PARKINGU:");
                    Wagon.wyswietlListeWagonow(wagony);

                    System.out.println("STACJE:");
                    Stacja.wyswietlListeStacji(stacje);

                    System.out.println("POLACZENIA:");
                    Polaczenia.wyswietlListePolaczen(polaczenia);
                }

                case 8 -> znajdzDroge(stacje);

                case 9 -> wykonuj=false;

                case 10 -> System.out.println(Wagon.zliczIlePodlaczanychDoSieci(wagony));

                default -> System.err.println("ZLY WYBOR");
            }
        }

        uruchomDroge(pociagi, stacje, lokomotywy, wagony);

    }
    ////////////////////////////////////////////////////////////
    public static void wybor(){
        System.out.println("******************************************************");
        System.out.println("* 1 - Dodaj lub usuń lokomotywe                      *");
        System.out.println("* 2 - Dodaj lub usuń wagon                           *");
        System.out.println("* 3 - Dodaj lub usuń pociag                          *");
        System.out.println("* 4 - Dodaj lub usuń wagon z skladu pociagu          *");
        System.out.println("* 5 - Dodaj stacje                                   *");
        System.out.println("* 6 - Dodaj polaczenie stacji                        *");
        System.out.println("* 7 - Wyswietl dane                                  *");
        System.out.println("* 8 - Znajdz jakas droge                             *");
        System.out.println("* 9 - Wyjdz z menu i odpal wszystkie pociagi         *");
        System.out.println("******************************************************");
    }
    //////////////////////////////////////////////
    //1
    public static void dodajUsunLokomotywe(ArrayList<Lokomotywa>lokomotywy, ArrayList<Stacja> stacje){
        System.out.println("* 1 - Dodaj lokomotywe");
        System.out.println("* 2 - Usuń lokomotywe");
        int x= scan.nextInt();
        switch (x){
            case 1 ->{
                lokomotywy.add(Lokomotywa.dodajlokomotywe(stacje));
            }
            case 2 ->{
                System.out.println("Podaj index lokomotywy do usuniecia");
                Lokomotywa.wyswietlListeLokomotyw(lokomotywy);
                int z=scan.nextInt()-1;//jedynke zamienia na index 0
                if(z>=lokomotywy.size())
                    break;
                lokomotywy.remove(z);
            }
            default -> System.err.println("ZLY WYBOR");
        }
    }
    //////////////////////////////////////////////
    //2
    public static void dodajUsunWagon(ArrayList<Wagon>wagony) {
        System.out.println("* 1 - Dodaj wagon");
        System.out.println("* 2 - Usuń wagon");
        int x = scan.nextInt();
        switch (x) {
            case 1 -> {
                try {
                    wagony.add(Wagon.dodajWagon());
                } catch (ZlyWyborWagonu e) {
                    System.err.println(e);
                }
            }
            case 2 -> {
                System.out.println("Podaj index wagonu do usuniecia");
                Wagon.wyswietlListeWagonow(wagony);
                int z=scan.nextInt()-1;//jedynke zamienia na index 0
                if(z>=wagony.size())
                    break;
                wagony.remove(z);
            }
            default -> System.err.println("ZLY WYBOR");
        }
    }
    //////////////////////////////////////////////
    //3
    public static void dodajUsunPociag(ArrayList<Pociag>pociagi, ArrayList<Lokomotywa>lokomotywy, ArrayList<Stacja> stacje, ArrayList<Polaczenia> polaczenia) {
        System.out.println("* 1 - Dodaj pociag");
        System.out.println("* 2 - Usuń pociag");
        int x = scan.nextInt();
        switch (x) {
            case 1 -> {
                try {
                    pociagi.add(Pociag.stworzPociag(lokomotywy, stacje, polaczenia));
                } catch (BrakLokomotyw e) {
                    System.err.println(e);
                }
            }
            case 2 -> {
                System.out.println("Podaj index pociagu do usuniecia");
                Pociag.wyswietlListePociagow(pociagi);
                int z=scan.nextInt()-1;//jedynke zamienia na index 0
                if(z>=pociagi.size())
                    break;
                pociagi.remove(z);
            }
            default -> System.err.println("ZLY WYBOR");
        }
    }
    //////////////////////////////////////////////
    //4
    public static void dodajWagonDoPociagu(ArrayList<Pociag>pociagi, ArrayList<Wagon> wagony) {
        System.out.println("* 1 - Dodaj wagon do pociagu");
        System.out.println("* 2 - Usuń wagon z pociagu");
        int x = scan.nextInt();
        switch (x) {
            case 1 -> {
                if(wagony.size()!=0){
                    System.out.println("WYBIERZ JEDEN Z POCIAGOW: ");
                    Pociag.wyswietlListePociagow(pociagi);
                    int index_pociag=scan.nextInt()-1;
                    System.out.println("WYBIERZ JEDEN Z WAGONOW: ");
                    Wagon.wyswietlListeWagonow(wagony);
                    int index_wagon=scan.nextInt()-1;
                    try {
                        pociagi.get(index_pociag).dodajWagon(wagony.get(index_wagon));
                        wagony.remove(index_wagon);
                    } catch (ZaDuzoWagonow | LimitWagi | LimitPodlaczenDoSieci e) {
                        System.err.println(e);
                    }
                }else{
                    System.err.println("BRAK WAGONOW");
                }
            }
            case 2 -> {
                System.out.println("WYBIERZ JEDEN Z POCIAGOW: ");
                Pociag.wyswietlListePociagow(pociagi);
                int index_pociag=scan.nextInt()-1;
                System.out.println("WYBIERZ JEDEN Z WAGONOW: ");
                Wagon.wyswietlListeWagonow(pociagi.get(index_pociag).getWagony());
                int index_wagon=scan.nextInt()-1;
                pociagi.get(index_pociag).getWagony().remove(index_wagon);
            }
            default -> System.err.println("ZLY WYBOR");
        }
    }
    //////////////////////////////////////////////
    //5
    public static void dodajPolaczenieMiedzyStacjami(ArrayList<Stacja>stacje, ArrayList<Polaczenia> polaczenia) {
        System.out.println("Podaj indexy stacji ktore chcesz polaczyc");
        Stacja.wyswietlListeStacji(stacje);
        int a=scan.nextInt()-1;
        int b=scan.nextInt()-1;
        try {
            Stacja.dodajPolaczenie(stacje,a,b);
            polaczenia.add(new Polaczenia(stacje.get(a), stacje.get(b), (int)(Math.random()*5+2)));
        } catch (StacjeSaJuzPolaczone e) {
            System.err.println(e);
        } catch (StacjaNieIstnieje e) {
            System.err.println(e);
        }catch (TeSameStacje e) {
            System.err.println(e);
        }
    }

    //////////////////////////////////////////////
    //8
    public static void znajdzDroge(ArrayList<Stacja>stacje) {
        Stacja.wyswietlListeStacji(stacje);
        System.out.println("Podaj nr stacji startu i konca drogi");
        int a=scan.nextInt()-1;
        int b=scan.nextInt()-1;
        if(stacje.size()-1<a || stacje.size()-1<b){
            System.err.println("JEDEN Z INDEXOW JEST ZBYT DUZY");
            return;
        }
        ArrayList<Stacja> droga = null;
        try {
            droga = Stacja.znajdzDroge(stacje, stacje.get(a), stacje.get(b));
            for(Stacja s : droga){
                System.out.println(s);
            }
        } catch (NieDaSieZnalezcDrogi e) {
            System.err.println(e);
        }
    }



    public static void uruchomDroge(ArrayList<Pociag>pociagi, ArrayList<Stacja>stacje, ArrayList<Lokomotywa>lokomotywy, ArrayList<Wagon>wagony){
        //STARTUJE POCIAGI
        Thread[] threads = new Thread[pociagi.size()];
        for(int i=0; i<pociagi.size(); i++){
            threads[i]= new Thread(pociagi.get(i));
        }

        for(int i=0; i<threads.length; i++){
            threads[i].start();
        }
        //WATEK ZAPISYWANIA
        WatekZapisywania zapis = new WatekZapisywania(pociagi);
        Thread thread = new Thread(zapis);
        thread.start();
        boolean wykonuj=true;
        int x;
        while(wykonuj) {
            System.out.println("1: WYSWIETL DANE O DRODZE POCIAGOW");
            x = scan.nextInt();
            switch (x) {
                case 1 -> {
                    for(Pociag pociag: pociagi){
                        System.out.println(pociag.getDaneOPrzejezdzie());
                    }
                }

                default -> System.out.println("ZLY WYBOR");
            }
        }
    }

}
