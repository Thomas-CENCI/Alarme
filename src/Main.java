import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        Moniteur mA = new Moniteur("A");

        Moniteur mB = new Moniteur("B");

        ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
        moniteurs.add(mA);
        moniteurs.add(mB);

        ArrayList<HashMap<String, String>> alarmes = new ArrayList<HashMap<String, String>>();

        FrameMoniteur frame_moniteur = new FrameMoniteur(mA, mB);
        FrameAccueil frame_accueil = new FrameAccueil(alarmes, moniteurs, frame_moniteur);

        frame_accueil.generate_button();

        // Statistics stats = new Statistics(moniteurs);
        // System.out.println("\nNombre : " + stats.numberOfAlarme() + "\nTypes : " + stats.typesOfAlarme() + "\nDefcon moyen : " + stats.meanDefcon());
    }
}
