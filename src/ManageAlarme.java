import javax.swing.*;
import java.util.ArrayList;

public class ManageAlarme {
    ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
    Alarme alarme;

    public ManageAlarme(ArrayList<Moniteur> moniteurs){
        this.moniteurs = moniteurs;
    }

    public void generateAlarme(String location, String type, int defcon, String detail_type) {
    	/*
    	Génère une alarme en fonction de sa localisation, son type, son degré d'importance et ses détails
    	*/

        JOptionPane newAlarmeWarning = new JOptionPane();

        if (type.toUpperCase().equals("INCENDIE")){
            alarme = new Incendie(this);
            alarme.setType("INCENDIE");
        }
        else if (type.toUpperCase().equals("RADIATION")){
            alarme = new Radiation(this);
            alarme.setType("RADIATION");      
        }
        else if (type.toUpperCase().equals("GAZ")){
            alarme = new Gaz(this);
            alarme.setType("GAZ");
        }

        alarme.setDetail(detail_type);
        alarme.setLocation(location);
        alarme.setDefcon(defcon);
        alarme.setDate();

        newAlarmeWarning.showMessageDialog(new JFrame(), "Une nouvelle alarme a été détectée :" + "\n- Date : " + alarme.getDate() + "\n- Type : " + alarme.getType() + "\n- Lieu : " + alarme.getLocation() + "\n- Niveau d'importance : " + alarme.getDefcon() + "\n- Détail : " + alarme.getDetail(), "Nouvelle alarme", JOptionPane.WARNING_MESSAGE);
        
        for (Moniteur moniteur : moniteurs){
        	if (moniteur.getTypeMoniteur() == "A" && type.toUpperCase().equals("GAZ")){
        		moniteur.addAlarme(alarme);
        	}

        	else if (moniteur.getTypeMoniteur() == "B" && type.toUpperCase().equals("RADIATION")){
        		moniteur.addAlarme(alarme);
        	}

        	else if (type.toUpperCase().equals("INCENDIE")){
        		moniteur.addAlarme(alarme);
        	}
        }
    }
}