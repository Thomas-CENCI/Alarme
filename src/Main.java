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

        Frame frame_generate = new Frame(moniteurs);
        FrameAccueil frame_accueil = new FrameAccueil(frame_generate, anomalies, moniteurs);

        FrameMoniteur frame_moniteur_A = new FrameMoniteur();
        frame_moniteur_A.display_moniteur(m1);
        m1.setFrame(frame_moniteur_A);

        FrameMoniteur frame_moniteur_B = new FrameMoniteur();
        frame_moniteur_B.display_moniteur(m2);
        m2.setFrame(frame_moniteur_B);

        frame_accueil.generate_button();
    }
}
