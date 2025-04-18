package Wagony;

public class WagonNaMaterialyCiekle extends WagonTowarowyPodstawowy{
    public WagonNaMaterialyCiekle(String nazwa, int wagaBrutto) {
        super(nazwa, 2200, wagaBrutto);
    }
    public void szczegoly(){
        System.out.println("SZCZEGOLY wagonu Na Materialy ciekle");
    }
    ////////////////////////
    @Override
    public String toString() {
        return "Wagon na materialy ciekle - " +
                "id: "+ getId() +
                " nadawca: "+ getNazwa() +
                " Waga netto" + getWagaNetto() +
                " Waga brutto" + getWagaBrutto();
    }
}
