package Wagony;

public class WagonNaCiekleMaterialyToksyczne extends WagonTowarowyCiezki implements WagonNaMaterialyCiekleCechy{
    public WagonNaCiekleMaterialyToksyczne(String nazwa, int wagaBrutto) {
        super(nazwa, 3000, wagaBrutto);
    }
    @Override
    public void szczegoly(){
        pokaz();
    }
    @Override
    public void pokaz(){
        System.out.println("Zawiera cechy wagonu na materialy ciekle");
    }
    ////////////////////////
    @Override
    public String toString() {
        return "Wagon na ciekle materialy toksyczne - " +
                "id: "+ getId() +
                " nadawca: "+ getNazwa() +
                " Waga netto" + getWagaNetto() +
                " Waga brutto" + getWagaBrutto();
    }
}
