import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WatekZapisywania implements Runnable{

    ArrayList<Pociag>pociagi;

    public WatekZapisywania(ArrayList<Pociag> pociagi) {
        this.pociagi = pociagi;
    }

    public static void zapiszDoPliku(ArrayList<Pociag> pociagi) throws FileNotFoundException {
        PrintWriter write = new PrintWriter("wynik.txt");
        for(Pociag pociag: pociagi){
            write.println(pociag.getDaneOPrzejezdzie());
        }
        write.close();
    }

    @Override
    public void run() {
        while(!czyUkonczone(pociagi)){
            try {
                zapiszDoPliku(pociagi);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean czyUkonczone(ArrayList<Pociag> pociagi){
        int i=0;
        for(Pociag pociag: pociagi){
            if(pociag.getCzyUkonczone()){
                i++;
            }
        }

        if(i==pociagi.size()){
            return true;
        }else{
            return false;
        }

    }
}
