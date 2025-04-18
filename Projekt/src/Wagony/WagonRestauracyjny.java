package Wagony;

public class WagonRestauracyjny extends Wagon implements PodlaczenieDoSieciElektrycznej{
    public WagonRestauracyjny(String nazwa, int wagaBrutto) {
        super(nazwa, 3500, wagaBrutto);
    }
    ////////////////////////
    @Override
    public String toString() {
        return "Wagon restauracyjny - "+super.toString();
    }
}
