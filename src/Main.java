import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Moniteur m1 = new Moniteur("A");
        m1.addAlarmListener(new Alarme("gaz", "salle1"));
        m1.addAlarmListener(new Alarme("incendie", "salle2"));


        Moniteur m2 = new Moniteur("B");
        m2.addAlarmListener(new Alarme("incendie", "salle2"));
        m2.addAlarmListener(new Alarme("radiation", "salle3"));


        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(m1);
        moniteurs.add(m2);

        Frame f = new Frame(moniteurs);
    }
}
