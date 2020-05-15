import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;

public class FrameMoniteur extends JFrame {
    private Moniteur moniteur;

    ArrayList<Anomalie> anomalies_recues = new ArrayList<Anomalie>(); /** Il faut trouver un moyen de remplir cette
                                                                       * liste avec les anomalies générées.
                                                                       * Je pensais mettre un Listener qui permettrait
                                                                       * de mettre à jour la JFrame.. A voir.
                                                                       */
    ArrayList<Anomalie> anomalies_traitees = new ArrayList<Anomalie>();

    public FrameMoniteur(Moniteur moniteur) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moniteur = moniteur;
    }

    public void tri_anomalies(){
        for (Alarme alarme : moniteur.getAlarmes()){
            for (Anomalie anomalie : alarme.getAnomalies()){
                if (!this.anomalies_recues.contains(anomalie)){
                    if (anomalie.getStatus() == false){
                        this.addAnomalie_reçues(anomalie);
                    }
                    else{
                        this.addAnomalie_traitees(anomalie);
                    }
                }
            }
        }
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

        left_panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel left_title = new JLabel("Anomalies reçues et non traitées");
        left_title.setForeground(Color.WHITE);

        left_panel.add(left_title, gbc);

        // JTable table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Type", "Date", "Location", "Status"}));

        // DefaultTableModel model = (DefaultTableModel) table.getModel();

        // String [] header={"Type", "Date", "Location", "Status"};
        // String [][] data={};
        // DefaultTableModel model = new DefaultTableModel(data, header); 
        // JTable table = new JTable(model);

        // model.addRow(new Object[]{"Type", "Date", "Location", "Status"});

        String [] header={"Type", "Date", "Location", "Status"};
        String [][] data={};

        DefaultTableModel model = new DefaultTableModel(data,header);

        JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(450,63));
        table.setFillsViewportHeight(true);

        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);

        for (Anomalie a : this.anomalies_recues){
            model.addRow(new Object[]{a.getType(), a.getDate(), a.getLocation(), a.getStatus()} );
        }

        // Object[][] data = new Object[][] {
        // 	{1, "John", 40.0, false },
        // 	{2, "Rambo", 70.0, false },
        // 	{3, "Zorro", 60.0, true },
        // };
        //create table with data
        gbc.gridy = 1;
        left_panel.add(js, gbc);

        this.tri_anomalies();

        System.out.println(this.anomalies_recues+" COUCOU");

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

        JButton refresh_button = new JButton("Refresh");
        refresh_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left_panel.removeAll();
                right_panel.removeAll();
                FrameMoniteur.this.display_moniteur(FrameMoniteur.this.moniteur);
            }
        });

        button_panel.add(detail_button);
        button_panel.add(close_button);
        button_panel.add(traitee_button);
        button_panel.add(refresh_button);

        this.getContentPane().add(title_panel, BorderLayout.NORTH);
        this.getContentPane().add(splitContent, BorderLayout.CENTER);
        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
