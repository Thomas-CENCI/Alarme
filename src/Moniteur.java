import javax.swing.*;
import java.util.ArrayList;

public class Moniteur {
    ArrayList<Alarme> alarmList = new ArrayList<Alarme>();
    private Alarme alarme;
    public String type_moniteur;

    public Moniteur(String type){
        this.type_moniteur = type;
    }

    public ArrayList<Alarme> getAlarmes(){
        return this.alarmList;
    }

    public void addAlarme(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void addAlarmListener(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void removeAlarmListener(Alarme alarme){
        this.alarmList.remove(alarme);
    }

    public ArrayList<Alarme> getAlarmesRecues(){
        ArrayList<Alarme> alarmes_recues = new ArrayList<Alarme>();
        for (Alarme alarme : alarmList){
            if (alarme.getStatus() == false){
                alarmes_recues.add(alarme);
            }
        }
        return alarmes_recues;
    }

    public ArrayList<Alarme> getAlarmesTraitees(){
        ArrayList<Alarme> alarmes_traitees = new ArrayList<Alarme>();
        for (Alarme alarme : alarmList){
            if (alarme.getStatus() == true){
                alarmes_traitees.add(alarme);
            }
        }
        return alarmes_traitees;
    }

    public void generateAlarme(String location, String type, int defcon, String detail_type) {

        JOptionPane newAlarmeWarning = new JOptionPane();

        if (type.toUpperCase().equals("INCENDIE") || type.toUpperCase().equals("RADIATION") && this.type_moniteur.equals("B") || type.toUpperCase().equals("GAZ") && this.type_moniteur.equals("A")) {
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

            newAlarmeWarning.showMessageDialog(new JFrame(), "Une nouvelle alarme a été détectée :" + "\n- Date : " + alarme.getDate() + "\n- Moniteur : " + this.type_moniteur + "\n- Type : " + alarme.getType() + "\n- Lieu : " + alarme.getLocation() + "\n- Niveau d'importance : " + alarme.getDefcon() + "\n- Détail : " + alarme.getDetail(), "Nouvelle alarme", JOptionPane.WARNING_MESSAGE);
            this.addAlarme(alarme);
        }
    }
}
