import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameMoniteur extends JFrame {

    ArrayList<Anomalie> anomalies_recues = new ArrayList<Anomalie>(); /** Il faut trouver un moyen de remplir cette
                                                                       * liste avec les anomalies générées.
                                                                       * Je pensais mettre un Listener qui permettrait
                                                                       * de mettre à jour la JFrame.. A voir.
                                                                       */
    ArrayList<Anomalie> anomalies_traitees = new ArrayList<Anomalie>();

    public FrameMoniteur() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addAnomalie_reçues(Anomalie anomalie) { anomalies_recues.add(anomalie); }

    public void addAnomalie_traitees(Anomalie anomalie) { anomalies_recues.remove(anomalie); anomalies_traitees.add(anomalie); }

    public void display_moniteur(Moniteur moniteur){
        this.setTitle("Moniteur " + moniteur.type_moniteur);

        Font police_title = new Font("Tahoma", Font.BOLD, 20);

        JPanel title_panel = new JPanel();
        title_panel.setBackground(Color.ORANGE);

        JLabel title = new JLabel("Moniteur " + moniteur.type_moniteur);
        title.setForeground(Color.DARK_GRAY);
        title.setFont(police_title);
        title_panel.add(title, BorderLayout.CENTER);

        Font police = new Font("Tahoma", Font.BOLD, 18);

        JOptionPane detail = new JOptionPane();
        JOptionPane confirmation = new JOptionPane();

        JSplitPane splitContent = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitContent.setResizeWeight(0.43);

        JPanel button_panel = new JPanel();
        button_panel.setBackground(Color.DARK_GRAY);

        JPanel left_panel = new JPanel();
        left_panel.setBackground(Color.DARK_GRAY);

        JLabel left_title = new JLabel("Anomalies reçues et non traitées");
        left_title.setForeground(Color.WHITE);
        left_panel.add(left_title, BorderLayout.NORTH);

        left_panel.setBackground(Color.DARK_GRAY);
        left_title.setFont(police);
        splitContent.setLeftComponent(left_panel);

        JPanel right_panel = new JPanel();
        right_panel.setBackground(Color.DARK_GRAY);

        JLabel right_title = new JLabel("Anomalies traitées");
        right_title.setForeground(Color.WHITE);
        right_title.setFont(police);
        right_panel.add(right_title, BorderLayout.NORTH);

        right_panel.setBackground(Color.DARK_GRAY);
        splitContent.setRightComponent(right_panel);

        JButton close_button = new JButton("Fermer");
        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm;
                confirm = confirmation.showConfirmDialog(null, "Fermer la fenêtre du moniteur " + moniteur.type_moniteur + " ?", "Confirmation de fermeture", JOptionPane.YES_NO_OPTION);
                if(confirm == 0) {
                    dispose();
                }
            }
        });

        JButton traitee_button = new JButton("Traiter");
        traitee_button.setVisible(false);
        traitee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Mettre en place le déplacement de la zone "Anomalies reçues" vers "Anomalies traitées".
                 * Il suffit de supprimer de la liste anomalies_recues et de compléter anomalies_traitees.
                 */
                int confirm;
                confirm = confirmation.showConfirmDialog(null, "L'anomalie est-elle traitée ?", "Confirmation de traitement", JOptionPane.YES_NO_OPTION);
                if(confirm == 0){
                    traitee_button.setVisible(false);
                }
            }
        });

        JButton detail_button = new JButton("Détail");
        detail_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Il faut mettre en place la fonction qui affiche tous les détails de l'élément sélectionné. Voir
                 * Frame.java dans lequel j'ai mis en place une fonction qui parcourt les éléments et qui détecte
                 * s'ils sont sélectionnés. A voir si on peut s'en inspirer.
                 */
                detail.showMessageDialog(null, "Détail de l'élément sélectionné", "Détail", JOptionPane.INFORMATION_MESSAGE);
                traitee_button.setVisible(true);
            }
        });

        button_panel.add(detail_button);
        button_panel.add(close_button);
        button_panel.add(traitee_button);

        this.getContentPane().add(title_panel, BorderLayout.NORTH);
        this.getContentPane().add(splitContent, BorderLayout.CENTER);
        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
