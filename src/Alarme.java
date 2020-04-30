import javax.swing.*;

public class Alarme {

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
