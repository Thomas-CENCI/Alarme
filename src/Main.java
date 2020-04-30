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
        m1.addAlarmListener(new Alarme("gaz", "bâtiment1"));
        m1.addAlarmListener(new Alarme("incendie", "bâtiment2"));


        Moniteur m2 = new Moniteur("B");
        m2.addAlarmListener(new Alarme("incendie", "bâtiment2"));
        m2.addAlarmListener(new Alarme("radiation", "bâtiment3"));

        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(m1);
        moniteurs.add(m2);

        ArrayList<HashMap<String, String>> anomalies = new ArrayList<HashMap<String, String>>();

        Frame frame = new Frame(moniteurs);
        FrameAccueil g = new FrameAccueil(frame, anomalies, moniteurs);

        g.generate_button();
    }
}
