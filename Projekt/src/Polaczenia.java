import Exceptions.PolaczenieNieIstnieje;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Polaczenia {
    private Stacja a;
    private Stacja b;
    private double dlugosc;
    private boolean czyZajete;
    private ArrayList<Pociag>pociagiWKolejce;

    public Polaczenia(Stacja a, Stacja b, double dlugosc) {
        this.a = a;
        this.b = b;
        this.dlugosc = dlugosc;
        czyZajete=false;
        pociagiWKolejce=new ArrayList<>();
    }

    public static int znajdzIndexPolaczenia(ArrayList<Polaczenia>polaczenia, Stacja a, Stacja b) throws PolaczenieNieIstnieje{
        for (int i=0; i<polaczenia.size(); i++) {
            if(polaczenia.get(i).getA()==a && polaczenia.get(i).getB()==b)
                return i;
            if(polaczenia.get(i).getA()==b && polaczenia.get(i).getB()==a)
                return i;
        }
        System.err.println(a);
        System.err.println(b);
        throw new PolaczenieNieIstnieje("POLACZENIE NIE ISTNIEJE");
    }

    public void zajmijDroge(){
        this.czyZajete=true;
    }

    public void zwolnijDroge(){
        this.czyZajete=false;
    }
    public void dodajDoKolejki(Pociag p){
        pociagiWKolejce.add(p);
    }

    public void usunZKolejki(Pociag p){
        pociagiWKolejce.remove(p);
    }
    //////////////////////////////////////////////////

    public Stacja getA() {
        return a;
    }

    public Stacja getB() {
        return b;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public boolean getCzyZajete() {
        return czyZajete;
    }

    public ArrayList<Pociag> getPociagiWKolejce() {
        return pociagiWKolejce;
    }

    /////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "Droga " + a.getNazwa() + " - " + b.getNazwa() + " dlugosc=" + dlugosc;
    }

    public static void wyswietlListePolaczen(ArrayList<Polaczenia> drogi){
        int i=0;
        for(Polaczenia s : drogi){
            System.out.println((++i)+": "+s);
        }
    }

}
