package Wagony;

public class WagonBagazowoPocztowy extends Wagon{
    String nadawca;
    public WagonBagazowoPocztowy(String nazwa, int wagaBrutto) {
        super(nazwa, 2800, wagaBrutto);
    }
    @Override
    public void szczegoly(){
        System.out.println("Nadawca: " + nadawca);
    }
    //////////////////////////
    @Override
    public String toString() {
        return "Wagon bagazowo-pocztowy - "+super.toString();
    }
}
