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

    ArrayList<Alarme> alarmes_recues = new ArrayList<Alarme>(); /** Il faut trouver un moyen de remplir cette
                                                                       * liste avec les anomalies générées.
                                                                       * Je pensais mettre un Listener qui permettrait
                                                                       * de mettre à jour la JFrame.. A voir.
                                                                       */
    ArrayList<Alarme> alarmes_traitees = new ArrayList<Alarme>();

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

    public void tri_alarmes(Moniteur moniteur){
        for (Alarme alarme : moniteur.getAlarmes()){
            if (!this.alarmes_recues.contains(alarme)){
                if (alarme.getStatus() == false){
                    this.addAlarme_recues(alarme);
                }
                else{
                    this.addAlarme_traitees(alarme);
                }
            }
        }
    }

    public void addAlarme_recues(Alarme alarme) { alarmes_recues.add(alarme); }

    public void addAlarme_traitees(Alarme alarme) { alarmes_recues.remove(alarme); alarmes_traitees.add(alarme); }

public void display_moniteur(Moniteur moniteurA, Moniteur moniteurB){
        JPanel right_panel_A = new JPanel();
        JPanel right_panel_B = new JPanel();
        JPanel left_panel_A = new JPanel();
        JPanel left_panel_B = new JPanel();

        Font police = new Font("Tahoma", Font.BOLD, 18);

        this.setTitle("Moniteurs");

        JOptionPane detail = new JOptionPane();
        JOptionPane confirmation = new JOptionPane();

        JSplitPane content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        content.setResizeWeight(0.5);

        JSplitPane splitContentG = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitContentG.setResizeWeight(0.43);
        content.setLeftComponent(splitContentG);

        left_panel_A.setBackground(Color.DARK_GRAY);

        left_panel_A.setLayout(new GridBagLayout());

        GridBagConstraints left_gbc_A = new GridBagConstraints();
        left_gbc_A.anchor = GridBagConstraints.NORTH;
        left_gbc_A.weightx = 0.1;
        left_gbc_A.weighty = 0.1;
        left_gbc_A.gridx = 0;
        left_gbc_A.gridy = 0;

        JLabel left_title_A = new JLabel("Alarmes reçues et non traitées (A)");
        left_title_A.setForeground(Color.WHITE);

        left_panel_A.add(left_title_A, left_gbc_A);

        // JTable table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"Type", "Date", "Location", "Status"}));

        // DefaultTableModel model = (DefaultTableModel) table.getModel();

        // String [] header={"Type", "Date", "Location", "Status"};
        // String [][] data={};
        // DefaultTableModel model = new DefaultTableModel(data, header);
        // JTable table = new JTable(model);

        // model.addRow(new Object[]{"Type", "Date", "Location", "Status"});

        String [] left_header_A = {"Type", "Date", "Location", "Status"};
        String [][] left_data_A = {};

        DefaultTableModel left_model_A = new DefaultTableModel(left_data_A,left_header_A);

        JTable left_table_A = new JTable(left_model_A);

        left_table_A.setPreferredScrollableViewportSize(new Dimension(450,63));
        left_table_A.setFillsViewportHeight(true);

        JScrollPane left_js_A = new JScrollPane(left_table_A);
        left_js_A.setVisible(true);

        for (Alarme a : this.alarmes_recues){
            left_model_A.addRow(new Object[]{a.getType(), a.getDate(), a.getLocation(), a.getStatus()} );
        }

        // Object[][] data = new Object[][] {
        //  {1, "John", 40.0, false },
        //  {2, "Rambo", 70.0, false },
        //  {3, "Zorro", 60.0, true },
        // };
        //create table with data
        left_gbc_A.gridy = 1;
        left_panel_A.add(left_js_A, left_gbc_A);

        this.tri_alarmes(moniteurA);

        left_panel_A.setBackground(Color.DARK_GRAY);
        left_title_A.setFont(police);

        splitContentG.setLeftComponent(left_panel_A);

        JLabel right_title_A = new JLabel("Alarmes traitées (A)");
        right_title_A.setForeground(Color.WHITE);
        right_title_A.setFont(police);

        right_panel_A.setLayout(new GridBagLayout());

        GridBagConstraints right_gbc_A = new GridBagConstraints();
        right_gbc_A.anchor = GridBagConstraints.NORTH;
        right_gbc_A.weightx = 0.1;
        right_gbc_A.weighty = 0.1;
        right_gbc_A.gridx = 0;
        right_gbc_A.gridy = 0;

        right_panel_A.add(right_title_A, right_gbc_A);

        String [] right_header_A = {"Type", "Date", "Location", "Status"};
        String [][] right_data_A = {};

        DefaultTableModel right_model_A = new DefaultTableModel(right_data_A,right_header_A);

        JTable right_table_A = new JTable(right_model_A);

        right_table_A.setPreferredScrollableViewportSize(new Dimension(450,63));
        right_table_A.setFillsViewportHeight(true);

        JScrollPane right_js_A = new JScrollPane(right_table_A);
        right_js_A.setVisible(true);

        for (Alarme a : this.alarmes_recues){
            right_model_A.addRow(new Object[]{a.getType(), a.getDate(), a.getLocation(), a.getStatus()} );
        }

        // Object[][] data = new Object[][] {
        //  {1, "John", 40.0, false },
        //  {2, "Rambo", 70.0, false },
        //  {3, "Zorro", 60.0, true },
        // };
        //create table with data
        right_gbc_A.gridy = 1;
        right_panel_A.add(right_js_A, right_gbc_A);

        this.tri_alarmes(moniteurA);

        right_panel_A.setBackground(Color.DARK_GRAY);
        right_title_A.setFont(police);

        splitContentG.setRightComponent(right_panel_A);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JSplitPane splitContentD = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitContentD.setResizeWeight(0.43);
        content.setRightComponent(splitContentD);

        left_panel_B.setBackground(Color.DARK_GRAY);

        left_panel_B.setLayout(new GridBagLayout());

        JLabel left_title_B = new JLabel("Alarmes reçues et non traitées (B)");
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

        JScrollPane js_B = new JScrollPane(table_B);
        js_B.setVisible(true);

        for (Alarme a : this.alarmes_recues){
            model_B.addRow(new Object[]{a.getType(), a.getDate(), a.getLocation(), a.getStatus()} );
        }

        // Object[][] data = new Object[][] {
        //  {1, "John", 40.0, false },
        //  {2, "Rambo", 70.0, false },
        //  {3, "Zorro", 60.0, true },
        // };
        //create table with data
        gbc_B.gridy = 1;
        left_panel_B.add(js_B, gbc_B);

        this.tri_alarmes(moniteurB);

        left_panel_B.setBackground(Color.DARK_GRAY);
        left_title_B.setFont(police);
        splitContentD.setLeftComponent(left_panel_B);

        right_panel_B.setBackground(Color.DARK_GRAY);

        JLabel right_title_B = new JLabel("Alarmes traitées (B)");
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
                confirm = confirmation.showConfirmDialog(null, "L'alarme est-elle traitée ?", "Confirmation de traitement", JOptionPane.YES_NO_OPTION);
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

        System.out.println(this.alarmes_recues+" Alarmes recues frame moniteur");

        button_panel.add(detail_button);
        button_panel.add(close_button);
        button_panel.add(traitee_button);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
