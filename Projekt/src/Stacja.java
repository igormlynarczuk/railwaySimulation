import Exceptions.*;

import java.util.ArrayList;
public class Stacja {
    private String nazwa;
    private ArrayList<Stacja> polaczeniaStacji;

    public Stacja(String nazwa) {
        this.nazwa = nazwa;
        polaczeniaStacji = new ArrayList<>();
    }
    /////////////////////////////////////
    public String getNazwa() {
        return nazwa;
    }

    public ArrayList<Stacja> getPolaczeniaStacji() {
        return polaczeniaStacji;
    }
    ////////////////////////////////////

    public static Stacja dodajStacje(){
        return new Stacja(NazwyStacji.zwrocNazwe());
    }

    public void dodajPolaczenieJednej(Stacja s){
        polaczeniaStacji.add(s);
    }
    public static void dodajPolaczenie(ArrayList<Stacja> stacje, int a, int b) throws StacjeSaJuzPolaczone, StacjaNieIstnieje, TeSameStacje{
        if(stacje.get(a)==stacje.get(b))
            throw new TeSameStacje("NIE MOZESZ PODLACZYC STACJI DO SAMEJ SIEBIE");
        if(a>=stacje.size() || b>=stacje.size())
            throw new StacjaNieIstnieje("PODANE INDEXY SA BLEDNE");
        if(znajdz(stacje.get(a).getPolaczeniaStacji(), stacje.get(b)))
            throw new StacjeSaJuzPolaczone("STACJE SA JUZ POLACZONE");
        stacje.get(a).dodajPolaczenieJednej(stacje.get(b));
        stacje.get(b).dodajPolaczenieJednej(stacje.get(a));
    }

    /////////////////////////////////////////////////
    public static ArrayList<Stacja> znajdzDroge(ArrayList<Stacja>stacje, Stacja start, Stacja koniec) throws NieDaSieZnalezcDrogi{
        ArrayList<Stacja>result = new ArrayList<>();
        boolean[] odwiedzone = new boolean[stacje.size()];
        for(boolean z: odwiedzone)
            z=false;
        int ile_odwiedzonych=0;
        Stacja aktualna = start;
        result.add(aktualna);
        odwiedzone[znajdzIndex(stacje, aktualna)]=true;
        while(ile_odwiedzonych<odwiedzone.length&&aktualna!=koniec){
            if(znajdz(aktualna.getPolaczeniaStacji(), koniec)){
                result.add(koniec);
                aktualna=koniec;
                ile_odwiedzonych++;
            }
            else{
                boolean czy_jest_nie_odwiedzona=false;
                for(int i=0; i<aktualna.getPolaczeniaStacji().size(); i++){
                    //sprawdza czy stacja do ktorej chcemy przejsc NIE byla jeszcze odwiedzona
                    if(odwiedzone[znajdzIndex(stacje, aktualna.getPolaczeniaStacji().get(i))]==false){
                        aktualna=aktualna.getPolaczeniaStacji().get(i);
                        result.add(aktualna);
                        odwiedzone[znajdzIndex(stacje, aktualna)]=true;
                        czy_jest_nie_odwiedzona=true;
                        ile_odwiedzonych++;
                        break;
                    }
                }
                if(czy_jest_nie_odwiedzona==false){
                    if(result.size()-2<0)
                        break;
                    aktualna=result.get(result.size()-2);
                    result.remove(result.size()-1);
                }
            }
        }

        if(aktualna!=koniec)
            throw new NieDaSieZnalezcDrogi("NIE DA SIE ZNALEZC DROGI");

        return result;
    }

    public static boolean znajdz(ArrayList<Stacja>stacje, Stacja szukana){
        for(Stacja s : stacje){
            if(s==szukana)
                return true;
        }
        return false;
    }

    public static int znajdzIndex(ArrayList<Stacja>stacje, Stacja szukana){
        for(int i=0; i<stacje.size(); i++){
            if(stacje.get(i)==szukana)
                return i;
        }
        return 0;
    }
    /////////////////////////////////////
    @Override
    public String toString() {
        String polaczenia="";
        if(polaczeniaStacji!=null)
            for(int i=0; i<polaczeniaStacji.size(); i++){
                polaczenia=polaczenia+polaczeniaStacji.get(i).getNazwa()+", ";
            }
        return "Stacja - " +
                "nazwa: " + nazwa +
                " polaczeniaStacji=" + polaczenia;
    }
    public static void wyswietlListeStacji(ArrayList<Stacja> stacje){
        int i=0;
        for(Stacja s : stacje){
            System.out.println((++i)+": "+s);
        }
    }

    public static void wyswietNazwyZlListyStacji(ArrayList<Stacja> stacje){
        int i=0;
        for(Stacja s : stacje){
            System.out.print(s.getNazwa()+", ");
        }
        System.out.println();
    }

}
