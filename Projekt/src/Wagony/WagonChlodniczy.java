package Wagony;

public class WagonChlodniczy extends WagonTowarowyPodstawowy implements PodlaczenieDoSieciElektrycznej{

    public WagonChlodniczy(String nazwa, int wagaBrutto) {
        super(nazwa, 2400, wagaBrutto);
    }
    @Override
    public void szczegoly(){
        System.out.println("SZCZEGOLY wagonu towarowego podstawowego");
    }
    //////////////////////
    @Override
    public String toString() {
        return "Wagon Chlodniczy - " +
                "id: "+ getId() +
                " nadawca: "+ getNazwa() +
                " Waga netto" + getWagaNetto() +
                " Waga brutto" + getWagaBrutto();
    }
}
