import Exceptions.RailroadHazard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lokomotywa {

    public static int index=0;
    private int id;
    private String nazwa;
    private Stacja stacjaMacierzysta;
    private Stacja stacjaZrodlowa;
    private Stacja stacjaDocelowa;
    private int MaxWagonow;
    private int MaxWagonowSieci;
    private int MaxWaga;
    private double predkosc;

    public Lokomotywa( String nazwa, Stacja stacjaMacierzysta, Stacja stacjaZrodlowa, Stacja stacjaDocelowa,
                       int maxWagonow,int maxWagonowSieci, int maxWaga, double predkosc) {
        this.id = ++index;
        this.nazwa = nazwa;
        this.stacjaMacierzysta = stacjaMacierzysta;
        this.stacjaZrodlowa = stacjaZrodlowa;
        this.stacjaDocelowa = stacjaDocelowa;
        this.MaxWagonow = maxWagonow;
        this.MaxWagonowSieci = maxWagonowSieci;
        this.MaxWaga = maxWaga;
        this.predkosc = predkosc;
    }

    public void zmienPredkosc() throws RailroadHazard{
        int szansa=(int)(Math.random()*2);
        if(szansa==0)
            this.predkosc+=this.predkosc*0.03;
        else
            this.predkosc-=this.predkosc*0.03;
        if(predkosc>200)
            throw new RailroadHazard("PREDKOSC PRZEKROCZONA: " + predkosc);
    }
    public void nadajPredkosc(){
        this.predkosc=Math.random()*20+150;
    }

    public static Lokomotywa dodajlokomotywe(ArrayList<Stacja>stacje){
        return new Lokomotywa("Lokomotywa", stacje.get((int)(Math.random()*stacje.size()))
                , stacje.get((int)(Math.random()*stacje.size())), stacje.get((int)(Math.random()*stacje.size())),
                (int)(Math.random()*5)+10, (int)(Math.random()*2)+3,
                (int)(Math.random()*40000)+60000, Math.random()*30+150);
    }

    public ArrayList<Stacja> znajdzDroge(ArrayList<Stacja> s, Stacja start, Stacja koniec){
        ArrayList<Stacja>result = new ArrayList<>();
        result.add(start);
        Stacja aktualnaStacja=start;
        boolean[] odwiedzone = new boolean[s.size()];
        Arrays.fill(odwiedzone, false);
        boolean znaleziono=true;
        while(znaleziono){

        }
        return result;
    }


    ////////////////////////////////////////////////
    public int getId() {
        return id;
    }
    public String getNazwa() {
        return nazwa;
    }

    public Stacja getStacjaMacierzysta() {
        return stacjaMacierzysta;
    }

    public Stacja getStacjaZrodlowa() {
        return stacjaZrodlowa;
    }

    public Stacja getStacjaDocelowa() {
        return stacjaDocelowa;
    }
    public int getMaxWagonow() {
        return MaxWagonow;
    }
    public int getMaxWaga() {
        return MaxWaga;
    }
    public int getMaxWagonowSieci() {
        return MaxWagonowSieci;
    }
    public double getPredkosc() {
        return predkosc;
    }
    //////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return  "Lokomotywa - " +
                "IdLokomotywy: " + id +
                " nazwa: " + nazwa +
                " stacjaMacierzysta:" + stacjaMacierzysta.getNazwa() +
                " stacjaZrodlowa:" + stacjaZrodlowa.getNazwa() +
                " stacjaDocelowa:" + stacjaDocelowa.getNazwa() +
                " MaxWagonow:" + MaxWagonow +
                " MaxWaga:" + MaxWaga +
                " MaxWagonowSieci:" + MaxWagonowSieci +
                " predkosc:" + predkosc;
    }
    public static void wyswietlListeLokomotyw(ArrayList<Lokomotywa> lokomotywy){
        int i=0;
        for(Lokomotywa l : lokomotywy){
            System.out.println((++i)+": "+l);
        }
    }
}
