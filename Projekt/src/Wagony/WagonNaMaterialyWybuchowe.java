package Wagony;

public class WagonNaMaterialyWybuchowe extends WagonTowarowyCiezki{
    public WagonNaMaterialyWybuchowe(String nazwa, int wagaBrutto) {
        super(nazwa, 3400, wagaBrutto);
    }
    ///////////////////////
    @Override
    public String toString() {
        return "Wagon na materialy wybuchowe - " +
                "id: "+ getId() +
                " nadawca: "+ getNazwa() +
                " Waga netto" + getWagaNetto() +
                " Waga brutto" + getWagaBrutto();
    }
}
