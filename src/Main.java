import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        Moniteur mA = new Moniteur("A");
        mA.addAlarmListener(new Alarme("gaz", "bâtiment1"));
        mA.addAlarmListener(new Alarme("incendie", "bâtiment2"));


        Moniteur mB = new Moniteur("B");
        mB.addAlarmListener(new Alarme("incendie", "bâtiment2"));
        mB.addAlarmListener(new Alarme("radiation", "bâtiment3"));

        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(mA);
        moniteurs.add(mB);

        ArrayList<HashMap<String, String>> anomalies = new ArrayList<HashMap<String, String>>();

        Frame frame_generate = new Frame(moniteurs);
        FrameAccueil frame_accueil = new FrameAccueil(frame_generate, anomalies, moniteurs);

        FrameMoniteur frame_moniteur_A = new FrameMoniteur(mA);
        frame_moniteur_A.display_moniteur(mA);

        FrameMoniteur frame_moniteur_B = new FrameMoniteur(mB);
        frame_moniteur_B.display_moniteur(mB);

        frame_accueil.generate_button();

        Statistics stats = new Statistics(moniteurs);
        
        Thread.sleep(30000); /** Il faut que toutes les fenêtres soient fermées pour que les stats "fonctionnent" */
        System.out.println("\nNombre : " + stats.numberOfAnomalie() + "\nTypes : " + stats.typesOfAnomalie() + "\nDefcon moyen : " + stats.meanDefcon());
    }
}
