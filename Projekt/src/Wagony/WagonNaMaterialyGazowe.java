package Wagony;

public class WagonNaMaterialyGazowe extends WagonTowarowyPodstawowy{
    public WagonNaMaterialyGazowe(String nazwa, int wagaBrutto) {
        super(nazwa, 2300, wagaBrutto);
    }
    public void szczegoly(){
        System.out.println("SZCZEGOLY wagonu na materialy gazowe");
    }
    /////////////////////////////
    @Override
    public String toString() {
        return "Wagon na materialy gazowe - " +
                "id: "+ getId() +
                " nadawca: "+ getNazwa() +
                " Waga netto" + getWagaNetto() +
                " Waga brutto" + getWagaBrutto();
    }
}
