package Wagony;

public class WagonTowarowyCiezki extends Wagon{
    public WagonTowarowyCiezki(String nazwa, int wagaBrutto) {
        super(nazwa, 2700, wagaBrutto);
    }

    public WagonTowarowyCiezki(String nazwa, int wagaNetto, int wagaBrutto) {
        super(nazwa, wagaNetto, wagaBrutto);
    }
    ////////////////////////
    @Override
    public String toString() {
        return "Wagon towarowy ciezki - "+super.toString();
    }
}
