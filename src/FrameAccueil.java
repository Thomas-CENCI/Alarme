import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class FrameAccueil extends JFrame {
    Frame frame;
    ArrayList anomalies;
    ArrayList moniteurs;

    public FrameAccueil(Frame f, ArrayList<HashMap<String, String>> anomalies, ArrayList<Moniteur> moniteurs) {
        this.setSize(350, 75);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Génération d'anomalie");
        this.frame = f;
        this.anomalies = anomalies;
        this.moniteurs = moniteurs;
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
                frame = new Frame(moniteurs);
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
