package Wagony;

public class WagonPocztowy extends Wagon implements PodlaczenieDoSieciElektrycznej{
    public WagonPocztowy(String nazwa, int wagaBrutto) {
        super(nazwa, 3200, wagaBrutto);
    }
    ////////////////////////////
    @Override
    public String toString() {
        return "Wagon pocztowy - "+super.toString();
    }
}
