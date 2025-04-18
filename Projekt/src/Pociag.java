import Exceptions.*;
import Wagony.Wagon;

import java.lang.reflect.Array;
import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Pociag implements Runnable{
    public static int index=0;
    private int id;
    private Lokomotywa lokomotywa;
    private ArrayList<Wagon> wagony;
    private ArrayList<Stacja>droga;
    private Stacja aktualnaStacja;
    ///////////////////////////////////////
    private boolean czekaNaZwolnienieStacji;
    private String daneOPrzejezdzie;
    private ArrayList<Polaczenia>polaczenia;
    private ArrayList<Stacja>stacje;
    private static String monitor = new String();
    private boolean czyUkonczone;
    public Pociag(Lokomotywa lokomotywa, ArrayList<Polaczenia>polaczenia, ArrayList<Stacja>stacje) {
        this.id=++index;
        this.lokomotywa = lokomotywa;
        this.wagony = new ArrayList<>();
        this.droga=new ArrayList<>();
        this.polaczenia=polaczenia;
        this.stacje=stacje;
        czyUkonczone=false;
    }

    public static Pociag stworzPociag(ArrayList<Lokomotywa>lokomotywy, ArrayList<Stacja>stacje, ArrayList<Polaczenia>polaczenia) throws BrakLokomotyw{
        Scanner scan = new Scanner(System.in);
        if(lokomotywy.isEmpty())
            throw new BrakLokomotyw("BRAK LOKOMOTYW W LISCIE NAJPIER MUSISZ JE STWOZYC");
        System.out.println("WYBIERZ JEDNA Z LOKOMOTYW");
        Lokomotywa.wyswietlListeLokomotyw(lokomotywy);
        int z=scan.nextInt()-1;//jedynke zamienia na index 0
        Pociag pociag = new Pociag(lokomotywy.get(z), polaczenia, stacje);
        lokomotywy.remove(z);
        return pociag;
    }
    public void dodajWagon(Wagon w) throws ZaDuzoWagonow, LimitWagi, LimitPodlaczenDoSieci{
        if(wagony.size()+1>lokomotywa.getMaxWagonow())
            throw new ZaDuzoWagonow("LIMIT WAGONOW OSIAGNIETY");
        if(Wagon.zliczWage(this.wagony)+w.getWagaBrutto()>lokomotywa.getMaxWaga())
            throw new LimitWagi("DODANIE WAGONU PRZEKROCZY WAGE");
        if(Wagon.zliczIlePodlaczanychDoSieci(this.wagony)+1>lokomotywa.getMaxWagonowSieci())
            throw new LimitPodlaczenDoSieci("DODANIE WAGONU PRZEKROCZY LIMIT WAGONOW PODLACZONYCH DO SIECI");
        this.wagony.add(w);
    }
    /////////////////////////////////////

    @Override
    public void run() {
        boolean czyDrogaPowrotna=false;
        //JEDZIE Z STACJI ZRODLOWEJ DO DOCELOWEJ
        try {
            this.droga = Stacja.znajdzDroge(stacje,lokomotywa.getStacjaZrodlowa(),lokomotywa.getStacjaDocelowa());
        } catch (NieDaSieZnalezcDrogi e) {
            System.err.println(e);
        }
        jedzWJednaStrone(czyDrogaPowrotna);
        System.out.println("Pociag o id: " + id + " czeka na wypakunek 30 sekund");
        daneOPrzejezdzie = "id pociagu: " + id + " WYPAKOWUJE SIE";
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pociag o id: " + id + " WYPAKOWAL SIE");
        //JEDZIE Z STACJI DOCELOWEJ DO ZRODLOWEJ
        czyDrogaPowrotna=true;
        try {
            this.droga = Stacja.znajdzDroge(stacje,lokomotywa.getStacjaDocelowa(),lokomotywa.getStacjaZrodlowa());
        } catch (NieDaSieZnalezcDrogi e) {
            System.err.println(e);
        }
        jedzWJednaStrone(czyDrogaPowrotna);
        System.out.println("Pociag o id: " + id + " WROCIL DO STACJI ZRODLOWEJ");
        daneOPrzejezdzie = "id pociagu: " + id + " WROCIL DO STACJI ZRODLOWEJ";
        this.czyUkonczone=true;
    }
    public void jedzWJednaStrone(boolean czyDrogaPowrotna){
        czekaNaZwolnienieStacji=false;
        aktualnaStacja=droga.get(0);
        daneOPrzejezdzie = "id pociagu: " + id + " " + droga;
        int indexDrogi=1;
        while(aktualnaStacja!=droga.get(droga.size()-1)){
            try {
                Polaczenia aktualnePolaczenie=polaczenia.get(Polaczenia.znajdzIndexPolaczenia(polaczenia, aktualnaStacja, droga.get(indexDrogi)));
                if(!aktualnePolaczenie.getPociagiWKolejce().contains(this))
                    aktualnePolaczenie.dodajDoKolejki(this);
                if (!aktualnePolaczenie.getCzyZajete() && aktualnePolaczenie.getPociagiWKolejce().get(0)==this) {
                    if(czekaNaZwolnienieStacji){
                        System.err.println("Pociag o id: " + id + " RUSZA");
                        czekaNaZwolnienieStacji=false;
                    }
                    synchronized (monitor){
                        aktualnePolaczenie.zajmijDroge();
                        aktualnePolaczenie.usunZKolejki(this);
                    }
                    double przejechanaDroga = 0;
                    lokomotywa.nadajPredkosc();
                    while (przejechanaDroga < aktualnePolaczenie.getDlugosc()) {
                        synchronized (monitor) {
                            if (czyDrogaPowrotna)
                                daneOPrzejezdzie = "id pociagu: " + id + " predkosc: " + lokomotywa.getPredkosc() + " procent przejechanego aktualnego odcinka: " + (int) (100 * przejechanaDroga / aktualnePolaczenie.getDlugosc()) + "%" + " JEST W DRODZE POWROTNEJ";
                            else
                                daneOPrzejezdzie = "id pociagu: " + id + " predkosc: " + lokomotywa.getPredkosc() + " procent przejechanego aktualnego odcinka: " + (int) (100 * przejechanaDroga / aktualnePolaczenie.getDlugosc()) + "%" + " JEDZIE DO CELU";
                        }
                        przejechanaDroga += (double) this.lokomotywa.getPredkosc() / 3600;
                        //WYJATEK W RAZIE PRZEKROCZENIA PREDKOSCI
                        try {
                            lokomotywa.zmienPredkosc();
                        } catch (RailroadHazard e) {
                            System.err.println(e);
                            System.err.println("ID POCIAGU: " + id + " POCIAG ZWALNIA");
                            lokomotywa.nadajPredkosc();
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    aktualnaStacja = droga.get(indexDrogi);
                    indexDrogi++;
                    synchronized (monitor){
                        aktualnePolaczenie.zwolnijDroge();
                    }
                }else{//JEZELI POLACZENIE JEST ZAJETE
                    if(!czekaNaZwolnienieStacji){
                        System.err.println("Pociag o id: " + id + " CZEKA NA ZWOLNIENIE DROGI");
                        synchronized (monitor) {
                            if (czyDrogaPowrotna)
                                daneOPrzejezdzie = "id pociagu: " + id + " CZEKA NA ZWOLNIENIE DROGI i JEST W DRODZE POWROTNEJ";
                            else
                                daneOPrzejezdzie = "id pociagu: " + id + " CZEKA NA ZWOLNIENIE DROGI i JEDZIE DO CELU";
                        }
                        czekaNaZwolnienieStacji=true;
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            } catch (PolaczenieNieIstnieje e) {
                throw new RuntimeException(e);
            }

        }
    }

    /////////////////////////////////////
    public Lokomotywa getLokomotywa() {
        return lokomotywa;
    }

    public ArrayList<Wagon> getWagony() {
        return wagony;
    }

    public int getId() {
        return id;
    }
    public ArrayList<Stacja> getStacjeDrogi() {
        return droga;
    }
    public String getDaneOPrzejezdzie() {
        return daneOPrzejezdzie;
    }

    public Stacja getAktualnaStacja() {
        return aktualnaStacja;
    }
    public boolean getCzyUkonczone() {
        return czyUkonczone;
    }

    /////////////////////////////////////
    @Override
    public String toString() {
        String result="";
        if(wagony!=null)
            for (Wagon wagon : wagony) {
                result += wagon + "\n";
            }
        return "Pociag - " +
                "lokomotywa=" + lokomotywa +
                " wagony=\n" + result;
    }
    public static void wyswietlListePociagow(ArrayList<Pociag> pociagi){
        int i=0;
        for(Pociag p : pociagi){
            System.out.println((++i)+": "+p);
        }
    }

}
