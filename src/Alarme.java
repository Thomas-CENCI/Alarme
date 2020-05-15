import javax.swing.*;
import java.util.*;

public class Alarme {
    private ArrayList<Anomalie> anomaliesList = new ArrayList<Anomalie>();
    private String location;
    private String type;

    public Alarme(String type, String location){
        this.type = type;
        this.location = location;
    }

    public void addAnomalie(Anomalie anomalie){this.anomaliesList.add(anomalie);}

    public void removeAnomalie(Anomalie anomalie){this.anomaliesList.remove(anomalie);}

    public String getType(){return this.type;}

    public String getLocation(){return this.location;}

    public ArrayList<Anomalie> getAnomalies(){return this.anomaliesList;}

    public void testSeuil(int val){
        int valmax = 100;

        JOptionPane message = new JOptionPane();

        if (val > valmax){
            message.showMessageDialog(null, "Une alarme a detecté une anomalie", "Alerte", JOptionPane.WARNING_MESSAGE);
        }
        else{
            message.showMessageDialog(null, "Tudo ben", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
