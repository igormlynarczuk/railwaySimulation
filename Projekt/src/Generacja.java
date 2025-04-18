import Exceptions.*;
import Wagony.Wagon;

import java.util.ArrayList;

public class Generacja {
    public static void generowanie(ArrayList<Stacja> stacje, ArrayList<Lokomotywa> lokomotywy, ArrayList<Pociag> pociagi, ArrayList<Wagon> wagony, ArrayList<Polaczenia> polaczenia){
        for(int i=0; i<5; i++){
            stacje.add(Stacja.dodajStacje());
        }
        //TWORZY POLACZENIA POMIEDZY PIERWSZYMI 5 STACJAMI
        try {
            Stacja.dodajPolaczenie(stacje,0,4);
            polaczenia.add(new Polaczenia(stacje.get(0), stacje.get(4), (int)(Math.random()*3+2)));
        } catch (StacjeSaJuzPolaczone | StacjaNieIstnieje | TeSameStacje e) {
            System.err.println(e);
        }

        for(int i=1; i<5; i++){
            try {
                Stacja.dodajPolaczenie(stacje,i-1,i);
                polaczenia.add(new Polaczenia(stacje.get(i-1), stacje.get(i), (int)(Math.random()*3+2)));
            } catch (StacjeSaJuzPolaczone | StacjaNieIstnieje |TeSameStacje e) {
                System.err.println(e);
            }
        }

        for(int i=0; i<95; i++){
            stacje.add(Stacja.dodajStacje());
            try {
                int b=(int)(Math.random()*(stacje.size()-1));
                Stacja.dodajPolaczenie(stacje,stacje.size()-1,b);
                polaczenia.add(new Polaczenia(stacje.get(stacje.size()-1), stacje.get(b), (int)(Math.random()*3+2)));
            } catch (StacjeSaJuzPolaczone | StacjaNieIstnieje | TeSameStacje e) {
                System.err.println(e);
            }
        }
        //PO PODLACZENIU KAZDEJ STACJI DODAJE 80 LOSOWYCH POLACZEN(MOZE BYC MNIEJ JAK WYLOSUJE JUZ ISTNIEJACE POLACZENIA)
        for(int i=0; i<80; i++){
            try {
                int a=(int)(Math.random()*(stacje.size()));
                int b=(int)(Math.random()*(stacje.size()));
                Stacja.dodajPolaczenie(stacje,a,b);
                polaczenia.add(new Polaczenia(stacje.get(a), stacje.get(b), (int)(Math.random()*3+2)));
            } catch (StacjeSaJuzPolaczone | StacjaNieIstnieje | TeSameStacje e) {
                //System.err.println(e);
            }
        }
        System.out.println("STACJE:");
        Stacja.wyswietlListeStacji(stacje);
        /////////////////////////TWORZY LOKOMOTYWY
        for(int i=0; i<25; i++){
            lokomotywy.add(Lokomotywa.dodajlokomotywe(stacje));
        }
        Lokomotywa.wyswietlListeLokomotyw(lokomotywy);
        //////////////////////////TWORZY POCIAGI
        while(lokomotywy.size()>0){
            int z=(int)(Math.random()*(lokomotywy.size()));//losuje id lokomotywy
            pociagi.add(new Pociag(lokomotywy.get(z), polaczenia, stacje));
            lokomotywy.remove(z);
        }
        /////////////////////////TWORZY WAGONY DO POCIAGU
        for(int i=0; i<pociagi.size(); i++){
            int los=(int)(Math.random()*6)+5;
            for(int j=0; j<los; j++){
                try {
                    pociagi.get(i).dodajWagon(Wagon.dodajWagonAuto());
                } catch (ZaDuzoWagonow e) {
                    System.err.println(e);
                } catch (LimitWagi e) {
                    //System.err.println(e);
                } catch (ZlyWyborWagonu e) {
                    System.err.println(e);
                } catch (LimitPodlaczenDoSieci e) {
                    //System.err.println(e);
                }
            }
        }
        Pociag.wyswietlListePociagow(pociagi);
    }
}
