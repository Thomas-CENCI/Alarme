import javax.swing.*;
import java.util.ArrayList;

public class Moniteur {
    ArrayList<Alarme> alarmList = new ArrayList<Alarme>();
    private Anomalie anomalie;
    FrameMoniteur frame;
    public String type_moniteur;

    public Moniteur(String type){
        this.type_moniteur = type;
    }

    public void setFrame(FrameMoniteur fm) { this.frame = fm; }

    public ArrayList<Alarme> getAlarmes(){
        return this.alarmList;
    }

    public void addAlarmListener(Alarme alarme){
        this.alarmList.add(alarme);
    }

    public void removeAlarmListener(Alarme alarme){
        this.alarmList.remove(alarme);
    }

    public void generateAnomalie(String location, String type, int defcon) {
        JOptionPane newAnomalieWarning = new JOptionPane();

        if (type.toUpperCase().equals("INCENDIE")) {
            anomalie = new Incendie(this);
            anomalie.setType("INCENDIE");
            anomalie.setLocation(location);
            anomalie.setDefcon(defcon);
            newAnomalieWarning.showMessageDialog(new JFrame(), "Une nouvelle anomalie a été détectée :\n- Type : " + anomalie.getType() + "\n- Lieu : " + anomalie.location + "\n- Niveau d'importance : " + anomalie.defcon, "Nouvelle anomalie", JOptionPane.WARNING_MESSAGE);
        }
        if (type.toUpperCase().equals("RADIATION") && this.type_moniteur.equals("B")) {
            anomalie = new Radiation(this);
            anomalie.setType("RADIATION");
            anomalie.setLocation(location);
            anomalie.setDefcon(defcon);
            newAnomalieWarning.showMessageDialog(new JFrame(), "Une nouvelle anomalie a été détectée :\n- Type : " + anomalie.getType() + "\n- Lieu : " + anomalie.location + "\n- Niveau d'importance : " + anomalie.defcon, "Nouvelle anomalie", JOptionPane.WARNING_MESSAGE);
        }
        if (type.toUpperCase().equals("GAZ") && this.type_moniteur.equals("A")) {
            anomalie = new Gaz(this);
            anomalie.setType("GAZ");
            anomalie.setLocation(location);
            anomalie.setDefcon(defcon);
            newAnomalieWarning.showMessageDialog(new JFrame(), "Une nouvelle anomalie a été détectée :\n- Type : " + anomalie.getType() + "\n- Lieu : " + anomalie.location + "\n- Niveau d'importance : " + anomalie.defcon, "Nouvelle anomalie", JOptionPane.WARNING_MESSAGE);
        }

        for (Alarme alarme : alarmList){
            if (alarme.getType().toUpperCase().equals(type.toUpperCase()) && alarme.getLocation().toUpperCase().equals(location.toUpperCase())){
                boolean exist = false;
                for (Anomalie a : alarme.getAnomalies()){
                    if (a.getDate().equals(anomalie.getDate())){
                        exist = true;
                    }
                }
                if (!exist){
                    alarme.addAnomalie(anomalie);
                    alarme.testSeuil(anomalie.seuil());         
                }
            }
        }
    }
}
