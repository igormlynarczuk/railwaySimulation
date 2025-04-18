package Wagony;

public class WagonTowarowyPodstawowy extends Wagon{
    public WagonTowarowyPodstawowy(String nazwa, int wagaBrutto) {
        super(nazwa, 2000, wagaBrutto);
    }

    public WagonTowarowyPodstawowy(String nazwa, int wagaNetto, int wagaBrutto) {
        super(nazwa, wagaNetto, wagaBrutto);
    }
    //////////////////////////
    @Override
    public String toString() {
        return "Wagon towarowy podstawowy - "+super.toString();
    }
}
