package Wagony;

import java.util.Locale;

public class WagonPasazerski extends Wagon implements PodlaczenieDoSieciElektrycznej{

    int maxMiejsc;
    int ileZajetychMiejsc;
    public WagonPasazerski(String nazwa, int wagaBrutto) {
        super(nazwa, 3000, wagaBrutto);
        this.maxMiejsc= (int)(Math.random()*100);
        this.ileZajetychMiejsc=(int)(Math.random()*maxMiejsc);
    }
    public void procentZajetychMiejsc(){
        System.out.println("PROCENT ZAJETYCH MIEJSC TO:");
        System.out.format(Locale.ENGLISH,"%.0f%n", (double)100*ileZajetychMiejsc/maxMiejsc);
    }
    @Override
    public void szczegoly(){
        procentZajetychMiejsc();
    }
    ////////////////////////////////////
    @Override
    public String toString() {
        return "Wagon pasazerski - "+super.toString();
    }
}
