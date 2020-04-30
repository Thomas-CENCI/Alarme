import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        Moniteur m1 = new Moniteur("A");
        m1.addAlarmListener(new Alarme("gaz", "salle1"));
        m1.addAlarmListener(new Alarme("incendie", "salle2"));


        Moniteur m2 = new Moniteur("B");
        m2.addAlarmListener(new Alarme("incendie", "salle2"));
        m2.addAlarmListener(new Alarme("radiation", "salle3"));


        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(m1);
        moniteurs.add(m2);

        ArrayList<HashMap<String, String>> anomalies = new ArrayList<HashMap<String, String>>();

        Frame frame = new Frame(moniteurs);
        Frame g = new Frame(frame, anomalies);
    }
}
