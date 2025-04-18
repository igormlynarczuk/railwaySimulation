package Wagony;

public class WagonNaMaterialyToksyczne extends WagonTowarowyCiezki{
    public WagonNaMaterialyToksyczne(String nazwa, int wagaBrutto) {
        super(nazwa, 2900, wagaBrutto);
    }
    //////////////////////
    @Override
    public String toString() {
        return "Wagon na materialy toksyczne - " +
                "id: "+ getId() +
                " nadawca: "+ getNazwa() +
                " Waga netto" + getWagaNetto() +
                " Waga brutto" + getWagaBrutto();
    }
}
