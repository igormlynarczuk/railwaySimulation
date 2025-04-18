import Exceptions.*;
import Wagony.Wagon;

import java.util.ArrayList;
import java.util.Scanner;

public class Presentation {
    public static void main(String[] args) {

        System.out.println("PREZENTOWANIE METOD");
        ///////////PRZYKŁAD JAZDY I PREWENCJI KOLIZJI
        {
            ArrayList<Lokomotywa> lokomotywy = new ArrayList<>();
            ArrayList<Wagon> wagony = new ArrayList<>();
            ArrayList<Pociag> pociagi = new ArrayList<>();
            ArrayList<Stacja> stacje = new ArrayList<>();
            ArrayList<Polaczenia> polaczenia = new ArrayList<>();
            for(int i=0; i<2; i++){
                stacje.add(Stacja.dodajStacje());
            }
            //TWORZY POLACZENIA POMIEDZY PIERWSZYMI 2 STACJAMI
            try {
                Stacja.dodajPolaczenie(stacje,0,1);
                polaczenia.add(new Polaczenia(stacje.get(0), stacje.get(1), (int)(Math.random()*3+2)));
            } catch (StacjeSaJuzPolaczone | StacjaNieIstnieje | TeSameStacje e) {
                System.err.println(e);
            }
            ///TWORZY LOKOMOTYWY
            for(int i=0; i<3; i++){
                lokomotywy.add(new Lokomotywa("Lokomotywa", stacje.get((int)(Math.random()*stacje.size()))
                        , stacje.get(0), stacje.get(1),
                        (int)(Math.random()*5)+10, (int)(Math.random()*4)+1,
                        (int)(Math.random()*40000)+60000, Math.random()*30+150));
            }
            Lokomotywa.wyswietlListeLokomotyw(lokomotywy);
            //////////////////////////TWORZY POCIAGI
            while(lokomotywy.size()>0){
                int z=(int)(Math.random()*(lokomotywy.size()));//losuje id lokomotywy
                pociagi.add(new Pociag(lokomotywy.get(z), polaczenia, stacje));
                lokomotywy.remove(z);
            }

            Thread[] threads = new Thread[pociagi.size()];
            for(int i=0; i<pociagi.size(); i++){
                threads[i]= new Thread(pociagi.get(i));
            }

            for(int i=0; i<threads.length; i++){
                threads[i].start();
            }
            try {
                threads[0].join();
                threads[1].join();
                threads[2].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        ///////////ZNAJDYWANIE DROGI
        {
            System.out.println("PRZYKŁAD ZNAJDYWANIA DROGI MIĘDZY STACJAMI");
            ArrayList<Stacja> stacje = new ArrayList<>();
            ArrayList<Polaczenia> polaczenia = new ArrayList<>();
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

            //ZNAJDUJE DROGE
            try {
                System.out.println(Stacja.znajdzDroge(stacje, stacje.get(0), stacje.get(3)));
            } catch (NieDaSieZnalezcDrogi e) {
                throw new RuntimeException(e);
            }
        }
        //

        //TWORZENIE KILKU POCIĄGU
        {
            ArrayList<Lokomotywa> lokomotywy = new ArrayList<>();
            ArrayList<Wagon> wagony = new ArrayList<>();
            ArrayList<Pociag> pociagi = new ArrayList<>();
            ArrayList<Stacja> stacje = new ArrayList<>();
            ArrayList<Polaczenia> polaczenia = new ArrayList<>();
            for(int i=0; i<5; i++){
                stacje.add(Stacja.dodajStacje());
            }
            boolean domomentu=true;
            while(domomentu){
                lokomotywy.add(Lokomotywa.dodajlokomotywe(stacje));
                Scanner scan = new Scanner(System.in);
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
                        System.out.println("Podaj index lokomotywy do usuniecia");
                        Pociag.wyswietlListePociagow(pociagi);
                        int z=scan.nextInt()-1;//jedynke zamienia na index 0
                        if(z>=pociagi.size())
                            break;
                        pociagi.remove(z);
                    }
                    default -> domomentu=false;
                }
            }
        }

    }
}
