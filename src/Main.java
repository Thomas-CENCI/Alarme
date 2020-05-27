import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        Moniteur mA = new Moniteur("A");
        mA.addAlarmListener(new Alarme("gaz", "bâtiment1"));
        mA.addAlarmListener(new Alarme("incendie", "bâtiment2"));
        mA.addAlarmListener(new Alarme("radiation", "bâtiment4"));

        Moniteur mB = new Moniteur("B");
        mB.addAlarmListener(new Alarme("incendie", "bâtiment2"));
        mB.addAlarmListener(new Alarme("radiation", "bâtiment3"));
        mB.addAlarmListener(new Alarme("gaz", "bâtiment4"));

        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(mA);
        moniteurs.add(mB);

        ArrayList<HashMap<String, String>> anomalies = new ArrayList<HashMap<String, String>>();

        FrameMoniteur frame_moniteur = new FrameMoniteur(mA, mB);
        FrameAccueil frame_accueil = new FrameAccueil(anomalies, moniteurs, frame_moniteur);

       //frame_moniteur.display_moniteur(mA, mB);

        frame_accueil.generate_button();

        Statistics stats = new Statistics(moniteurs);
        
        Thread.sleep(30000); /** Il faut que toutes les fenêtres soient fermées pour que les stats "fonctionnent" */
        System.out.println("\nNombre : " + stats.numberOfAnomalie() + "\nTypes : " + stats.typesOfAnomalie() + "\nDefcon moyen : " + stats.meanDefcon());
    }
}
