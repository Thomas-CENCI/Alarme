import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class FrameAccueil extends JFrame {
    private ArrayList anomalies;
    private ArrayList moniteurs;
    private FrameMoniteur frame_moniteur;

    public FrameAccueil(ArrayList<HashMap<String, String>> anomalies, ArrayList<Moniteur> moniteurs, FrameMoniteur frame_moniteur) {
        this.setSize(350, 75);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Génération d'anomalie");
        this.anomalies = anomalies;
        this.moniteurs = moniteurs;
        this.frame_moniteur = frame_moniteur;
    }

    public void generate_button() {
        JPanel content = new JPanel();
        content.setBackground(Color.DARK_GRAY);

        JButton generer = new JButton("Générer une anomalie");
        JButton fermer = new JButton("Fermer");

        final HashMap<String, String>[] result = new HashMap[]{new HashMap<String, String>()};

        generer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame(moniteurs, frame_moniteur);
                result[0] = frame.generate();
                while (!(result[0].isEmpty())) {
                    anomalies.add(frame.generate());
                    System.out.println(anomalies);
                }
            }
        });

        fermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        content.add(generer);
        content.add(fermer);
        this.getContentPane().add(content);
        this.setVisible(true);
    }
}
