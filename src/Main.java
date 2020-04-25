import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Moniteur m1 = new Moniteur();
        m1.addAlarmListener(new Alarme());

        Moniteur m2 = new Moniteur();
        m2.addAlarmListener(new Alarme());

        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(m1);
        moniteurs.add(m2);

        Frame f = new Frame(moniteurs);
    }
}
