import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class FrameMoniteur extends JFrame {
    private Moniteur moniteurA;
    private Moniteur moniteurB;

    ArrayList<Anomalie> anomalies_recues = new ArrayList<Anomalie>(); /** Il faut trouver un moyen de remplir cette
                                                                       * liste avec les anomalies générées.
                                                                       * Je pensais mettre un Listener qui permettrait
                                                                       * de mettre à jour la JFrame.. A voir.
                                                                       */
    ArrayList<Anomalie> anomalies_traitees = new ArrayList<Anomalie>();

    public FrameMoniteur(Moniteur moniteurA, Moniteur moniteurB) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moniteurA = moniteurA;
        this.moniteurB = moniteurB;
    }

    public void refresh() {
        //left_panel.removeAll();
        //right_panel.removeAll();
        FrameMoniteur.this.display_moniteur(FrameMoniteur.this.moniteurA, FrameMoniteur.this.moniteurB);
    }

    public void tri_anomalies(Moniteur moniteur){
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

public void display_moniteur(Moniteur moniteurA, Moniteur moniteurB){

        Font police = new Font("Tahoma", Font.BOLD, 18);

        this.setTitle("Moniteurs");

        JOptionPane detail = new JOptionPane();
        JOptionPane confirmation = new JOptionPane();

        JSplitPane content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        content.setResizeWeight(0.5);

        JSplitPane splitContentG = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitContentG.setResizeWeight(0.43);
        content.setLeftComponent(splitContentG);

        JPanel left_panel_A = new JPanel();
        left_panel_A.setBackground(Color.DARK_GRAY);

        left_panel_A.setLayout(new GridBagLayout());

        GridBagConstraints gbc_A = new GridBagConstraints();
        gbc_A.anchor = GridBagConstraints.NORTH;
        gbc_A.weightx = 0.1;
        gbc_A.weighty = 0.1;
        gbc_A.gridx = 0;
        gbc_A.gridy = 0;

        JLabel left_title_A = new JLabel("Anomalies reçues et non traitées (A)");
        left_title_A.setForeground(Color.WHITE);

        left_panel_A.add(left_title_A, gbc_A);

        // JTable table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Type", "Date", "Location", "Status"}));

        // DefaultTableModel model = (DefaultTableModel) table.getModel();

        // String [] header={"Type", "Date", "Location", "Status"};
        // String [][] data={};
        // DefaultTableModel model = new DefaultTableModel(data, header);
        // JTable table = new JTable(model);

        // model.addRow(new Object[]{"Type", "Date", "Location", "Status"});

        String [] header_A = {"Type", "Date", "Location", "Status"};
        String [][] data_A = {};

        DefaultTableModel model_A = new DefaultTableModel(data_A,header_A);

        JTable table_A = new JTable(model_A);

        table_A.setPreferredScrollableViewportSize(new Dimension(450,63));
        table_A.setFillsViewportHeight(true);

        JScrollPane js = new JScrollPane(table_A);
        js.setVisible(true);

        for (Anomalie a : this.anomalies_recues){
            model_A.addRow(new Object[]{a.getType(), a.getDate(), a.getLocation(), a.getStatus()} );
        }

        // Object[][] data = new Object[][] {
        //  {1, "John", 40.0, false },
        //  {2, "Rambo", 70.0, false },
        //  {3, "Zorro", 60.0, true },
        // };
        //create table with data
        gbc_A.gridy = 1;
        left_panel_A.add(js, gbc_A);

        this.tri_anomalies(moniteurA);

        System.out.println(this.anomalies_recues+" COUCOU");

        left_panel_A.setBackground(Color.DARK_GRAY);
        left_title_A.setFont(police);
        splitContentG.setLeftComponent(left_panel_A);

        JPanel right_panel_A = new JPanel();
        right_panel_A.setBackground(Color.DARK_GRAY);

        JLabel right_title_A = new JLabel("Anomalies traitées (A)");
        right_title_A.setForeground(Color.WHITE);
        right_title_A.setFont(police);
        right_panel_A.add(right_title_A, BorderLayout.NORTH);

        right_panel_A.setBackground(Color.DARK_GRAY);
        splitContentG.setRightComponent(right_panel_A);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JSplitPane splitContentD = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitContentD.setResizeWeight(0.43);
        content.setRightComponent(splitContentD);

        JPanel left_panel_B = new JPanel();
        left_panel_B.setBackground(Color.DARK_GRAY);

        left_panel_B.setLayout(new GridBagLayout());

        JLabel left_title_B = new JLabel("Anomalies reçues et non traitées (B)");
        left_title_B.setForeground(Color.WHITE);

        GridBagConstraints gbc_B = new GridBagConstraints();
        gbc_B.anchor = GridBagConstraints.NORTH;
        gbc_B.weightx = 0.1;
        gbc_B.weighty = 0.1;
        gbc_B.gridx = 0;
        gbc_B.gridy = 0;

        left_panel_B.add(left_title_B, gbc_B);

        // JTable table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Type", "Date", "Location", "Status"}));

        // DefaultTableModel model = (DefaultTableModel) table.getModel();

        // String [] header={"Type", "Date", "Location", "Status"};
        // String [][] data={};
        // DefaultTableModel model = new DefaultTableModel(data, header);
        // JTable table = new JTable(model);

        // model.addRow(new Object[]{"Type", "Date", "Location", "Status"});

        String [] header_B = {"Type", "Date", "Location", "Status"};
        String [][] data_B = {};


        DefaultTableModel model_B = new DefaultTableModel(data_B,header_B);

        JTable table_B = new JTable(model_B);

        table_B.setPreferredScrollableViewportSize(new Dimension(450,63));
        table_B.setFillsViewportHeight(true);

        for (Anomalie a : this.anomalies_recues){
            model_B.addRow(new Object[]{a.getType(), a.getDate(), a.getLocation(), a.getStatus()} );
        }

        // Object[][] data = new Object[][] {
        //  {1, "John", 40.0, false },
        //  {2, "Rambo", 70.0, false },
        //  {3, "Zorro", 60.0, true },
        // };
        //create table with data
        gbc_B.gridy = 1;
        left_panel_B.add(js, gbc_B);

        this.tri_anomalies(moniteurB);

        System.out.println(this.anomalies_recues+" COUCOU");

        left_panel_B.setBackground(Color.DARK_GRAY);
        left_title_B.setFont(police);
        splitContentD.setLeftComponent(left_panel_B);

        JPanel right_panel_B = new JPanel();
        right_panel_B.setBackground(Color.DARK_GRAY);

        JLabel right_title_B = new JLabel("Anomalies traitées (B)");
        right_title_B.setForeground(Color.WHITE);
        right_title_B.setFont(police);
        right_panel_B.add(right_title_B, BorderLayout.NORTH);

        right_panel_B.setBackground(Color.DARK_GRAY);
        splitContentD.setRightComponent(right_panel_B);

        JPanel button_panel = new JPanel();
        button_panel.setBackground(Color.DARK_GRAY);

        JButton close_button = new JButton("Fermer");
        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm;
                confirm = confirmation.showConfirmDialog(null, "Fermer la fenêtre des moniteurs", "Confirmation de fermeture", JOptionPane.YES_NO_OPTION);
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

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
